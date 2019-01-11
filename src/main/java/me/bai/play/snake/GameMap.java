package me.bai.play.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Bai
 * @date 2017年9月21日 下午12:50:51
 */
public class GameMap extends JFrame implements KeyListener {
    private Snake snake;
    private Food food;
    private Game game;

    private int offset;
    private int gridWidth;
    private int gridNum;
    private Graphics jg;
    private Map<Integer, Direction> keyMap = new HashMap<>();

    public GameMap(Game game) {
        this.game = game;
        gridNum = Constants.MAP_GRID_NUM;
        offset = Constants.MAP_SIDE_OFFSET;
        gridWidth = Constants.MAP_GRID_WIDTH;
        int desktopOffsetX = Constants.DESKTOP_OFFSET_X;
        int desktopOffsetY = Constants.DESKTOP_OFFSET_Y;
        Container p = getContentPane();
        setBounds(desktopOffsetX, desktopOffsetY, gridWidth * gridNum + offset * 2,
            gridWidth * gridNum + offset * 2);
        setVisible(true);
        //p.setBackground(new Color(0xf5f5f5));
        setLayout(null);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取专门用于在窗口界面上绘图的对象
        jg = this.getGraphics();
        // 设置颜色
        jg.setColor(Color.BLACK);
        // 绘制外层矩形框，减1是避免在边上走一圈把边框抹掉
        jg.drawRect(offset - 1, offset - 1, gridWidth * gridNum + 1, gridWidth * gridNum + 1);

        addKeyListener(this);// 加入key监听

        // 设置方向键
        keyMap.put(37, Direction.LEFT);// ←
        keyMap.put(38, Direction.UP);// ↑
        keyMap.put(39, Direction.RIGHT);// →
        keyMap.put(40, Direction.DOWN);// ←

        keyMap.put(87, Direction.UP);// w
        keyMap.put(68, Direction.RIGHT);// d
        keyMap.put(65, Direction.LEFT);// a
        keyMap.put(83, Direction.DOWN);// s
    }

    public void init() {
        jg.clearRect(offset, offset, gridWidth * gridNum, gridWidth * gridNum);
        LinkedList<SnakeNode> body = snake.getShape().getBody();
        for (SnakeNode joint : body) {
            fillColor(joint.getX(), joint.getY());
        }
    }

    public void updateScreen() {
        drawFood();
        drawSnake();
    }

    private void drawSnake() {
        SnakeNode forwardNode = snake.getShape().getBody().getLast();
        fillColor(forwardNode.getX(), forwardNode.getY());
        SnakeNode lastTail = snake.getShape().getLastTail();
        if (lastTail != null) {
            clearColor(lastTail.getX(), lastTail.getY());
        }
    }

    private void drawFood() {
        if (food.getStatus() != Food.FOOD_STATUS_EATEN) {
            int x = food.getX();
            int y = food.getY();
            jg.fillRoundRect(offset + (x * gridWidth), offset + (y * gridWidth), gridWidth, gridWidth, gridWidth,
                gridWidth);
        }
    }

    private void fillColor(int x, int y) {
        jg.fillRect(offset + (x * gridWidth), offset + (y * gridWidth), gridWidth, gridWidth);
    }

    private void clearColor(int x, int y) {
        jg.clearRect(offset + (x * gridWidth), offset + (y * gridWidth), gridWidth, gridWidth);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        Direction direction = keyMap.get(keyCode);
        if (direction != null) {
            if (game.isPause()) {
                game.setPause(false);
            }
            snake.tryTurn(direction);
        } else if (keyCode == 32) {
            game.setPause(!game.isPause());
        } else if (keyCode == 82) {
            game.init();
        } else if (keyCode == 33) {
            int currentSpeed = game.getSpeed();
            game.setSpeed(currentSpeed<<1);
        } else if (keyCode == 34) {
            int currentSpeed = game.getSpeed();
            int newSpeed = currentSpeed >> 1;
            if (newSpeed == 0) {
                newSpeed = 1;
            }
            game.setSpeed(newSpeed);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

}
