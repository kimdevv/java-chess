package service;

import dao.ChessGameDao;
import domain.Side;
import domain.Turn;
import domain.board.ChessBoardInitializer;
import domain.piece.Piece;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessGameServiceTest {

    ChessGameService chessGameService;

    @BeforeEach
    void setUp() {
        chessGameService = new ChessGameService("roomName", new ChessGameDao() {
            @Override
            public void saveData(String roomName, String boardData, String turnData) {
            }

            @Override
            public void deleteBoard(String roomName) {
            }

            @Override
            public String dataToBoard(String roomName) {
                return "a8R,h8R,b8N,g8N,c8B,f8B,d8Q,e8K,a7P,b7P,c7P,d7P,e7P,f7P,g7P,h7P,a1r,h1r,b1n,g1n,c1b,f1b,d1q,e1k,a2p,b2p,c2p,d2p,e2p,f2p,g2p,h2p";
            }

            @Override
            public String dataToTurn(String roomName) {
                return "WHITE";
            }
        });
    }

    @DisplayName("저장된 데이터를 보드로 가져온다")
    @Test
    void loadBoard() {
        Map<Position, Piece> board = chessGameService.loadBoard();
        assertThat(board).isEqualTo(ChessBoardInitializer.init());
    }

    @DisplayName("저장된 턴 정보를 턴으로 가져온다")
    @Test
    void loadTurn() {
        Turn turn = chessGameService.loadTurn();
        assertThat(turn).isEqualTo(new Turn(Side.WHITE));
    }
}
