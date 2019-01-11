package me.bai.play.snake;

/**
 * @author Bai
 * @date 2017年9月21日 下午12:36:27
 */
public class Game implements Runnable {
    private Snake snake;
    private GameMap gameMap;
    private int speed;
    private boolean pause;

    public Game() {
        setSpeed(Constants.SNAKE_SPEED);
        gameMap = new GameMap(this);
        init();
    }

    public void init() {
        snake = new Snake();
        SnakeShape shape = SnakeShape.builder(0, 0).right().right().right().build();
        snake.setShape(shape);
        gameMap.setSnake(snake);
        gameMap.init();
        Food food = createFood();
        gameMap.setFood(food);
        gameMap.updateScreen();
        pause = true;
    }

    public Food createFood() {
        int size = Constants.MAP_GRID_NUM;
        int space = size * size - snake.getLength();
        int foodIndex = (int)(Math.random() * space);
        boolean[][] haveSnakeBody = new boolean[size][size];
        for (SnakeNode node : snake.getShape().getBody()) {
            haveSnakeBody[node.getX()][node.getY()] = true;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!haveSnakeBody[i][j]) {
                    foodIndex--;
                }
                if (foodIndex < 0) {
                    // todo 想要多个food怎么办？需要变成list
                    return new Food(i, j);
                }
            }
        }
        return null;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 / speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (snake.getError() == null && !pause) {
                boolean justMove = snake.tryMove();
                if (justMove) {
                    Food food = gameMap.getFood();
                    if (snake.isHeadOnFood(food)) {
                        food.setStatus(Food.FOOD_STATUS_EATEN);
                        snake.eat(food);
                        food = createFood();
                        gameMap.setFood(food);
                    }
                    gameMap.updateScreen();
                }
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
