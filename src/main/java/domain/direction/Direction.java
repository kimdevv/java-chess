package domain.direction;

public interface Direction {

    boolean isSameDirection(int rowDifference, int columnDifference);

    int calculateDistance(int rowDifference, int columnDifference);

    int getRowOffset();

    int getColumnOffset();
}
