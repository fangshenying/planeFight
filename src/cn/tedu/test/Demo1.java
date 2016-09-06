package cn.tedu.test;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo1 {

	public static void main(String[] args) {
		// 如何显示图形界面
		// Frame: 框架，相框
		// size：大小
		// Visible：可见
		// Panel:面板
		JFrame frame = new JFrame();
		frame.setSize(480, 852);// 设置大小
		// 设置默认关闭窗口后的行为关窗口并且退出程序(默认只关窗口但不退出程序)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);//窗口居中
		JPanel panel = new JPanel();
		panel.setBackground(Color.orange);// 改变板的背景颜色为黑色，,板默认时灰色的（看不见）
		frame.add(panel);// 板放框里面,板默认时灰色的
		frame.setVisible(true);// 显示
		

	}

}
