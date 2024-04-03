package domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.position.Position;
import fixture.PositionFixture;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    @Test
    void 기물을_움직일_때_중간에_다른_기물이_있으면_예외가_발생한다() {
        Position source = PositionFixture.F4;
        Map<Position, Piece> positionPieceMap = Map.of(
                source, new Queen(Color.WHITE),
                PositionFixture.F5, new Queen(Color.BLACK));
        ChessBoard board = new ChessBoard(positionPieceMap);

        Position target = PositionFixture.F8;
        assertThatThrownBy(() -> board.movePiece(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 존재합니다.");
    }

    @Test
    void 기물을_움직일_때_중간에_다른_기물이_없으면_이동한다() {
        Position source = PositionFixture.C1;
        Position target = PositionFixture.C8;
        Piece piece = new Queen(Color.WHITE);
        ChessBoard board = new ChessBoard(Map.of(source, piece));

        board.movePiece(source, target);

        assertThat(board.getPositionAndPieces()).containsEntry(target, piece)
                .doesNotContainKey(source);
    }

    @Test
    void 기물을_잡는다() {
        Position source = PositionFixture.F4;
        Position target = PositionFixture.B8;
        Piece sourcePiece = new Queen(Color.WHITE);
        BlackPawn targetPiece = new BlackPawn();
        Map<Position, Piece> positionPieceMap = Map.of(
                source, sourcePiece,
                target, targetPiece);
        ChessBoard board = new ChessBoard(positionPieceMap);

        board.movePiece(source, target);

        assertThat(board.getPositionAndPieces()).containsEntry(target, sourcePiece)
                .doesNotContainValue(targetPiece);
    }

    @Test
    void 기물을_움직일_때_자신의_말이_아니면_예외가_발생한다() {
        Position source = PositionFixture.C3;
        Position target = PositionFixture.E3;
        Map<Position, Piece> positionPieceMap = Map.of(source, new Queen(Color.BLACK));
        ChessBoard board = new ChessBoard(positionPieceMap);

        assertThatThrownBy(() -> board.movePiece(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자신의 말을 움직여야 합니다.");
    }

    @Test
    void 기물을_움직이면_턴이_바뀐다() {
        Position whiteSource = PositionFixture.C7;
        Position blackSource = PositionFixture.E3;
        Map<Position, Piece> positionPieceMap = Map.of(
                whiteSource, new Queen(Color.WHITE),
                blackSource, new Queen(Color.BLACK)
        );
        ChessBoard board = new ChessBoard(positionPieceMap);
        board.movePiece(whiteSource, PositionFixture.A5);

        assertThatCode(() -> board.movePiece(blackSource, PositionFixture.F4))
                .doesNotThrowAnyException();
    }

    @Test
    void 남아있는_기물의_점수를_계산한다() {
        Color color = Color.BLACK;
        Map<Position, Piece> board = Map.of(
                PositionFixture.A1, new Queen(color),
                PositionFixture.B2, new Rook(color),
                PositionFixture.C3, new Bishop(color),
                PositionFixture.D4, new Knight(color),
                PositionFixture.F5, new King(color),
                PositionFixture.G6, new BlackPawn()
        );
        ChessBoard chessBoard = new ChessBoard(board);

        double score = chessBoard.calculateScore(color);

        assertThat(score).isEqualTo(20.5);
    }

    @Test
    void 같은_file의_pawn이_있을_경우_각각의_pawn_점수를_절반으로_계산한다() {
        Color color = Color.BLACK;
        Map<Position, Piece> board = Map.of(
                PositionFixture.B2, new BlackPawn(),
                PositionFixture.B3, new BlackPawn(),
                PositionFixture.B4, new BlackPawn(),
                PositionFixture.D5, new BlackPawn(),
                PositionFixture.G6, new BlackPawn(),
                PositionFixture.H7, new BlackPawn()
        );
        ChessBoard chessBoard = new ChessBoard(board);

        double score = chessBoard.calculateScore(color);

        assertThat(score).isEqualTo(4.5);
    }

    @Test
    void 화이트_또는_블랙의_킹_중_한_쪽이_잡혔으면_true를_반환한다() {
        Map<Position, Piece> board = Map.of(
                PositionFixture.A2, new King(Color.BLACK)
        );
        ChessBoard chessBoard = new ChessBoard(board);

        boolean isCapturedKing = chessBoard.isKingCaptured();

        assertThat(isCapturedKing).isTrue();
    }

    @Test
    void 화이트_또는_블랙의_킹_모두_잡히지_않았으면_false를_반환한다() {
        Map<Position, Piece> board = Map.of(
                PositionFixture.E8, new King(Color.WHITE),
                PositionFixture.C2, new King(Color.BLACK)
        );
        ChessBoard chessBoard = new ChessBoard(board);

        boolean isCapturedKing = chessBoard.isKingCaptured();

        assertThat(isCapturedKing).isFalse();
    }

    @Test
    void 화이트_킹이_잡혔으면_블랙이_승리한다() {
        Map<Position, Piece> board = Map.of(
                PositionFixture.A3, new King(Color.BLACK)
        );
        ChessBoard chessBoard = new ChessBoard(board);

        Winner winner = chessBoard.getWinner();

        assertThat(winner).isEqualTo(Winner.BLACK);
    }

    @Test
    void 블랙_킹이_잡혔으면_화이트가_승리한다() {
        Map<Position, Piece> board = Map.of(
                PositionFixture.H6, new King(Color.WHITE)
        );
        ChessBoard chessBoard = new ChessBoard(board);

        Winner winner = chessBoard.getWinner();

        assertThat(winner).isEqualTo(Winner.WHITE);
    }

    @Test
    void 화이트_킹과_블랙_킹이_모두_잡히지_않았으면_무승부이다() {
        Map<Position, Piece> board = Map.of(
                PositionFixture.G3, new King(Color.WHITE),
                PositionFixture.E1, new King(Color.BLACK)
        );
        ChessBoard chessBoard = new ChessBoard(board);

        Winner winner = chessBoard.getWinner();
        assertThat(winner).isEqualTo(Winner.DRAW);
    }
}
