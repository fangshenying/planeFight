package cn.tedu.test;

import cn.tedu.shooter.Bullet;

public class BulletTest {

	public static void main(String[] args) {
		Bullet b = new Bullet(200, 200);
		System.out.println(b);
		b.move();
		System.out.println(b);

	}

}
