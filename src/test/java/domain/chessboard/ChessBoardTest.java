package domain.chessboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.coordinate.Coordinate;
import domain.piece.BlackPawn;
import domain.piece.Blank;
import domain.piece.Color;
import domain.piece.base.ChessPiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    ChessBoard chessBoard;
    Coordinate start;
    Coordinate destination;

    @BeforeEach
    void setUp() {
        chessBoard = new ChessBoard();
    }

    @DisplayName("이동할 위치에 말이 존재해야 한다.")
    @Test
    void pieceExistence() {
        start = new Coordinate(2, 0);
        destination = new Coordinate(3, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이동할 말이 존재하지 않습니다.");
    }

    @DisplayName("색상이 다른 말을 이동할 수 없다.")
    @Test
    void cantMoveOtherColorPiece() {
        start = new Coordinate(6, 0);
        destination = new Coordinate(7, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("색상이 다른 말을 움직일 수 없습니다.");
    }

    @DisplayName("목적지에 같은 색상의 말이 존재하면 이동할 수 없다.")
    @Test
    void cantMoveWhenSameColorPieceInDestination() {
        // 흰색 Rook을 흰색 Pawn이 있는 바로 위 칸으로 이동한다.
        start = new Coordinate(7, 0);
        destination = new Coordinate(6, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색의 말이 있는 곳으로는 이동할 수 없습니다.");
    }

    @DisplayName("목적지까지의 경로 상에 다른 말이 존재하면 이동할 수 없다.")
    @Test
    void cantMoveWhenPieceInPath() {
        // 흑색 Rook을 아래로 두 칸 이동시킨다. 이때,경로상에 흑색 Pawn이 존재한다.
        start = new Coordinate(0, 0);
        destination = new Coordinate(2, 0);

        assertThatThrownBy(() -> chessBoard.movePiece(start, destination, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }

    @DisplayName("상대의 말을 잡고 그 위치로 이동한다.")
    @Test
    void moveAfterKill() {
        /*
         RNBQKBNR
         PPPPPPPP
         ........
         ........
         .p......
         ........
         p.pppppp
         rnbqkbnr
         */
        chessBoard.movePiece(new Coordinate(6, 1), new Coordinate(4, 1), Color.WHITE);

        /*
         RNBQKBNR
         .PPPPPPP
         ........
         P.......
         .p......
         ........
         p.pppppp
         rnbqkbnr
         */
        chessBoard.movePiece(new Coordinate(1, 0), new Coordinate(3, 0), Color.BLACK);

        /*
         RNBQKBNR
         .PPPPPPP
         ........
         ........
         .P......
         ........
         p.pppppp
         rnbqkbnr
         */
        chessBoard.movePiece(new Coordinate(3, 0), new Coordinate(4, 1), Color.BLACK);

        assertThat(getPieceInBoard(new Coordinate(4, 1))).isInstanceOf(BlackPawn.class);
        assertThat(getPieceInBoard(new Coordinate(1, 0))).isInstanceOf(Blank.class);
        assertThat(getPieceInBoard(new Coordinate(6, 1))).isInstanceOf(Blank.class);
    }

    @DisplayName("상대의 KING을 잡았는지 확인한다.")
    @Test
    void removeKing() {
        MovingResult movingResult;
        /*
         RNBQKBNR
         PPPPPPPP
         ........
         ........
         ........
         ..n.....
         pppppppp
         r.bqkbnr
         */
        movingResult = chessBoard.movePiece(new Coordinate(7, 1), new Coordinate(5, 2), Color.WHITE);
        assertFalse(movingResult.isKingRemoved());

        /*
         RNBQKBNR
         PPPPPPPP
         ........
         .n......
         ........
         ........
         pppppppp
         r.bqkbnr
         */
        movingResult = chessBoard.movePiece(new Coordinate(5, 2), new Coordinate(3, 1), Color.WHITE);
        assertFalse(movingResult.isKingRemoved());

         /*
         RNBQKBNR
         PPnPPPPP
         ........
         ........
         ........
         ........
         pppppppp
         r.bqkbnr
         */
        movingResult = chessBoard.movePiece(new Coordinate(3, 1), new Coordinate(1, 2), Color.WHITE);
        assertFalse(movingResult.isKingRemoved());

         /*
         RNBQnBNR
         PP.PPPPP
         ........
         ........
         ........
         ........
         pppppppp
         r.bqkbnr
         */
        movingResult = chessBoard.movePiece(new Coordinate(1, 2), new Coordinate(0, 4), Color.WHITE);
        assertTrue(movingResult.isKingRemoved());
    }

    private ChessPiece getPieceInBoard(Coordinate coordinate) {
        return chessBoard.getBoard().get(coordinate);
    }
}
