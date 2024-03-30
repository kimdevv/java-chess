package chess.domain.chessboard;

import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardGeneratorTest {

    @Test
    @DisplayName("초기화된 체스 보드를 생성한다.")
    void ChessBoardGenerator_Create_initial_chess_board() {
        var result = ChessBoardGenerator.initializeBoard();

        assertAll(
                () -> assertThat(result.get(new Position("a", "8"))).isEqualTo(new Rook(BLACK)),
                () -> assertThat(result.get(new Position("b", "8"))).isEqualTo(new Knight(BLACK)),
                () -> assertThat(result.get(new Position("c", "8"))).isEqualTo(new Bishop(BLACK)),
                () -> assertThat(result.get(new Position("d", "8"))).isEqualTo(new Queen(BLACK)),
                () -> assertThat(result.get(new Position("e", "8"))).isEqualTo(new King(BLACK)),
                () -> assertThat(result.get(new Position("f", "8"))).isEqualTo(new Bishop(BLACK)),
                () -> assertThat(result.get(new Position("g", "8"))).isEqualTo(new Knight(BLACK)),
                () -> assertThat(result.get(new Position("h", "8"))).isEqualTo(new Rook(BLACK)),
                () -> assertThat(result.get(new Position("a", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("b", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("c", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("d", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("e", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("f", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("g", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("h", "7"))).isEqualTo(new BlackPawn()),
                () -> assertThat(result.get(new Position("a", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("b", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("c", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("d", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("e", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("f", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("g", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("h", "2"))).isEqualTo(new WhitePawn()),
                () -> assertThat(result.get(new Position("a", "1"))).isEqualTo(new Rook(WHITE)),
                () -> assertThat(result.get(new Position("b", "1"))).isEqualTo(new Knight(WHITE)),
                () -> assertThat(result.get(new Position("c", "1"))).isEqualTo(new Bishop(WHITE)),
                () -> assertThat(result.get(new Position("d", "1"))).isEqualTo(new Queen(WHITE)),
                () -> assertThat(result.get(new Position("e", "1"))).isEqualTo(new King(WHITE)),
                () -> assertThat(result.get(new Position("f", "1"))).isEqualTo(new Bishop(WHITE)),
                () -> assertThat(result.get(new Position("g", "1"))).isEqualTo(new Knight(WHITE)),
                () -> assertThat(result.get(new Position("h", "1"))).isEqualTo(new Rook(WHITE))
        );
    }
}
