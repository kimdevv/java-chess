package chess.dto;

import chess.domain.piece.Team;

public record TurnDto(String turn) {

    public static TurnDto from(Team team) {
        return new TurnDto(team.name());
    }

    public Team getTurn() {
        return Team.valueOf(turn);
    }
}
