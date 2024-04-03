package domain.piece;

import domain.File;
import domain.Rank;
import domain.Square;
import domain.Team;
import domain.piece.jumping.King;
import domain.piece.pawn.BlackPawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {
    @DisplayName("같은 팀의 현재 점수를 반환한다.")
    @Test
    void score() {
        // given
        final Pieces pieces = new Pieces(Map.of(new Square(File.B, Rank.EIGHT), new King(Team.BLACK),
                new Square(File.C, Rank.EIGHT), new Rook(Team.BLACK),
                new Square(File.A, Rank.SEVEN), new BlackPawn(),
                new Square(File.C, Rank.SEVEN), new BlackPawn(),
                new Square(File.D, Rank.SEVEN), new Bishop(Team.BLACK),
                new Square(File.B, Rank.SIX), new BlackPawn(),
                new Square(File.E, Rank.SIX), new Queen(Team.BLACK)));
        // when
        final double score = pieces.score(Team.BLACK);
        //then
        assertThat(score).isEqualTo(20.0);
    }

}
