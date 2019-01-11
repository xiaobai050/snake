package me.bai.play.snake;

/**
 * @author Bai
 * @date 2017年9月21日 下午12:35:58
 */
public class Main {
	public static void main(String[] args) {
		new Thread(new Game()).start();
	}
}
