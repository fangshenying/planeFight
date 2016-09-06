package cn.tedu.test;

import cn.tedu.shooter.World;

public class TestRemove {

	public static void main(String[] args) {
		World world = new World();
		System.out.println(world);
		System.out.println();
		world.testRemove();
		world.removeObjects();
		System.out.println(world);

	}

}
