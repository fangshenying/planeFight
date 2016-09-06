package cn.tedu.shooter;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class World extends JPanel {
	/** 一片天空 */
	private Sky sky;
	/** 一个英雄飞机 */
	private Hero hero;
	/** 英雄打出的多个子弹 */
	private Bullet[] bullets;
	/** 飞行的物体（大飞机， 小飞机，蜜蜂等） **/
	private FlyingObject[] flyingObjects;
	/** 计分变量，生命变量，火力变量 **/
	private int score = 0, life = 3, fireType = 1;
	/** 定义游戏状态管理部分的变量 **/
	public static final int READY = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;

	private int state = READY;

	private static BufferedImage pause;
	private static BufferedImage ready;
	private static BufferedImage gameOver;

	static {
		try {
			String png = "cn/tedu/shooter/start.png";
			ready = ImageIO.read(World.class.getClassLoader().getResourceAsStream(png));
			png = "cn/tedu/shooter/pause.png";
			pause = ImageIO.read(World.class.getClassLoader().getResourceAsStream(png));
			png = "cn/tedu/shooter/gameover.png";
			gameOver = ImageIO.read(World.class.getClassLoader().getResourceAsStream(png));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 构造器
	public World() {
		sky = new Sky();
		hero = new Hero();
		bullets = new Bullet[] { new Bullet(236, 430), new Bullet(236, 450), new Bullet(236, 470) };
		// bullets = new Bullet[0];
		flyingObjects = new FlyingObject[] { new Bee(), new Airplane(), new BigPlane() };
		nextTime = System.currentTimeMillis() + 1000;
		// 可以加也可以不加,单元测试时必须注释掉
		// nextShootTime = System.currentTimeMillis() +200;
	}

	private long nextTime;

	/** nextOne方法(指定时间随机产生一个敌人)被设定时（1/24秒）调用 **/
	public void nextOne() {
		long now = System.currentTimeMillis();
		if (now >= nextTime) {
			nextTime = now + 300;
			FlyingObject obj = randomOne();
			flyingObjects = Arrays.copyOf(flyingObjects, flyingObjects.length + 1);
			flyingObjects[flyingObjects.length - 1] = obj;
		}
	}

	private static FlyingObject randomOne() {
		int n = (int) (Math.random() * 10);
		switch (n) {
		case 0:
			return new Bee();
		case 1:
		case 2:
			return new BigPlane();
		case 3:
			return new BigPlaneAward();
		default:
			return new Airplane();
		}
	}

	// 添加toString方法，方便测试
	public String toString() {
		return "sky:" + sky + ", \n" + "hero:" + hero + ", \n" + "bullets:" + Arrays.toString(bullets) + ", \n"
				+ "flyingObjects:" + Arrays.toString(flyingObjects);
	}

	/**
	 * 在World类中修改JPanel 重写JPanel提供的绘制方法，修改其绘制功能。 替换了原有的绘制功能
	 **/

	public void paint(Graphics g) {
		// Graphics g 是一个画笔对象，由Java Swing提供的画笔对象
		// g.drawString("Hello World", 100, 150);
		// g.drawLine(160, 10, 160, 200);

		// 显示图片
		// g.drawImage(sky.image, (int)sky.x, (int)sky.y, null);
		sky.paint(g);
		hero.paint(g);

		// g.drawImage(bullets[0].image, (int)bullets[0].x,
		// (int)bullets[0].y,null);
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].paint(g);
		}

		// g.drawImage(flyingObjects[0].image, (int)flyingObjects[0].x,
		// (int)flyingObjects[0].y, null);
		for (int i = 0; i < flyingObjects.length; i++) {
			// flyingObjects[i].y = 300;// 测试代码
			flyingObjects[i].paint(g);
		}

		// 在World的paint方法中增加分数显示
		g.drawString("SCORE:" + score, 20, 30);
		g.drawString("LIFE:" + life, 20, 50);
		g.drawString("FIRE:" + fireType, 20, 70);
		g.drawRect(10, 15, 80, 60);

		//游戏状态图片选择
		switch (state) {
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			g.drawImage(pause, 400, 0, null);
			break;
		case READY:
			g.drawImage(ready, 0, 0, null);
			g.drawImage(ready, 400, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameOver, 0, 0, null);
			g.drawImage(gameOver, 400, 0, null);
			break;
		}
	}

	/*
	 * 在world类中添加定时器，和启动定时器的方法
	 */
	private Timer timer;

	public void action() {
		/*
		 * 开始鼠标的监听
		 */
		MouseAdapter l = new MouseAdapter() {// 写好了一个监听器
			@Override
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING) {
					// System.out.println(e.getWhen());
					int x = e.getX();
					int y = e.getY();
					World.this.hero.move(x, y);// 内部类共享外部类的属性和方法
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) {
					state = PAUSE;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) {
					state = RUNNING;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (state == READY) {
					state = RUNNING;
				}
				if (state == GAME_OVER) {
					score = 0;
					life = 3;
					fireType = 1;
					hero = new Hero();
					bullets = new Bullet[0];
					flyingObjects = new FlyingObject[0];
					state = READY;
				}
			}

		};

		// 将监听器加入到当前面板中
		addMouseListener(l);
		addMouseMotionListener(l);

		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if (state == RUNNING) {
					/*
					 * 坐标位置变化
					 */
					// 每隔1秒随机产生一个飞行物
					nextOne();
					move();// 移动方法
					shoot();// 射击封装到shoot方法
					// 删除掉那些需要删除的对象
					// 凡是状态时REMOVE的子弹/飞机
					removeObjects();
					// System.out.println(bullets.length);//跟踪子弹数组的长度变化
					// 检查碰撞情况
					duangDuang();
					// 控制英雄的生命周期
					heroLifeCircle();
				}
				/*
				 * 图片重新绘制,跟上坐标的变化
				 */
				// 重新绘制JPanel
				repaint();// 继承自JPanel的方法
			}
		}, 0, 1000 / 24);// 从当前时间开始每隔1/24秒定时器更新一次
	}

	/*
	 * 添加英雄生命周期控制方法
	 */
	public void heroLifeCircle() {
		if (hero.isActive()) {
			for (FlyingObject plane : flyingObjects) {
				if (plane.isActive() && plane.duang(hero)) {
					hero.goDead();
					plane.goDead();
				}
			}
		}
		if (hero.canRemove()) {
			if (life > 0) {
				life--;
				hero = new Hero();
				// 清场(确保重新生成的英雄机附近的飞机都去死)
				for (FlyingObject plane : flyingObjects) {
					if (plane.isActive() && plane.duang(hero)) {
						plane.goDead();
					}
				}
			} else {
				state = GAME_OVER;
			}
		}
	}

	/*
	 * 添加碰撞检查方法
	 */
	public void duangDuang() {
		for (FlyingObject plane : flyingObjects) {
			if (plane.isActive()) {
				if (shootByBullet(plane)) {
					plane.hit();
					if (plane.isDead()) {
						// 计分
						// 转换成子类型才能计分
						if (plane instanceof Enemy) {
							Enemy enemy = (Enemy) plane;
							int s = enemy.getScore();
							score += s;
						}
						// 奖品
						if (plane instanceof Award) {
							Award award = (Award) plane;
							int awd = award.getAward();
							if (awd == Award.LIFE) {
								life++;
							} else if (awd == Award.FIRE) {
								fireType = 1;
							} else if (awd == Award.DOUBLE_FIRE) {
								fireType = 2;
							}
						}
					}
				}
			}
		}
	}

	public boolean shootByBullet(FlyingObject plane) {
		for (Bullet bullet : bullets) {
			if (bullet.isActive()) {
				if (plane.duang(bullet)) {
					bullet.hit();
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * 删除掉那些需要删除的对象 凡是状态时REMOVE的子弹/飞机
	 */
	public void removeObjects() {
		// 删多余的子弹(性能最有算法是找到第一个状态为REMOVE状态的子弹时才开始创建空数组)
		Bullet[] ary = {};
		for (Bullet bullet : bullets) {
			if (bullet.canRemove()) {// 常量要用类名访问,World与FlyingObject没有继承关系
				continue;
			}
			ary = Arrays.copyOf(ary, ary.length + 1);
			ary[ary.length - 1] = bullet;// 每次取最后一个元素进行赋值
		}
		bullets = ary;

		// 删多余的飞机
		FlyingObject[] ary1 = {};
		for (FlyingObject object : flyingObjects) {
			if (object.canRemove()) {
				continue;
			}
			ary1 = Arrays.copyOf(ary1, ary1.length + 1);
			ary1[ary1.length - 1] = object;
		}
		flyingObjects = ary1;
	}

	// 测试方法,测试掉删除掉子弹和飞机（因为bulles和flyingObjects是private的,故在World类里写测试方法）
	public void testRemove() {
		bullets[0].hit();
		flyingObjects[0].hit();
		flyingObjects[0].move();
		flyingObjects[0].move();
		flyingObjects[0].move();
		flyingObjects[0].move();
		flyingObjects[0].move();
		flyingObjects[0].move();
		// flyingObjects[0].state = FlyingObject.REMOVE;

	}

	/*
	 * 移动方法
	 */
	private void move() {
		// 两个天空交替移动
		sky.move();
		// 每个飞机移动一下
		for (int i = 0; i < flyingObjects.length; i++) {
			flyingObjects[i].move();
		}
		// 每个子弹移动一下
		for (Bullet b : bullets) {
			b.move();// b代表bullets每个元素
		}
		// 英雄机动画效果
		hero.move();
	}

	/*
	 * 射击控制方法 被主定时器调用
	 */
	private long nextShootTime;

	public void shoot() {
		// 控制射击速度
		long now = System.currentTimeMillis();
		if (now > nextShootTime) {
			nextShootTime = now + 150;
			Bullet[] ary = hero.shoot(fireType);
			// 将子弹ary添加到bullets数组
			bullets = Arrays.copyOf(bullets, bullets.length + ary.length);
			System.arraycopy(ary, 0, bullets, bullets.length - ary.length, ary.length);
		}
	}

	/** 在 World中添加main方法，显示图形界面 **/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(480, 652);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);// 窗口居中
		// JPanel panel = new JPanel();
		// panel.setBackground(Color.BLACK);
		// frame.add(panel);
		World world = new World();
		frame.add(world);
		// frame窗口在显示的时候，会自动调用paint方法，
		// 如果重写paint，则显示时候自动执行重写以后的paint方法
		frame.setVisible(true);
		world.action();// 启动定时器
	}

}
