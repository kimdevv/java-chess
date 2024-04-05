package chess.service;

import chess.domain.board.BoardStatus;
import java.util.Arrays;

public enum BoardStatusConverter {

    BLACK_TURN(BoardStatus.BLACK_TURN),
    WHITE_TURN(BoardStatus.WHITE_TURN),
    ;

    private final BoardStatus boardStatus;

    BoardStatusConverter(BoardStatus boardStatus) {
        this.boardStatus = boardStatus;
    }

    public static String convertToString(BoardStatus boardStatus) {
        return Arrays.stream(values())
                .filter(it -> it.boardStatus == boardStatus)
                .findAny()
                .map(Enum::name)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 BoardStatus"));
    }

    public static BoardStatus convertToBoardStatus(String string) {
        try {
            return BoardStatus.valueOf(string);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("유효하지 않은 String");
        }
    }
}
