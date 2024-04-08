package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceFactoryTest {

    @Test
    @DisplayName("기물 타입이 폰일 경우 폰 객체를 생성한다.")
    void createPawn() {
        Piece whitePawn = PieceFactory.create(PieceType.PAWN, Color.WHITE);
        assertThat(whitePawn).isEqualTo(new Pawn(Color.WHITE));
    }

    @Test
    @DisplayName("기물 타입이 킹일 경우 킹 객체를 생성한다.")
    void createKing() {
        Piece whiteKing = PieceFactory.create(PieceType.KING, Color.WHITE);
        assertThat(whiteKing).isEqualTo(new King(Color.WHITE));
    }

    @Test
    @DisplayName("기물 타입이 퀸일 경우 퀸 객체를 생성한다.")
    void createQueen() {
        Piece whiteQueen = PieceFactory.create(PieceType.QUEEN, Color.WHITE);
        assertThat(whiteQueen).isEqualTo(new Queen(Color.WHITE));
    }

    @Test
    @DisplayName("기물 타입이 나이트일 경우 나이트 객체를 생성한다.")
    void createKnight() {
        Piece whiteKnight = PieceFactory.create(PieceType.KNIGHT, Color.WHITE);
        assertThat(whiteKnight).isEqualTo(new Knight(Color.WHITE));
    }

    @Test
    @DisplayName("기물 타입이 룩일 경우 룩 객체를 생성한다.")
    void createRook() {
        Piece whiteRook = PieceFactory.create(PieceType.ROOK, Color.WHITE);
        assertThat(whiteRook).isEqualTo(new Rook(Color.WHITE));
    }

    @Test
    @DisplayName("기물 타입이 비숍일 경우 비숍 객체를 생성한다.")
    void createBishop() {
        Piece whiteBishop = PieceFactory.create(PieceType.BISHOP, Color.WHITE);
        assertThat(whiteBishop).isEqualTo(new Bishop(Color.WHITE));
    }

    @Test
    @DisplayName("기물 타입이 없을 경우 Empty 객체를 생성한다.")
    void createEmpty() {
        Piece empty = PieceFactory.create(PieceType.EMPTY, Color.NONE);
        assertThat(empty).isEqualTo(Empty.getInstance());
    }
}
