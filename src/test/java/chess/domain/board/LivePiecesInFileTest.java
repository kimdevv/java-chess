package chess.domain.board;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.WhitePawnFirstMove;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.PieceScore;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.domain.strategy.KnightMoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import chess.domain.strategy.WhitePawnFirstMoveStrategy;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LivePiecesInFileTest {
    private static final WhitePawnFirstMoveStrategy WHITE_PAWN_STRATEGY = new WhitePawnFirstMoveStrategy();
    private static final Pawn FIRST_PAWN =
            new WhitePawnFirstMove(new PieceInfo(Position.of("a2"), Team.WHITE), WHITE_PAWN_STRATEGY);
    private static final Pawn SECOND_PAWN =
            new WhitePawnFirstMove(new PieceInfo(Position.of("a3"), Team.WHITE), WHITE_PAWN_STRATEGY);
    private static final Pawn THIRD_PAWN =
            new WhitePawnFirstMove(new PieceInfo(Position.of("a4"), Team.WHITE), WHITE_PAWN_STRATEGY);

    private static final Rook WHITE_ROOK =
            new Rook(new PieceInfo(Position.of("b1"), Team.WHITE), new RookMoveStrategy());
    private static final Knight BLACK_KNIGHT =
            new Knight(new PieceInfo(Position.of("b2"), Team.BLACK), new KnightMoveStrategy());

    @DisplayName("한 열에 폰이 한 개 있으면 합계는 1점이다.")
    @Test
    void calculateOnePawnScoreSumTest() {
        List<Piece> livePieces = List.of(FIRST_PAWN);
        LivePiecesInFile livePiecesInFile = new LivePiecesInFile(livePieces);

        double actualScoreSum = livePiecesInFile.calculatePieceScoreSum(Team.WHITE);
        double expectedScoreSum = 1.0;

        Assertions.assertThat(actualScoreSum).isEqualTo(expectedScoreSum);
    }

    @DisplayName("한 열에 폰이 두 개 있으면 합계는 1점이다.")
    @Test
    void calculateTwoPawnInSameFileScoreSumTest() {
        List<Piece> livePieces = List.of(FIRST_PAWN, SECOND_PAWN);
        LivePiecesInFile livePiecesInFile = new LivePiecesInFile(livePieces);

        double actualScoreSum = livePiecesInFile.calculatePieceScoreSum(Team.WHITE);
        double expectedScoreSum = 1.0;

        Assertions.assertThat(actualScoreSum).isEqualTo(expectedScoreSum);
    }

    @DisplayName("한 열에 폰이 세 개 있으면 합계는 1.5점이다.")
    @Test
    void calculateThreePawnInSameFileScoreSumTest() {
        List<Piece> livePieces = List.of(FIRST_PAWN, SECOND_PAWN, THIRD_PAWN);
        LivePiecesInFile livePiecesInFile = new LivePiecesInFile(livePieces);

        double actualScoreSum = livePiecesInFile.calculatePieceScoreSum(Team.WHITE);
        double expectedScoreSum = 1.5;

        Assertions.assertThat(actualScoreSum).isEqualTo(expectedScoreSum);
    }

    @DisplayName("한 열에 흰색 폰 하나와 검은색 나이트 하나가 있으면, 흰색 팀의 합계는 흰색 룩 하나의 점수와 같다.")
    @Test
    void calculateWhitePiecesScoreSumTest() {
        List<Piece> livePieces = List.of(WHITE_ROOK, BLACK_KNIGHT);
        LivePiecesInFile livePiecesInFile = new LivePiecesInFile(livePieces);

        double actualScoreSum = livePiecesInFile.calculatePieceScoreSum(Team.WHITE);
        double expectedScoreSum = PieceScore.ROOK_SCORE.get();

        Assertions.assertThat(actualScoreSum).isEqualTo(expectedScoreSum);
    }

    @DisplayName("한 열에 흰색 폰 하나와 검은색 나이트 하나가 있으면, 검은색 팀의 합계는 검은색 나이트 하나의 점수와 같다.")
    @Test
    void calculateBlackPiecesScoreSumTest() {
        List<Piece> livePieces = List.of(WHITE_ROOK, BLACK_KNIGHT);
        LivePiecesInFile livePiecesInFile = new LivePiecesInFile(livePieces);

        double actualScoreSum = livePiecesInFile.calculatePieceScoreSum(Team.BLACK);
        double expectedScoreSum = PieceScore.KNIGHT_SCORE.get();

        Assertions.assertThat(actualScoreSum).isEqualTo(expectedScoreSum);
    }
}
