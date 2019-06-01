package cn.lemon.pojo;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    // 蛇身
    private List<Point> body = new LinkedList<Point>();
    // 移动方向
    private Direction direction = Direction.RIGHT;
    // 移动速度
    private float speed = 1.0f;

    public Snake() {
        body.add(new Point(50, 50, PointType.SNAKE));
        body.add(new Point(40, 50, PointType.SNAKE));
    }


    // 移动
    public void move(Direction direction) {
        if ((direction.ordinal() + this.direction.ordinal()) % 2 == 0
                && direction != this.direction) {
            return;
        }
        this.direction = direction;
    }

    // 判断蛇是否吃到食物
    public boolean isEatFood(Point p) {
        int x = body.get(0).getX();
        int y = body.get(0).getY();
        if (p.getPointType() == PointType.FOOD && x == p.getX() && y == p.getY()) {
            return true;
        }
        return false;
    }

    // 判断蛇是否与异物碰撞
    public boolean isSelf() {
        Point head = body.get(0);
        for (int i = 1; i < body.size(); i++) {
            Point p = body.get(i);
            if (head.getX() == p.getX() && head.getY() == p.getY()) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getBody() {
        return body;
    }

    public void setBody(List<Point> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getLength() {
        return this.body.size();
    }

}
