package me.bai.play.snake;

/**
 * @author Bai
 * @date 2017年9月21日 下午2:53:44
 */
public class SnakeNode extends MapPoint {

	private Direction direction;

	@Override
	public String toString() {
		return "   方向:" + direction + "	坐标:(" + getX() + "," + getY() + ")";
	}

	public SnakeNode(int x, int y, Direction direction) {
		setX(x);
		setY(y);
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}