package cn.tedu.shooter;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Hero extends FlyingObject {
	private static BufferedImage[] imgs;

	static {
		imgs = new BufferedImage[6];
		try {
			for (int i = 0; i < imgs.length; i++) {
				String png = "cn/tedu/shooter/hero" + i + ".png";
				imgs[i] = ImageIO.read(Hero.class.getClassLoader().getResourceAsStream(png));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Hero() {
		this.width = 97;
		this.height = 124;
		this.x = (480 - width) / 2;
		this.y = 500;
		this.image = imgs[0];
	}

	private int n = 0;

	// 重写父类的move方法
	public void move() {
		if (isActive()) {
			n++;
			int i = n / 3 % 2;
			image = imgs[i];
		}
		if (state == DEAD) {
			// 从子类对象中获取下一个照片
			BufferedImage img = nextImage();
			if (img == null) {
				state = REMOVE;
			} else {
				image = img;
			}
		}

	}

	// 在Hero里添加重载move方法,接受鼠标坐标
	public void move(int x, int y) {
		this.x = x - width / 2;
		this.y = y - height / 2;
	}

	/*
	 * 在hero上添加射击方法
	 */
	public Bullet[] shoot(int type) {
		int x = (int) (this.x + this.width / 2 - 8 / 2);
		int y = (int) (this.y - 10);
		if (type == 1) {// 单枪
			return new Bullet[] { new Bullet(x, y) };
		}

		if (type == 2) {// 双枪
			return new Bullet[] { new Bullet(x - 35, y), new Bullet(x + 35, y) };

		}
		return new Bullet[0];
	}

	/*
	 * imgs=[0 1 2 3 4 5]
	 **/
	private int index = 1;// 下标序号

	protected BufferedImage nextImage() {
		index++;
		if (index >= imgs.length) {
			return null;
		}
		return imgs[index];

	}

}
