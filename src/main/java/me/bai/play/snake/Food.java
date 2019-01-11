package me.bai.play.snake;

/**
 * @author Bai
 * @date 2017年9月21日 下午5:45:25
 */
public class Food extends MapPoint {

    private int status;
    public static final int FOOD_STATUS_EXIST = 0;
    public static final int FOOD_STATUS_EATEN = 1;

    public Food(int x, int y) {
        setX(x);
        setY(y);
        status = FOOD_STATUS_EXIST;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
