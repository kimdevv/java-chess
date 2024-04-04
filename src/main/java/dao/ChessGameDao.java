package dao;

public interface ChessGameDao {

    void saveData(String roomName, String boardData, String turnData);

    void deleteBoard(String roomName);

    String dataToBoard(String roomName);

    String dataToTurn(String roomName);
}
