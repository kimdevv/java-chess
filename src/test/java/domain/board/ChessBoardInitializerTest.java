package domain.board;

import domain.piece.Piece;
import domain.position.Position;
import fixture.PieceFixture;
import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ChessBoardInitializerTest {

    Map<Position, Piece> board = ChessBoardInitializer.init();

    @DisplayName("검은색 룩은 a8, h8에 위치한다. 흰색 룩은 a1, h1에 위치한다.")
    @Test
    void rookPositionTest() {
        assertThat(board).contains(
                Map.entry(PositionFixture.a8(), PieceFixture.blackRook()),
                Map.entry(PositionFixture.h8(), PieceFixture.blackRook()),
                Map.entry(PositionFixture.a1(), PieceFixture.whiteRook()),
                Map.entry(PositionFixture.h1(), PieceFixture.whiteRook())
        );
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다. 흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void knightPositionTest() {
        assertThat(board).contains(
                Map.entry(PositionFixture.b8(), PieceFixture.blackKnight()),
                Map.entry(PositionFixture.g8(), PieceFixture.blackKnight()),
                Map.entry(PositionFixture.b1(), PieceFixture.whiteKnight()),
                Map.entry(PositionFixture.g1(), PieceFixture.whiteKnight())
        );
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다. 흰색 나이트는 c1, f1에 위치한다.")
    @Test
    void bishopPositionTest() {
        assertThat(board).contains(
                Map.entry(PositionFixture.c8(), PieceFixture.blackBishop()),
                Map.entry(PositionFixture.f8(), PieceFixture.blackBishop()),
                Map.entry(PositionFixture.c1(), PieceFixture.whiteBishop()),
                Map.entry(PositionFixture.f1(), PieceFixture.whiteBishop())
        );
    }

    @DisplayName("검은색 퀸은 d8에 위치한다. 흰색 퀸은 d1에 위치한다.")
    @Test
    void queenPositionTest() {
        assertThat(board).contains(
                Map.entry(PositionFixture.d8(), PieceFixture.blackQueen()),
                Map.entry(PositionFixture.d1(), PieceFixture.whiteQueen())
        );
    }

    @DisplayName("검은색 킹은 e8에 위치한다. 흰색 킹은 e1에 위치한다.")
    @Test
    void kingPositionTest() {
        assertThat(board).contains(
                Map.entry(PositionFixture.e8(), PieceFixture.blackKing()),
                Map.entry(PositionFixture.e1(), PieceFixture.whiteKing())
        );
    }

    @DisplayName("검은색 폰은 a7~h7에 위치한다. 흰색 폰은 a2~h2에 위치한다.")
    @Test
    void pawnPositionTest() {
        assertThat(board).contains(
                Map.entry(PositionFixture.a7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.b7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.c7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.d7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.e7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.f7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.g7(), PieceFixture.blackPawn()),
                Map.entry(PositionFixture.h7(), PieceFixture.blackPawn()),

                Map.entry(PositionFixture.a2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.b2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.c2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.d2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.e2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.f2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.g2(), PieceFixture.whitePawn()),
                Map.entry(PositionFixture.h2(), PieceFixture.whitePawn())
        );
    }
}
