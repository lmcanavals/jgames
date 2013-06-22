package org.aoeu.jgames.snake;

/**
 * @author Luis Martín Canaval Sánchez
 */
class Snake {

    private enum Direction {LEFT, RIGHT, UP, DOWN}

    private int[] snakeX;
    private int[] snakeY;
    private int appleX;
    private int appleY;
    private int worldWidth;
    private int worldHeight;
    private int maxBodyLength;
    private int bodyLength;
    private int start;
    private boolean dead;
    private Direction direction;

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
        dead = false;
        newApple();
    }

    boolean step() {
        int head = start + bodyLength;
        int neck = start + bodyLength - 1;
        int newX, newY;
        switch (direction) {
            case LEFT:
                newX = snakeX[neck] - 1;
                snakeX[head] = newX < 0 ? worldWidth - 1 : newX;
                snakeY[head] = snakeY[neck];
                break;
            case DOWN:
                snakeX[head] = snakeX[neck];
                newY = snakeY[neck] + 1;
                snakeY[head] = newY >= worldHeight ? 0 : newY;
                break;
            case RIGHT:
                newX = snakeX[neck] + 1;
                snakeX[head] = newX >= worldWidth ? 0 : newX;
                snakeY[head] = snakeY[neck];
                break;
            case UP:
                snakeX[head] = snakeX[neck];
                newY = snakeY[neck] - 1;
                snakeY[head] = newY < 0 ? worldHeight - 1 : newY;
                break;
        }
        if (snakeX[head] == appleX && snakeY[head] == appleY) {
            newApple();
            bodyLength++;
        } else {
            start++;
        }
        return dead;
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

    int getBodyLength() {
        return bodyLength;
    }

    int getWorldWidth() {
        return worldWidth;
    }

    int getWorldHeight() {
        return worldHeight;
    }

    int getAppleX() {
        return appleX;
    }

    int getAppleY() {
        return appleY;
    }

    boolean isDead() {
        return dead;
    }

    int getX(int i) {
        return snakeX[start + i % maxBodyLength];
    }

    int getY(int i) {
        return snakeY[start + i % maxBodyLength];
    }

}
