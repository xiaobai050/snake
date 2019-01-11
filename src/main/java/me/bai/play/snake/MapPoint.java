package me.bai.play.snake;

/**
 * @author Bai
 * @date 2017年9月21日 下午7:01:39
 */
public class MapPoint {
	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSamePosition(MapPoint anotherJoint) {
		return x == anotherJoint.getX() && y == anotherJoint.getY();
	}
}
