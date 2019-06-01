package sample;

import cn.lemon.pojo.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    private Snake snake = new Snake();
    final Canvas canvas = new Canvas(400, 400);
    Point food = new Point(0, 0, PointType.FOOD);
    Timer timer = new Timer();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("贪吃蛇程序");
        Group root = new Group();
        randomFoodPos(400, 400);
        Point old_head = snake.getBody().get(0);
        Point new_head = new Point(old_head.getX(), old_head.getY(), PointType.SNAKE);

        update(canvas);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCharacter().toString()) {
                    case "w":
                        snake.move(Direction.UP);
                        break;
                    case "s":
                        snake.move(Direction.DOWN);
                        break;
                    case "a":
                        snake.move(Direction.LEFT);
                        break;
                    case "d":
                        snake.move(Direction.RIGHT);
                        break;
                }
            }
        });
        MyTimerTask myTask = new MyTimerTask();
        timer.schedule(myTask, 0, 1 * 500);
        primaryStage.show();
    }

    private void update(Canvas canvas) {
        if (snake.isSelf()) {
            timer.cancel();
            return;
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        List<Point> body = snake.getBody();
        if (snake.isEatFood(food)) {
            Point p = new Point(food.getX(), food.getY(), PointType.SNAKE);
            body.add(0, p);
            snake.setBody(body);
            randomFoodPos(400, 400);
        }
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        body.remove(body.size() - 1);
        Point old_head = body.get(0);
        Point new_head = new Point(old_head.getX(), old_head.getY(), PointType.SNAKE);
        switch (snake.getDirection()) {
            case UP:
                new_head.setY(new_head.getY() - 10);
                break;
            case DOWN:
                new_head.setY(new_head.getY() + 10);
                break;
            case LEFT:
                new_head.setX(new_head.getX() - 10);
                break;
            case RIGHT:
                new_head.setX(new_head.getX() + 10);
                break;
        }
        body.add(0, new_head);
        for (int i = 0; i < body.size(); i++) {
            Point p = body.get(i);
            gc.fillRect(p.getX(), p.getY(), 10, 10);
        }
        gc.setFill(Color.RED);
        gc.fillRect(food.getX(), food.getY(), 10, 10);
    }

    public static void main(String[] args) {
        launch(args);
    }

    class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            snake.move(snake.getDirection());
            update(canvas);
        }
    }

    public void randomFoodPos(int width, int height) {
        Random random = new Random();
        int x = random.nextInt(width / 10) * 10;
        int y = random.nextInt(height / 10) * 10;
        food.setX(x);
        food.setY(y);
    }
}
