package cn.tedu.shooter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Airplane extends FlyingObject implements Enemy {
	private static BufferedImage[] imgs;

	static {
		imgs = new BufferedImage[5];
		try {
			for (int i = 0; i < imgs.length; i++) {
				String png = "cn/tedu/shooter/airplane" + i + ".png";
				imgs[i] = ImageIO.read(Airplane.class.getClassLoader().getResourceAsStream(png));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Airplane() {
		// 小飞机的出场位置是随机的
		super(49, 36);
		this.image = imgs[0];
	}

	/*
	 * imgs=[0 1 2 3 4 ]
	 **/
	private int index = 0;// 下标序号

	protected BufferedImage nextImage() {
		index++;
		if (index >= imgs.length) {
			return null;
		}
		return imgs[index];

	}
	
	public void paint(Graphics g) {
		super.paint(g);//调用父类的方法
		if(isDead()){
			g.drawString("" + getScore(), (int)x, (int)y);//死了在小飞机上显示相应的分数
		}
	}

	
	/*
	 * 小飞机打掉一个得1分
	 */
	public int getScore() {

		return 1;
	}

}
