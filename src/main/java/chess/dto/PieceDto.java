package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.Type;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;

public record PieceDto(String file, String rank, String team, String type) {

    public static PieceDto of(final Point point, final Piece piece) {
        File file = point.getFile();
        Rank rank = point.getRank();
        Team team = piece.getTeam();
        Type type = piece.getType();

        return new PieceDto(
                file.name(),
                String.valueOf(rank.getPosition()),
                team.name(),
                type.name());
    }

    public Point getPoint() {
        return Point.of(File.valueOf(file).getPosition(), Integer.parseInt(rank));
    }

    public Piece getPiece() {
        final Team team = Team.valueOf(this.team);
        final Type type = Type.valueOf(this.type);

        return type.getPiece(team);
    }
}
