package dao;

import domain.game.TeamColor;
import java.sql.Connection;

public interface GameDao {
    int addGame(Connection connection);

    TeamColor findTurn(Connection connection, int gameId);

    void updateTurn(Connection connection, int gameId, TeamColor teamColor);
}
