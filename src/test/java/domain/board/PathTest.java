package domain.board;

import domain.Side;
import domain.piece.King;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Rook;
import domain.position.Position;
import fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PathTest {

    @DisplayName("current와 target의 사이를 경로로 만든다.")
    @Nested
    class PathMake {

        @DisplayName("대각선 경로를 만든다.")
        @Test
        void makeDiagonalPath() {
            Position current = PositionFixture.a1();
            Position target = PositionFixture.h8();

            Path path = Path.createOf(current, target);

            List<Position> diagonalPath = List.of(PositionFixture.b2(),
                    PositionFixture.c3(),
                    PositionFixture.d4(),
                    PositionFixture.e5(),
                    PositionFixture.f6(),
                    PositionFixture.g7()
            );

            assertThat(path).extracting("path").isEqualTo(diagonalPath);
        }

        @DisplayName("수평 경로를 만든다.")
        @Test
        void makeRankPath() {
            Position current = PositionFixture.a1();
            Position target = PositionFixture.h1();

            Path path = Path.createOf(current, target);

            List<Position> rankPath = List.of(PositionFixture.b1(),
                    PositionFixture.c1(),
                    PositionFixture.d1(),
                    PositionFixture.e1(),
                    PositionFixture.f1(),
                    PositionFixture.g1()
            );

            assertThat(path).extracting("path").isEqualTo(rankPath);
        }

        @DisplayName("수직 경로를 만든다.")
        @Test
        void makeFilePath() {
            Position current = PositionFixture.a1();
            Position target = PositionFixture.a8();

            Path path = Path.createOf(current, target);

            List<Position> filePath = List.of(PositionFixture.a2(),
                    PositionFixture.a3(),
                    PositionFixture.a4(),
                    PositionFixture.a5(),
                    PositionFixture.a6(),
                    PositionFixture.a7()
            );

            assertThat(path).extracting("path").isEqualTo(filePath);
        }
    }

    @DisplayName("경로에 존재하는 기물들을 찾는다.")
    @Test
    void findPiecesOnPath() {
        Position current = PositionFixture.a1();
        Position target = PositionFixture.h8();

        Path path = Path.createOf(current, target);

        Map<Position, Piece> board = new LinkedHashMap<>();

        board.put(PositionFixture.a2(), new Rook(Side.BLACK));
        board.put(PositionFixture.b2(), new Pawn(Side.BLACK));
        board.put(PositionFixture.g7(), new King(Side.BLACK));

        Map<Position, Piece> pieces = path.findPieces(board);

        Map<Position, Piece> piecesOnPath = new LinkedHashMap<>();
        piecesOnPath.put(PositionFixture.b2(), new Pawn(Side.BLACK));
        piecesOnPath.put(PositionFixture.g7(), new King(Side.BLACK));

        assertThat(pieces).isEqualTo(piecesOnPath);
    }
}
