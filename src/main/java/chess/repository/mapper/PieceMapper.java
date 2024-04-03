package chess.repository.mapper;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;

public class PieceMapper {

    public static String mapPieceTypeToValue(Piece piece) {
        if (piece instanceof Pawn) {
            return "pawn";
        }
        if (piece instanceof Rook) {
            return "rook";
        }
        if (piece instanceof Knight) {
            return "knight";
        }
        if (piece instanceof Bishop) {
            return "bishop";
        }
        if (piece instanceof Queen) {
            return "queen";
        }
        if (piece instanceof King) {
            return "king";
        }
        throw new IllegalArgumentException("알 수 없는 피스 타입입니다");
    }

    public static String mapPieceTeamToValue(Piece piece) {
        return piece.getTeam().name();
    }

    public static Piece mapValueToPiece(String pieceTypeValue, String teamValue) {
        Team team = mapValueToTeam(teamValue);
        if ("pawn".equals(pieceTypeValue)) {
            return new Pawn(team);
        }
        if ("knight".equals(pieceTypeValue)) {
            return new Knight(team);
        }
        if ("bishop".equals(pieceTypeValue)) {
            return new Bishop(team);
        }
        if ("rook".equals(pieceTypeValue)) {
            return new Rook(team);
        }
        if ("queen".equals(pieceTypeValue)) {
            return new Queen(team);
        }
        if ("king".equals(pieceTypeValue)) {
            return new King(team);
        }
        throw new IllegalArgumentException("값을 Piece로 매핑할 수 없습니다 - " + pieceTypeValue + " " + teamValue);
    }

    private static Team mapValueToTeam(String value) {
        if (value.equals("WHITE")) {
            return Team.WHITE;
        }
        return Team.BLACK;
    }
}
