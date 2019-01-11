package me.bai.play.snake;

import java.util.LinkedList;

/**
 * @author Bai
 * @date 2017年9月21日 下午2:53:58
 */
public class SnakeShape {
    private LinkedList<SnakeNode> body;
    private SnakeNode lastTail;

    public void removeTail() {
        lastTail = body.removeFirst();
    }

    public SnakeNode getForwardNode() {
        Direction direction = body.getLast().getDirection();
        SnakeNode oldHead = body.getLast();
        int x = oldHead.getX();
        int y = oldHead.getY();
        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        return new SnakeNode(x, y, direction);
    }

    public void addHead(SnakeNode head) {
        body.add(head);
    }

    public static SnakeShapeBuilder builder(int i, int j) {
        return builder(new SnakeNode(i, j, null));
    }

    public static SnakeShapeBuilder builder(SnakeNode head) {
        return new SnakeShapeBuilder(head);
    }

    public LinkedList<SnakeNode> getBody() {
        return body;
    }

    public void setBody(LinkedList<SnakeNode> body) {
        this.body = body;
    }

    public SnakeNode getHead() {
        return body.getLast();
    }

    public void turn(Direction newDirection) {
        SnakeNode head = body.getLast();
        head.setDirection(newDirection);
    }

    public int getLength() {
        return body.size();
    }

    public boolean isCover(SnakeNode nextNode) {
        for (SnakeNode node : body) {
            if (nextNode.isSamePosition(node)) {
                return true;
            }
        }
        return false;
    }

    public SnakeNode getLastTail() {
        return lastTail;
    }

    public static class SnakeShapeBuilder {
        private SnakeShape shape;

        public SnakeShapeBuilder(SnakeNode head) {
            shape = new SnakeShape();
            LinkedList<SnakeNode> body = new LinkedList<>();
            body.add(head);
            shape.setBody(body);
        }

        public SnakeShape build() {
            return shape;
        }

        public SnakeShapeBuilder left() {
            shape.turn(Direction.LEFT);
            shape.addHead(shape.getForwardNode());
            return this;
        }

        public SnakeShapeBuilder right() {
            shape.turn(Direction.RIGHT);
            shape.addHead(shape.getForwardNode());
            return this;
        }

        public SnakeShapeBuilder up() {
            shape.turn(Direction.UP);
            shape.addHead(shape.getForwardNode());
            return this;
        }

        public SnakeShapeBuilder down() {
            shape.turn(Direction.DOWN);
            shape.addHead(shape.getForwardNode());
            return this;
        }
    }
}
