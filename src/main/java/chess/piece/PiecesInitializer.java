package chess.piece;

import java.util.ArrayList;
import java.util.List;

public class PiecesInitializer {

    public static List<Piece> initializeBlack() {
        List<Piece> blackPieces = new ArrayList<>();
        blackPieces.add(new King(DefaultPosition.KING_BLACK.getPosition()));
        blackPieces.add(new Queen(DefaultPosition.QUEEN_BLACK.getPosition()));
        blackPieces.add(new Bishop(DefaultPosition.BISHOP_LEFT_BLACK.getPosition()));
        blackPieces.add(new Bishop(DefaultPosition.BISHOP_RIGHT_BLACK.getPosition()));
        blackPieces.add(new Knight(DefaultPosition.KNIGHT_LEFT_BLACK.getPosition()));
        blackPieces.add(new Knight(DefaultPosition.KNIGHT_RIGHT_BLACK.getPosition()));
        blackPieces.add(new Rook(DefaultPosition.ROOK_LEFT_BLACK.getPosition()));
        blackPieces.add(new Rook(DefaultPosition.ROOK_RIGHT_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_FIRST_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_SECOND_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_THIRD_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_FOURTH_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_FIFTH_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_SIXTH_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_SEVENTH_BLACK.getPosition()));
        blackPieces.add(new Pawn(DefaultPosition.PAWN_EIGHTH_BLACK.getPosition()));
        return blackPieces;
    }

    public static List<Piece> initializeWhite() {
        List<Piece> whitePieces = new ArrayList<>();
        whitePieces.add(new King(DefaultPosition.KING_WHITE.getPosition()));
        whitePieces.add(new Queen(DefaultPosition.QUEEN_WHITE.getPosition()));
        whitePieces.add(new Bishop(DefaultPosition.BISHOP_LEFT_WHITE.getPosition()));
        whitePieces.add(new Bishop(DefaultPosition.BISHOP_RIGHT_WHITE.getPosition()));
        whitePieces.add(new Knight(DefaultPosition.KNIGHT_LEFT_WHITE.getPosition()));
        whitePieces.add(new Knight(DefaultPosition.KNIGHT_RIGHT_WHITE.getPosition()));
        whitePieces.add(new Rook(DefaultPosition.ROOK_LEFT_WHITE.getPosition()));
        whitePieces.add(new Rook(DefaultPosition.ROOK_RIGHT_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_FIRST_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_SECOND_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_THIRD_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_FOURTH_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_FIFTH_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_SIXTH_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_SEVENTH_WHITE.getPosition()));
        whitePieces.add(new Pawn(DefaultPosition.PAWN_EIGHTH_WHITE.getPosition()));
        return whitePieces;
    }
}
