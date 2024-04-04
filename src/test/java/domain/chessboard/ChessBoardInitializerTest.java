package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import domain.piece.type.PieceType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardInitializerTest {

    Map<Coordinate, ChessPiece> board;
    List<ChessPiece> piecesInRow;

    @BeforeEach
    void setUp() {
        board = ChessBoardInitializer.createInitialBoard();
    }

    @DisplayName("맨 위의 행은 모두 흑색 말로 구성되어 있다.")
    @Test
    void firstRowPiecesIsAllBlack() {
        piecesInRow = getPiecesInRow(0);

        assertThat(piecesInRow).allMatch(piece -> piece.hasSameColor(Color.BLACK));
    }

    @DisplayName("위에서 두 번째 행은 모두 흑색 폰으로 구성되어 있다.")
    @Test
    void secondRowPiecesIsAllBlackPawn() {
        piecesInRow = getPiecesInRow(1);

        assertThat(piecesInRow).allMatch(piece -> piece.isSameType(PieceType.PAWN) && piece.hasSameColor(Color.BLACK));
    }

    @DisplayName("위에서 세 번째 행부터 여섯 번째 행은 모두 비어있다.")
    @Test
    void thirdToSixthRowPieceIsAllEmpty() {
        for (int i = 2; i <= 5; i++) {
            piecesInRow = getPiecesInRow(i);

            assertThat(piecesInRow).allMatch(piece -> piece.isSameType(PieceType.BLANK));
        }
    }

    @DisplayName("아래에서 두 번째 행은 모두 흰색 폰으로 구성되어 있다.")
    @Test
    void seventhRowPieceIsAllWhitePawn() {
        piecesInRow = getPiecesInRow(6);

        assertThat(piecesInRow).allMatch(piece -> piece.isSameType(PieceType.PAWN) && piece.hasSameColor(Color.WHITE));
    }

    @DisplayName("맨 아래의 행은 모두 흰색 말로 구성되어 있다.")
    @Test
    void eighthRowPieceIsAllWhite() {
        piecesInRow = getPiecesInRow(7);

        assertThat(piecesInRow).allMatch(piece -> piece.hasSameColor(Color.WHITE));
    }

    private List<ChessPiece> getPiecesInRow(int row) {
        return board.entrySet().stream()
                .filter(entry -> entry.getKey().isSameRow(row))
                .map(Entry::getValue)
                .toList();
    }
}
