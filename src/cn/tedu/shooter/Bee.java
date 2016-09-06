package cn.tedu.shooter;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bee extends FlyingObject implements Award {
	private static BufferedImage[] imgs;

	static {
		imgs = new BufferedImage[5];
		try {
			for (int i = 0; i < imgs.length; i++) {
				String png = "cn/tedu/shooter/bee" + i + ".png";
				imgs[i] = ImageIO.read(Bee.class.getClassLoader().getResourceAsStream(png));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Bee() {
		super(60, 50);
		this.image = imgs[0];
		int[] dir = { -3, 3 };
		direction = dir[(int) (Math.random() * 2)];
		// boolean[] flags = { true, false };
		// flag = flags[(int) (Math.random() * 2)];
	}

	private int direction;
	// boolean flag;

	// 重写move方法
	public void move() {
		super.move();
		// y += step;
		if (state == ACTIVE) {
			x += direction;
			if (x >= 480 - width) {
				direction = -3;
			}
			if (x <= 0) {
				direction = 3;
			}
		}

		// if (this.x <= 0) {
		// flag = false;
		// }
		// if (this.x >= 480 - width) {
		// flag = true;
		// }
		//
		// if (!flag) {
		// x += step;
		// y += step;
		//
		// }
		//
		// if (flag) {
		// x -= step;
		// y += step;
		// }
	}

	/*
	 * imgs=[0 1 2 3 4 ]
	 **/
	private int index = 0;// 下标序号

	protected BufferedImage nextImage() {
		index++;
		if (index == imgs.length) {
			return null;
		}
		return imgs[index];
	}

	
	
	/*
	 * 实现蜜蜂中的奖励算法
	 */
	@Override
	public int getAward() {
		int[] ary = { LIFE, FIRE, DOUBLE_FIRE };
		int i = (int) (Math.random() * 3);
		return ary[i];
	}

}
