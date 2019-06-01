package cn.lemon.pojo;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SnakeTest {
    // 测试蛇的存活状态
    @Test
    public void testIsAlive() {
        Snake snake = new Snake();
        // 死亡：蛇未初始化或碰撞
        Assert.assertEquals(snake.getLength(), 0);
    }

    // 测试蛇身的初始化
    @Test
    public void testSnakeBodyInitialize() {
        Snake snake = new Snake();
        List<Point> body = new LinkedList<Point>();
        for (int i = 0; i < 8; i++) {
            Point p = new Point(0, i, PointType.SNAKE);
            body.add(p);
        }
        snake.setBody(body);
        Assert.assertEquals(snake.getLength(), 8);
    }

    // 测试蛇的移动
    @Test
    public void testSnakeMove() {
        Snake snake = new Snake();
        List<Point> body = new LinkedList<Point>();
        body.add(new Point(50, 50, PointType.SNAKE));
        body.add(new Point(40, 50, PointType.SNAKE));
        snake.setDirection(Direction.RIGHT);
        snake.setBody(body);

        System.out.println("origin head: "  + snake.getBody().get(0));
        snake.move(Direction.LEFT);
        System.out.println("move left: " + snake.getBody().get(0));
        snake.move(Direction.RIGHT);
        System.out.println("move right: " + snake.getBody().get(0));
        snake.move(Direction.UP);
        System.out.println("move up: " + snake.getBody().get(0));

        snake.move(Direction.DOWN);
        System.out.println("move down: " + snake.getBody().get(0));
    }

    // 测试蛇改变方向
    @Test
    public void testSnakeChangeDirection() {
        Snake snake = new Snake();
        snake.setDirection(Direction.UP);
        Assert.assertEquals(Direction.UP, snake.getDirection());
    }

    // 测试蛇的加速

    // 测试是否吃到果实
}
