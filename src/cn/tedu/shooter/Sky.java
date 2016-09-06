package cn.tedu.shooter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sky extends FlyingObject {
	/** 唯一（static）的图片素材 **/
	private static BufferedImage img;
	static {
		String png = "cn/tedu/shooter/background.png";
		try {
			img = ImageIO.read(Sky.class.getClassLoader().getResourceAsStream(png));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sky() {
		x = 0;
		y = 0;
		width = 480;
		height = 852;
		// 初始化正在显示的图片
		this.image = img;
		this.step = 1;
		y1 = -height;
	}

	private double y1;

	public void move() {
		y++;
		y1++;

		if (y >= height) {
			y = -height;
		}

		if (y1 >= height) {
			y1 = -height;
		}
	}

	public void paint(Graphics g) {
		g.drawImage(image, (int) x, (int) y, null);
		g.drawImage(image, (int) x, (int) y1, null);
	}

	protected BufferedImage nextImage() {
		return null;
	}

}
