package org.aoeu.jgames.snake;

/**
 * @author Luis Martín Canaval Sánchez
 */
public class Snake {

    enum Direction {LEFT, RIGHT, UP, DOWN}

    int[] snakeX;
    int[] snakeY;
    int appleX;
    int appleY;
    int worldWidth;
    int worldHeight;
    int maxBodyLength;
    int bodyLength;
    int start;
    boolean isDead;
    Direction direction;

    Snake(int worldWidth, int worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        maxBodyLength = worldHeight * worldWidth;
        snakeX = new int[maxBodyLength];
        snakeY = new int[maxBodyLength];
    }

    void reset() {
        bodyLength = 3;
        start = 0;
        snakeX[0] = 1;
        snakeY[0] = 1;
        snakeX[1] = 2;
        snakeY[1] = 1;
        snakeX[2] = 3;
        snakeY[2] = 1;
        direction = Direction.RIGHT;
        isDead = false;
        newApple();
    }

    boolean step() {
        int head = start + bodyLength;
        int neck = start + bodyLength - 1;
        switch (direction) {
            case LEFT:
                snakeX[head] = snakeX[neck] - 1;
                snakeY[head] = snakeY[neck];
                break;
            case DOWN:
                snakeX[head] = snakeX[neck];
                snakeY[head] = snakeY[neck] + 1;
                break;
            case RIGHT:
                snakeX[head] = snakeX[neck] + 1;
                snakeY[head] = snakeY[neck];
                break;
            case UP:
                snakeX[head] = snakeX[neck];
                snakeY[head] = snakeY[neck] - 1;
                break;
        }
        if (snakeX[head] == appleX && snakeY[head] == appleY) {
            newApple();
            bodyLength++;
        } else {
            start++;
        }
        return isDead;
    }

    void newApple() {
        appleX = (int) (Math.random() * worldWidth);
        appleY = (int) (Math.random() * worldHeight);
    }

    void goLeft() {
        direction = direction != Direction.RIGHT ? Direction.LEFT : direction;
    }

    void goRight() {
        direction = direction != Direction.LEFT ? Direction.RIGHT : direction;
    }

    void goUp() {
        direction = direction != Direction.DOWN ? Direction.UP : direction;
    }

    void goDown() {
        direction = direction != Direction.UP ? Direction.DOWN : direction;
    }

    public int getX(int i) {
        return snakeX[start + i % maxBodyLength];
    }

    public int getY(int i) {
        return snakeY[start + i % maxBodyLength];
    }

}
