package chess.service;

public interface GameDao {

    void save(String board, String boardStatus);

    BoardData find();
}
