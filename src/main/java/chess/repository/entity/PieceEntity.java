package chess.repository.entity;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.mapper.PieceMapper;
import chess.repository.mapper.PositionMapper;

public class PieceEntity {
    private final String position;
    private final String team;
    private final String type;

    public PieceEntity(String position, String team, String type) {
        this.position = position;
        this.team = team;
        this.type = type;
    }

    public static PieceEntity of(Piece piece, Position position) {
        return new PieceEntity(
                PositionMapper.mapPositionToValue(position),
                PieceMapper.mapPieceTeamToValue(piece),
                PieceMapper.mapPieceTypeToValue(piece));
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return team;
    }

    public String getType() {
        return type;
    }
}
