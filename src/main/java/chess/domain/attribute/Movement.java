package chess.domain.attribute;

public enum Movement {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    LEFT_UP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(1, -1),
    UP_UP(0, 2),
    DOWN_DOWN(0, -2),
    UP_UP_LEFT(-1, 2),
    UP_UP_RIGHT(1, 2),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),
    ;

    private final int x;
    private final int y;

    Movement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
