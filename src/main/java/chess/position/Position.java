package chess.position;

public record Position(
        Column column,
        Row row
) {
    public Position(final Row row, final Column column) {
        this(column, row);
    }

    public Position moveVertical(final int step) {
        return new Position(row.move(step), column);
    }

    public Position moveHorizontal(final int step) {
        return new Position(row, column.move(step));
    }

    public Position moveDiagonal(final int rowStep, final int columnStep) {
        return new Position(row.move(rowStep), column.move(columnStep));
    }

    public int calculateRowDifference(final Position position) {
        return row.calculateDifference(position.row());
    }

    public int calculateColumnDifference(final Position position) {
        return column.calculateDifference(position.column());
    }
}
