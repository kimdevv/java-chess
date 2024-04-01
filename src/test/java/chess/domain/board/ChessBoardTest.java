package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.StartEndPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ChessBoardTest {

    @DisplayName("체스말 이동 예외 테스트")
    @Nested
    class PieceMoveExceptionTest {
        @DisplayName("체스말을 움직일 때, 시작 위치에 아군 말이 존재하지 않는 경우 예외를 발생시킨다.")
        @Test
        void startEmptyExceptionTest() {
            // given
            Map<Position, Piece> pieces = provideEmptyBoard();
            ChessBoard chessBoard = new ChessBoard(pieces);
            StartEndPosition startEndPosition =
                    new StartEndPosition(new Position(File.A, Rank.FIRST), new Position(File.B, Rank.SECOND));
            Color currentTurn = Color.BLACK;

            // when & then
            assertThatThrownBy(() -> chessBoard.move(startEndPosition, currentTurn))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("시작 위치에 아군 체스말이 존재해야 합니다.");
        }

        @DisplayName("체스말을 움직일 때, 도착 위치에 아군 말이 존재하는 경우 예외를 발생시킨다.")
        @Test
        void canNotAttack() {
            // given
            Map<Position, Piece> pieces = provideEmptyBoard();
            pieces.put(new Position(File.A, Rank.FIRST), Rook.from(Color.WHITE));
            pieces.put(new Position(File.A, Rank.SECOND), Rook.from(Color.WHITE));
            ChessBoard chessBoard = new ChessBoard(pieces);
            StartEndPosition startEndPosition =
                    new StartEndPosition(new Position(File.A, Rank.FIRST), new Position(File.A, Rank.SECOND));
            Color currentTurn = Color.WHITE;

            // when & then
            assertThatThrownBy(() -> chessBoard.move(startEndPosition, currentTurn))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착 위치에 아군 체스말이 존재할 수 없습니다.");
        }
    }

    @DisplayName("체스말 이동 성공 테스트")
    @Nested
    class PieceMoveSuccessTest {
        @DisplayName("target이 비어있는 경우 체스말을 움직인다.")
        @Test
        void movePieceTest() {
            // given
            Map<Position, Piece> pieces = provideEmptyBoard();
            pieces.put(new Position(File.A, Rank.FIRST), Rook.from(Color.WHITE));
            ChessBoard chessBoard = new ChessBoard(pieces);
            Map<Position, Piece> expected = provideEmptyBoard();
            expected.put(new Position(File.B, Rank.FIRST), Rook.from(Color.WHITE));

            // when
            chessBoard.move(new StartEndPosition(
                    new Position(File.A, Rank.FIRST),
                    new Position(File.B, Rank.FIRST)), Color.WHITE);

            // then
            assertThat(chessBoard.getPieces()).isEqualTo(expected);
        }

        @DisplayName("target이 체스말인 경우 공격한다.")
        @Test
        void moveAttackTest() {
            // given
            Map<Position, Piece> pieces = provideEmptyBoard();
            pieces.put(new Position(File.A, Rank.FIRST), Rook.from(Color.WHITE));
            pieces.put(new Position(File.B, Rank.FIRST), Queen.from(Color.BLACK));
            ChessBoard chessBoard = new ChessBoard(pieces);
            Map<Position, Piece> expected = provideEmptyBoard();
            expected.put(new Position(File.B, Rank.FIRST), Rook.from(Color.WHITE));

            // when
            chessBoard.move(new StartEndPosition(
                    new Position(File.A, Rank.FIRST),
                    new Position(File.B, Rank.FIRST)), Color.WHITE);

            // then
            assertThat(chessBoard.getPieces()).isEqualTo(expected);
        }
    }

    @DisplayName("각 체스 말이 몇개 존재하는지 센다.")
    @Test
    void countPieceTest() {
        // given
        Map<Position, Piece> pieces = provideEmptyBoard();
        pieces.put(new Position(File.A, Rank.FIRST), Rook.from(Color.WHITE));
        pieces.put(new Position(File.B, Rank.EIGHTH), Rook.from(Color.BLACK));
        pieces.put(new Position(File.A, Rank.THIRD), Rook.from(Color.BLACK));
        pieces.put(new Position(File.D, Rank.FOURTH), Pawn.from(Color.WHITE));
        pieces.put(new Position(File.E, Rank.FOURTH), Pawn.from(Color.WHITE));

        ChessBoard chessBoard = new ChessBoard(pieces);

        // when & then
        assertAll(
                () -> assertThat(chessBoard.countPiece(Rook.from(Color.WHITE))).isEqualTo(1),
                () -> assertThat(chessBoard.countPiece(Rook.from(Color.BLACK))).isEqualTo(2),
                () -> assertThat(chessBoard.countPiece(Pawn.from(Color.WHITE))).isEqualTo(2),
                () -> assertThat(chessBoard.countPiece(Queen.from(Color.WHITE))).isEqualTo(0)
        );
    }

    static Map<Position, Piece> provideEmptyBoard() {
        Map<Position, Piece> pieces = new HashMap<>();

        for (Rank rank : Rank.values()) {
            pieces.putAll(provideFileByRank(rank));
        }

        return pieces;
    }

    static Map<Position, Piece> provideFileByRank(Rank rank) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (File file : File.values()) {
            pieces.put(new Position(file, rank), Empty.getInstance());
        }

        return pieces;
    }
}
