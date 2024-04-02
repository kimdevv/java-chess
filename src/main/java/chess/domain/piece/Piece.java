package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;

public interface Piece {
    Piece move(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeamExist);

    ChessPiece createNewPiece(PieceInfo newPieceInfo);

    boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist, boolean isSameTeam);

    PieceType getType();

    PieceInfo getPieceInfo();

    Team getTeam();

    boolean isSameTeam(Team otherTeam);

    Position getPosition();

    double getScore();
}
