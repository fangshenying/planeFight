package cn.tedu.test;

import cn.tedu.shooter.World;

public class WorldShootTest {

	public static void main(String[] args) {
		World world = new World();
		System.out.println(world);
		System.out.println();
		world.shoot();
		// 检查结果：子弹数组增加了两个元素
		System.out.println(world);

	}

}
