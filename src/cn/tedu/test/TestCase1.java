package cn.tedu.test;

import cn.tedu.shooter.Airplane;
import cn.tedu.shooter.Bee;
import cn.tedu.shooter.BigPlane;
import cn.tedu.shooter.Bullet;
import cn.tedu.shooter.Hero;
import cn.tedu.shooter.Sky;
import cn.tedu.shooter.World;

public class TestCase1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Airplane airplane = new Airplane();
		System.out.println(airplane);
		
		BigPlane plane = new BigPlane();
		System.out.println(plane);
		
		Bee bee = new Bee();
		System.out.println(bee);
		
		Hero hero = new Hero();
		System.out.println(hero);
		
		Bullet bullet = new Bullet(100, 100);
		System.out.println(bullet);
		
		Sky sky = new Sky();
		System.out.println(sky);
		
		World world = new World();
		System.out.println(world);

	}

}
