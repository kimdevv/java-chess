package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("초기 보드 생성 후 존재하는 기물들을 전부 확인한다")
    @Test
    void createInitialBoard() {
        Board board = BoardFactory.createInitialBoard();

        Map<Position, Piece> pieces = new HashMap<>();
        pieces.put(Position.of(File.A, Rank.ONE), Rook.WHITE);
        pieces.put(Position.of(File.B, Rank.ONE), Knight.WHITE);
        pieces.put(Position.of(File.C, Rank.ONE), Bishop.WHITE);
        pieces.put(Position.of(File.D, Rank.ONE), Queen.WHITE);
        pieces.put(Position.of(File.E, Rank.ONE), King.WHITE);
        pieces.put(Position.of(File.F, Rank.ONE), Bishop.WHITE);
        pieces.put(Position.of(File.G, Rank.ONE), Knight.WHITE);
        pieces.put(Position.of(File.H, Rank.ONE), Rook.WHITE);
        pieces.put(Position.of(File.A, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.B, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.C, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.D, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.E, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.F, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.G, Rank.TWO), Pawn.WHITE_PAWN);
        pieces.put(Position.of(File.H, Rank.TWO), Pawn.WHITE_PAWN);

        pieces.put(Position.of(File.A, Rank.EIGHT), Rook.BLACK);
        pieces.put(Position.of(File.B, Rank.EIGHT), Knight.BLACK);
        pieces.put(Position.of(File.C, Rank.EIGHT), Bishop.BLACK);
        pieces.put(Position.of(File.D, Rank.EIGHT), Queen.BLACK);
        pieces.put(Position.of(File.E, Rank.EIGHT), King.BLACK);
        pieces.put(Position.of(File.F, Rank.EIGHT), Bishop.BLACK);
        pieces.put(Position.of(File.G, Rank.EIGHT), Knight.BLACK);
        pieces.put(Position.of(File.H, Rank.EIGHT), Rook.BLACK);
        pieces.put(Position.of(File.A, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.B, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.C, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.D, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.E, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.F, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.G, Rank.SEVEN), Pawn.BLACK_PAWN);
        pieces.put(Position.of(File.H, Rank.SEVEN), Pawn.BLACK_PAWN);

        assertThat(board.getPieces()).isEqualTo(pieces);
    }

    @DisplayName("해당 위치에 존재하는 기물을 반환한다")
    @Test
    void findPieceByPosition() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(Position.of(File.A, Rank.ONE));

        assertThat(findPiece).isEqualTo(Rook.WHITE);
    }

    @DisplayName("해당 위치에 기물이 없으면 Empty를 반환한다")
    @Test
    void findPieceByPosition_Empty() {
        Board board = BoardFactory.createInitialBoard();

        Piece findPiece = board.findPieceByPosition(Position.of(File.A, Rank.THREE));

        assertThat(findPiece.getPieceType()).isEqualTo(PieceType.NONE);
    }

    @DisplayName("기물을 이동하는 경우 ")
    @Nested
    class whenMovePiece {
        @DisplayName("같은 위치면 예외를 발생한다")
        @Test
        void samePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position position = Position.of(File.A, Rank.ONE);

            assertThatThrownBy(
                    () -> board.move(position, position, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("기물이 없으면 예외를 발생한다")
        @Test
        void emptyPiece() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = Position.of(File.A, Rank.THREE);
            Position targetPosition = Position.of(File.A, Rank.FOUR);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("내 말이 아니면 예외를 발생한다")
        @Test
        void notMyTurn() {
            Color turn = Color.BLACK;
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = Position.of(File.A, Rank.ONE);
            Position targetPosition = Position.of(File.A, Rank.TWO);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, turn))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("이동할 수 없는 위치면 예외를 발생한다")
        @Test
        void notMovablePosition() {
            Board board = BoardFactory.createInitialBoard();
            Position sourcePosition = Position.of(File.A, Rank.ONE);
            Position targetPosition = Position.of(File.A, Rank.FOUR);

            assertThatThrownBy(
                    () -> board.move(sourcePosition, targetPosition, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @DisplayName("상대 진영 기물 위치로 이동했을 경우 제거하고 이동한다")
        @Test
        void whenMoveToEnemyPiece() {
            Position knightSourcePosition = Position.of(File.D, Rank.THREE);
            Position knightTargetPosition = Position.of(File.E, Rank.FIVE);

            Map<Position, Piece> pieces = new HashMap<>();
            pieces.put(knightSourcePosition, Knight.WHITE);
            pieces.put(knightTargetPosition, Rook.BLACK);

            Board board = new Board(pieces, new ScoreCalculator());

            board.move(knightSourcePosition, knightTargetPosition, Color.WHITE);

            assertThat(pieces).containsEntry(knightTargetPosition, Knight.WHITE);
        }

        @DisplayName("어느 한 쪽 킹이 존재하지 않는다면 true를 반환한다")
        @Test
        void givenBoardIsKingDeadWhenAnyKingDeadThenReturnTrue() {
            Board board = new Board(Map.of(Position.from("e8"), King.BLACK),
                    new ScoreCalculator());

            assertThat(board.isKingDead()).isTrue();
        }

        @DisplayName("두 킹이 모두 존재한다면 false를 반환한다")
        @Test
        void givenBoardIsKingDeadWhenAnyOneKingNotDeadThenReturnFalse() {
            Board board = new Board(Map.of(Position.from("e8"), King.BLACK, Position.from("e7"), King.WHITE),
                    new ScoreCalculator());

            assertThat(board.isKingDead()).isFalse();
        }
    }
}
