package state.chessGame.base;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import java.util.Map;

public interface ChessGameState {

    ChessGameState move(Coordinate start, Coordinate destination);

    ChessGameState end();

    boolean isPlaying();

    boolean isKingCaught();

    Map<Coordinate, ChessPiece> getBoard();

    Long getGameId();

    Color getTurn();

    void show();
}
