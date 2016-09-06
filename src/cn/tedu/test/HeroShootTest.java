package cn.tedu.test;

import java.util.Arrays;

import cn.tedu.shooter.Bullet;
import cn.tedu.shooter.Hero;

public class HeroShootTest {

	public static void main(String[] args) {
		Hero hero = new Hero();
		System.out.println(hero);
		//检查单枪射击的方法
		Bullet[] ary = hero.shoot(1);
		System.out.println(Arrays.toString(ary));
		//检查双枪射击的方法
		ary = hero.shoot(2);
		System.out.println(Arrays.toString(ary));

	}

}
