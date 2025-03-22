package chess.piece;

import chess.position.Position;

import static chess.position.Fixtures.*;

public enum DefaultPosition {

    KING_BLACK(E8.getPosition()),
    QUEEN_BLACK(D8.getPosition()),
    BISHOP_LEFT_BLACK(C8.getPosition()),
    BISHOP_RIGHT_BLACK(F8.getPosition()),
    KNIGHT_LEFT_BLACK(B8.getPosition()),
    KNIGHT_RIGHT_BLACK(G8.getPosition()),
    ROOK_LEFT_BLACK(A8.getPosition()),
    ROOK_RIGHT_BLACK(H8.getPosition()),
    PAWN_FIRST_BLACK(A7.getPosition()),
    PAWN_SECOND_BLACK(B7.getPosition()),
    PAWN_THIRD_BLACK(C7.getPosition()),
    PAWN_FOURTH_BLACK(D7.getPosition()),
    PAWN_FIFTH_BLACK(E7.getPosition()),
    PAWN_SIXTH_BLACK(F7.getPosition()),
    PAWN_SEVENTH_BLACK(G7.getPosition()),
    PAWN_EIGHTH_BLACK(H7.getPosition()),

    KING_WHITE(E1.getPosition()),
    QUEEN_WHITE(D1.getPosition()),
    BISHOP_LEFT_WHITE(C1.getPosition()),
    BISHOP_RIGHT_WHITE(F1.getPosition()),
    KNIGHT_LEFT_WHITE(B1.getPosition()),
    KNIGHT_RIGHT_WHITE(G1.getPosition()),
    ROOK_LEFT_WHITE(A1.getPosition()),
    ROOK_RIGHT_WHITE(H1.getPosition()),
    PAWN_FIRST_WHITE(A2.getPosition()),
    PAWN_SECOND_WHITE(B2.getPosition()),
    PAWN_THIRD_WHITE(C2.getPosition()),
    PAWN_FOURTH_WHITE(D2.getPosition()),
    PAWN_FIFTH_WHITE(E2.getPosition()),
    PAWN_SIXTH_WHITE(F2.getPosition()),
    PAWN_SEVENTH_WHITE(G2.getPosition()),
    PAWN_EIGHTH_WHITE(H2.getPosition());

    private final Position position;

    DefaultPosition(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
