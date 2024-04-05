package chess.service;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardStatus;
import chess.domain.game.GameCreator;
import chess.domain.game.InitialGameCreator;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameServiceTest {

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService(new GameDao() {
            @Override
            public void save(String board, String boardStatus) {
            }

            @Override
            public BoardData find() {
                String pieces = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
                String status = "WHITE_TURN";
                return new BoardData(pieces, status);
            }
        });
    }

    @DisplayName("주어진 데이터로 보드를 생성한다.")
    @Test
    void loadBoard() {
        GameCreator gameCreator = gameService.load();
        Map<Position, Piece> board = gameCreator.createBoard();

        assertThat(board).isEqualTo(new InitialGameCreator().createBoard());
    }

    @DisplayName("주어진 데이터로 보드 상태를 생성한다.")
    @Test
    void loadStatus() {
        GameCreator gameCreator = gameService.load();
        BoardStatus boardStatus = gameCreator.createBoardStatus();

        assertThat(boardStatus).isEqualTo(BoardStatus.WHITE_TURN);
    }
}
