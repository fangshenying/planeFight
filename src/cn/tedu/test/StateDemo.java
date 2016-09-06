package cn.tedu.test;

import cn.tedu.shooter.BigPlane;

public class StateDemo {

	public static void main(String[] args) {
		BigPlane plane = new BigPlane();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
		plane.hit();
		System.out.println(plane);
		plane.hit();
		System.out.println(plane);
		plane.hit();
		System.out.println(plane);
		System.out.println();
		plane.move();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
		plane.move();
		System.out.println(plane);
	}

}
