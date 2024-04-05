package service;

import db.booleanTranslator.BooleanTranslator;
import db.dao.ChessGameDao;
import db.entity.ChessGame;
import db.entity.dto.ChessGameDto;
import domain.chessboard.ChessBoardInitializer;
import domain.chessboard.ChessBoardScoreCalculator;
import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.room.GameRoom;
import java.sql.SQLException;
import java.util.Map;
import service.dto.GameScoreDto;
import state.chessGame.base.ChessGameState;
import state.chessGame.statusfactory.ChessStatusFactory;

public class ChessGameService {

    private final ChessGameDao chessGameDao;

    public ChessGameService() {
        this.chessGameDao = new ChessGameDao();
    }

    public ChessGameState addChessGame(String roomName) throws SQLException {
        GameRoom gameRoom = new GameRoom(roomName);

        ChessGame chessGame = ChessGame.create(gameRoom, String.valueOf(Color.WHITE));
        Long id = chessGameDao.save(chessGame);

        return ChessStatusFactory.makeRunningChessGame(id, ChessBoardInitializer.createInitialBoard(),
                chessGame.getTurn());
    }

    public ChessGameDto loadChessGame(String roomName) throws SQLException {
        ChessGameDto chessGameDto = chessGameDao.findChessGameByName(roomName);
        validateNotEndGame(chessGameDto.isRunning());
        return chessGameDto;
    }

    public GameScoreDto calculateScore(ChessGameState chessGameState) {
        Map<Coordinate, ChessPiece> board = chessGameState.getBoard();

        double blackScore = ChessBoardScoreCalculator.calculate(Color.BLACK, board);
        double whiteScore = ChessBoardScoreCalculator.calculate(Color.WHITE, board);

        return new GameScoreDto(blackScore, whiteScore);
    }

    public void stopChessGame(ChessGameState chessGameState) throws SQLException {
        chessGameDao.updateChessGameById(chessGameState.getGameId());
    }

    private static void validateNotEndGame(int isRunning) {
        if (isRunning == BooleanTranslator.translate(false)) {
            throw new IllegalArgumentException("이미 종료된 게임입니다.");
        }
    }

    public void switchTurn(ChessGameState chessGameState) throws SQLException {
        chessGameDao.updateChessGameTurnById(chessGameState.getGameId(), chessGameState.getTurn());
    }
}
