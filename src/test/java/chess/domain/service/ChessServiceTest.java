package chess.domain.service;

import chess.domain.board.Board;
import chess.domain.dao.FakeChessRoomDao;
import chess.domain.dao.FakePieceDao;
import chess.domain.pieceinfo.Team;
import chess.service.ChessService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessServiceTest {
    private static final long CHESS_ROOM_ID = 1;

    private final ChessService chessService =
            new ChessService(CHESS_ROOM_ID, new FakePieceDao(), new FakeChessRoomDao());

    @DisplayName("체스 게임을 초기화하면, 처음 턴은 WHITE이다.")
    @Test
    void initializeChessAndLoadTurnTest() {
        chessService.initializeChess();

        Team actualTurn = chessService.loadTurn();
        Team expectedTurn = Team.WHITE;

        Assertions.assertThat(actualTurn).isEqualTo(expectedTurn);
    }

    @DisplayName("체스 게임을 초기화한 뒤, 불러온 모든 말들의 점수 합계는 38점이다.")
    @Test
    void initializeChessAndLoadPiecesTest() {
        chessService.initializeChess();
        Board board = chessService.loadPieces();

        double actualScore = board.calculatePiecesScoreSum(Team.WHITE);
        double expectedScore = 38;

        Assertions.assertThat(actualScore).isEqualTo(expectedScore);
    }
}
