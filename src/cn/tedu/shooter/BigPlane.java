package cn.tedu.shooter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BigPlane extends FlyingObject implements Enemy {
	private static BufferedImage[] imgs;

	static {
		imgs = new BufferedImage[5];
		try {
			for (int i = 0; i < imgs.length; i++) {
				String png = "cn/tedu/shooter/bigplane" + i + ".png";
				imgs[i] = ImageIO.read(BigPlane.class.getClassLoader().getResourceAsStream(png));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BigPlane() {
		// 大飞机的出场位置是随机的
		super(69, 99);
		this.image = imgs[0];
		life = 3;// 大飞机打击3下才死
	}

	/*
	 * imgs=[0 1 2 3 4]
	 **/
	private int index = 0;// 下标序号

	protected BufferedImage nextImage() {
		index++;
		if (index >= imgs.length) {
			return null;
		}
		return imgs[index];

	}

	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);//调用父类的方法
		if(isDead()){
			g.drawString("" + getScore(), (int)x, (int)y);//死了在大飞机上显示相应的分数
		}
	}



	/*
	 * 大飞机打掉一个得5分
	 */
	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 5;
	}

}
