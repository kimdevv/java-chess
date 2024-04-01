package chess.service;

import static chess.domain.Fixtures.A1;
import static chess.domain.Fixtures.B2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.dao.ChessDAO;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.dto.ChessDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

    @Test
    @DisplayName("ChessDAO를 통해 진행중인 게임을 불러온다")
    void Given_ChessServiceWithTestChessDAO_When_LoadChess_Then_ReturnBoardWithEmptyPieceIfNotExistPiece() {
        //given
        ChessService chessService = new ChessService(new ChessDAO() {
            @Override
            public Map<Position, Piece> getBoard() {
                return Map.of(A1, Piece.of(PieceType.KING, Color.WHITE), B2, Piece.of(PieceType.KING, Color.BLACK));
            }

            @Override
            public Color getCurrentTurnColor() {
                return Color.BLACK;
            }

            @Override
            public void updateBoard(Board board) {
            }

            @Override
            public void updateColor(Color color) {
            }

            @Override
            public void initialize() {
            }
        });
        //when
        ChessDTO chessDTO = chessService.loadChess();
        Board board = chessDTO.board();
        Color color = chessDTO.color();
        //then
        assertAll(
                () -> assertThat(board.getBoard()).containsAllEntriesOf(
                        Map.of(B2, Piece.of(PieceType.KING, Color.BLACK), A1, Piece.of(PieceType.KING, Color.WHITE))),
                () -> Arrays.stream(File.values())
                        .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(file, rank)))
                        .filter(position -> !List.of(B2, A1).contains(position))
                        .forEach(position -> assertThat(board.getBoard().get(position)).isEqualTo(Piece.EMPTY_PIECE)),
                () -> assertThat(color).isEqualTo(Color.BLACK)
        );
    }

    @Test
    @DisplayName("진행 중인 체스 게임을 불러올 때 예외가 발생할 경우 RuntimeException을 발생시킨다.")
    void Given_ChessServiceWithTestChessDAO_When_LoadBoardException_Then_RuntimeException() {
        //given
        ChessService chessService = new ChessService(new ChessDAO() {
            @Override
            public Map<Position, Piece> getBoard() {
                throw new RuntimeException("외부에서 자료를 불러오는데 오류가 발생했습니다");
            }

            @Override
            public Color getCurrentTurnColor() {
                throw new RuntimeException("외부에서 현재 턴의 색상을 가져오는데 실패했습니다.");
            }

            @Override
            public void updateBoard(Board board) {
            }

            @Override
            public void updateColor(Color color) {
            }

            @Override
            public void initialize() {
            }
        });
        assertThatThrownBy(chessService::loadChess).isInstanceOf(RuntimeException.class);
    }
}
