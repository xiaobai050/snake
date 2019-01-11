package me.bai.play.snake;

import static me.bai.play.snake.Direction.*;

/**
 * @author Bai
 * @date 2017年9月21日 下午12:37:13
 */
public class Snake {
    private SnakeShape shape;
    private SnakeNode nextNode;
    private MoveError error;

    public boolean tryMove() {
        if (couldMove()) {
            move();
            return true;
        }
        return false;
    }

    private boolean couldMove() {
        nextNode = shape.getForwardNode();
        error = checkMoveForwardError();
        if (error != null) {
            String errMsg;
            switch (error) {
                case CRASH_BODY:
                    errMsg = "前面是自己的身体";
                    break;
                case CRASH_WALL:
                    errMsg = "前面是墙";
                    break;
                default:
                    errMsg = "其他错误：" + error;
                    break;
            }
            System.out.println("错误！" + errMsg);
            return false;
        }
        return true;
    }

    public MoveError checkMoveForwardError() {
        int x = nextNode.getX();
        int y = nextNode.getY();
        if (x < 0) { return MoveError.CRASH_WALL; }
        if (y < 0) { return MoveError.CRASH_WALL; }
        if (x >= Constants.MAP_GRID_NUM) { return MoveError.CRASH_WALL; }
        if (y >= Constants.MAP_GRID_NUM) { return MoveError.CRASH_WALL; }
        if (shape.isCover(nextNode)) { return MoveError.CRASH_BODY; }
        return null;
    }

    private void move() {
        shape.addHead(nextNode);
        shape.removeTail();
    }

    public boolean isHeadOnFood(Food food) {
        return nextNode.isSamePosition(food);
    }

    public void tryTurn(Direction newDirection) {
        if (couldTurn(newDirection)) {
            error = null;
            shape.turn(newDirection);
        }
    }

    private boolean couldTurn(Direction newDirection) {
        SnakeNode head = shape.getHead();
        Direction oldDirection = head.getDirection();
        if (dirError(newDirection, oldDirection, UP, DOWN)) { return false; }
        if (dirError(newDirection, oldDirection, LEFT, RIGHT)) { return false; }
        return true;
    }

    private boolean dirError(Direction newDirection, Direction oldDirection, Direction dir1, Direction dir2) {
        if (oldDirection == newDirection) {
            return true;
        }
        if (oldDirection == dir1 && newDirection == dir2) {
            return true;
        }
        if (oldDirection == dir2 && newDirection == dir1) {
            return true;
        }
        return false;
    }

    public int getLength() {
        return shape.getLength();
    }

    public SnakeShape getShape() {
        return shape;
    }

    public void setShape(SnakeShape shape) {
        this.shape = shape;
    }

    public MoveError getError() {
        return error;
    }

}

