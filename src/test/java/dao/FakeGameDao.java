package dao;

import domain.game.TeamColor;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class FakeGameDao implements GameDao {
    private static final Map<Integer, TeamColor> data = new HashMap<>();

    static {
        data.put(1, TeamColor.BLACK);
        data.put(2, TeamColor.WHITE);
    }

    @Override
    public int addGame(Connection notUsed) {
        int generatedKey = data.size() + 1;
        data.put(generatedKey, TeamColor.WHITE);
        return generatedKey;
    }

    @Override
    public TeamColor findTurn(Connection notUsed, int gameId) {
        return data.get(gameId);
    }

    @Override
    public void updateTurn(Connection notUsed, int gameId, TeamColor teamColor) {
        data.put(gameId, teamColor);
    }
}
