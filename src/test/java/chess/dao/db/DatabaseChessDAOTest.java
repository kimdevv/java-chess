package chess.dao.db;

import static chess.domain.Fixtures.B8;
import static chess.domain.Fixtures.F2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.ChessDAO;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DatabaseChessDAOTest {
    private static final ChessDAO DATABASE_CHESS_DAO = new DatabaseChessDAO(MySQLConfiguration.getConnection());

    @BeforeEach
    @AfterEach
    void setUp() {
        DATABASE_CHESS_DAO.initialize();
    }

    @Test
    @DisplayName("체스 게임을 초기화 한 뒤 보드를 불러올 경우 예외가 발생한다.")
    void Given_DatabaseChessDAO_When_initializeAndGetBoard_Then_Exception() {
        //given, when, then
        assertAll(
                () -> assertThatThrownBy(DATABASE_CHESS_DAO::getBoard).isInstanceOf(RuntimeException.class)
                        .hasMessage("DB에 기존 게임에 대한 정보가 존재하지 않습니다."),
                () -> assertThatThrownBy(DATABASE_CHESS_DAO::getCurrentTurnColor).isInstanceOf(RuntimeException.class)
                        .hasMessage("DB에 기존 게임에 대한 정보가 존재하지 않습니다.")
        );
    }

    @Test
    @DisplayName("저장한 보드를 다시 불러올 수 있다.")
    void Given_DatabaseChessDAOWithBoard_When_UpdateBoardAndGetBoard_Then_SameBoard() {
        //given
        Board board = new Board(
                Map.of(B8, Piece.of(PieceType.KING, Color.BLACK), F2, Piece.of(PieceType.KING, Color.WHITE)));
        //when
        DATABASE_CHESS_DAO.updateBoard(board);
        Map<Position, Piece> actualBoard = DATABASE_CHESS_DAO.getBoard();
        //then
        assertThat(actualBoard).isEqualTo(board.getBoard());
    }

    @Test
    @DisplayName("저장한 컬러를 다시 불러올 수 있다.")
    void Given_DatabaseChessDAOWithColor_When_UpdateColorAndGetColor_Then_SameColor() {
        //given
        Color color = Color.BLACK;
        //when
        DATABASE_CHESS_DAO.updateColor(color);
        Color actualColor = DATABASE_CHESS_DAO.getCurrentTurnColor();
        //then
        assertThat(actualColor).isEqualTo(color);
    }
}
