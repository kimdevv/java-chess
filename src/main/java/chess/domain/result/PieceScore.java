package chess.domain.result;

import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceScore {
    
    KING(PieceType.KING, new Score(0)),
    QUEEN(PieceType.QUEEN, new Score(9)),
    KNIGHT(PieceType.KNIGHT, new Score(2.5)),
    BISHOP(PieceType.BISHOP, new Score(3)),
    ROOK(PieceType.ROOK, new Score(5)),
    WHITE_PAWN(PieceType.WHITE_PAWN, new Score(1)),
    BLACK_PAWN(PieceType.BLACK_PAWN, new Score(1)),
    ;

    private final PieceType pieceType;
    private final Score score;

    PieceScore(final PieceType pieceType, final Score score) {
        this.pieceType = pieceType;
        this.score = score;
    }

    public static PieceScore getPieceScore(final PieceType pieceType) {
        return Arrays.stream(PieceScore.values()).filter(pieceScore -> pieceScore.pieceType == pieceType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("점수계산에 사용할 수 없는 체스 말 타입입니다."));
    }

    public Score getScore() {
        return score;
    }
}
