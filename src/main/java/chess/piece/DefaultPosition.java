package chess.piece;

import chess.position.Fixtures;
import chess.position.Position;

import static chess.position.Fixtures.*;

public enum DefaultPosition {

    KING_BLACK(E8),
    QUEEN_BLACK(D8),
    BISHOP_LEFT_BLACK(C8),
    BISHOP_RIGHT_BLACK(F8),
    KNIGHT_LEFT_BLACK(B8),
    KNIGHT_RIGHT_BLACK(G8),
    ROOK_LEFT_BLACK(A8),
    ROOK_RIGHT_BLACK(H8),
    PAWN_FIRST_BLACK(A7),
    PAWN_SECOND_BLACK(B7),
    PAWN_THIRD_BLACK(C7),
    PAWN_FOURTH_BLACK(D7),
    PAWN_FIFTH_BLACK(E7),
    PAWN_SIXTH_BLACK(F7),
    PAWN_SEVENTH_BLACK(G7),
    PAWN_EIGHTH_BLACK(H7),

    KING_WHITE(E1),
    QUEEN_WHITE(D1),
    BISHOP_LEFT_WHITE(C1),
    BISHOP_RIGHT_WHITE(F1),
    KNIGHT_LEFT_WHITE(B1),
    KNIGHT_RIGHT_WHITE(G1),
    ROOK_LEFT_WHITE(A1),
    ROOK_RIGHT_WHITE(H1),
    PAWN_FIRST_WHITE(A2),
    PAWN_SECOND_WHITE(B2),
    PAWN_THIRD_WHITE(C2),
    PAWN_FOURTH_WHITE(D2),
    PAWN_FIFTH_WHITE(E2),
    PAWN_SIXTH_WHITE(F2),
    PAWN_SEVENTH_WHITE(G2),
    PAWN_EIGHTH_WHITE(H2);

    private final Position position;

    DefaultPosition(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
