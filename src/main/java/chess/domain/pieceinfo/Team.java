package chess.domain.pieceinfo;

public enum Team {
    BLACK("BLACK"),
    WHITE("WHITE"),
    NONE("NONE");

    private final String team;

    Team(String team) {
        this.team = team;
    }

    public static Team takeTurn(Team team) {
        if (team == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public String getRawTeam() {
        return team;
    }
}
