package chess.domain.chessboard;

import static chess.domain.GameStatus.BLACK_TURN;
import static chess.domain.GameStatus.GAME_OVER;
import static chess.domain.GameStatus.WHITE_TURN;
import static chess.domain.chesspiece.Team.BLACK;
import static chess.domain.chesspiece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Piece;
import chess.domain.chesspiece.Score;
import chess.domain.chesspiece.Team;
import chess.domain.chesspiece.pawn.BlackPawn;
import chess.domain.chesspiece.pawn.WhitePawn;
import chess.domain.chesspiece.slidingPiece.Bishop;
import chess.domain.chesspiece.slidingPiece.King;
import chess.domain.chesspiece.slidingPiece.Queen;
import chess.domain.chesspiece.slidingPiece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessBoardTest {

    @Test
    @DisplayName("흑팀의 기물이 움직이면 백팀 차례가 된다.")
    void ChessBoard_Turn_to_white_when_black_move() {
        Position source = new Position(File.b, Rank.EIGHT);
        Position target = new Position(File.b, Rank.SEVEN);
        var sut = new ChessBoard(
                new HashMap<>() {{
                    put(new Position(File.b, Rank.EIGHT), new King(BLACK));
                }}
        );

        var result = sut.move(source, target, BLACK_TURN);

        assertThat(result).isEqualTo(WHITE_TURN);
    }

    @Test
    @DisplayName("백팀의 기물이 움직이면 흑팀 차례가 된다.")
    void ChessBoard_Turn_to_Black_when_white_move() {
        Position source = new Position(File.f, Rank.ONE);
        Position target = new Position(File.g, Rank.ONE);
        var sut = new ChessBoard(
                new HashMap<>() {{
                    put(new Position(File.f, Rank.ONE), new King(Team.WHITE));
                }}
        );

        var result = sut.move(source, target, WHITE_TURN);

        assertThat(result).isEqualTo(BLACK_TURN);
    }

    @Test
    @DisplayName("왕을 잡으면 게임이 종료된다.")
    void ChessBoard_Game_over_when_king_is_out() {
        Position source = new Position(File.a, Rank.SEVEN);
        Position target = new Position(File.b, Rank.EIGHT);
        var sut = new ChessBoard(
                new HashMap<>() {{
                    put(new Position(File.b, Rank.EIGHT), new King(BLACK));
                    put(new Position(File.a, Rank.SEVEN), new WhitePawn());
                }}
        );

        var result = sut.move(source, target, WHITE_TURN);

        assertThat(result).isEqualTo(GAME_OVER);
    }

    @Test
    @DisplayName("비어있는 공간을 움직이려고 하면 예외가 발생한다.")
    void ChessBoard_Throw_exception_when_move_empty() {
        Position source = new Position(File.a, Rank.SEVEN);
        Position target = new Position(File.b, Rank.EIGHT);
        var sut = new ChessBoard(
                new HashMap<>());

        assertThatThrownBy(() -> sut.move(source, target, WHITE_TURN))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*
    R.......
    ........
    ........
    p.......
    ........
    ........
    ........
    ........
    */
    @Test
    @DisplayName("이동 경로에 방해물이 있는 경우 예외가 발생한다.")
    void ChessBoard_Throw_exception_route_has_obstacle() {
        Position source = new Position(File.a, Rank.EIGHT);
        Position target = new Position(File.a, Rank.ONE);
        var sut = new ChessBoard(
                new HashMap<>() {{
                    put(new Position(File.a, Rank.EIGHT), new Rook(BLACK));
                    put(new Position(File.a, Rank.FIVE), new WhitePawn());
                }}
        );

        assertThatThrownBy(() -> sut.move(source, target, BLACK_TURN))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*
    R.......
    ........
    ........
    P.......
    ........
    ........
    ........
    ........
     */
    @Test
    @DisplayName("목적지에 아군이 있는 경우 예외가 발생한다.")
    void ChessBoard_Throw_exception_target_is_team() {
        Position source = new Position(File.a, Rank.EIGHT);
        Position target = new Position(File.a, Rank.FIVE);
        var sut = new ChessBoard(
                new HashMap<>() {{
                    put(new Position(File.a, Rank.EIGHT), new Rook(BLACK));
                    put(new Position(File.a, Rank.FIVE), new BlackPawn());
                }}
        );

        assertThatThrownBy(() -> sut.move(source, target, BLACK_TURN))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /*
    .KR.....
    p.PB....
    .P..Q...
    ........
    .....nq.
    .....p.p
    .....pp.
    ....rk..
    */
    @Test
    @DisplayName("각 팀의 점수를 계산한다.")
    void ChessBoard_Calculate_total_score() {
        var sut = new ChessBoard(
                new HashMap<>() {{
                    put(new Position(File.b, Rank.EIGHT), new King(BLACK));
                    put(new Position(File.c, Rank.EIGHT), new Rook(BLACK));
                    put(new Position(File.a, Rank.SEVEN), new BlackPawn());
                    put(new Position(File.c, Rank.SEVEN), new BlackPawn());
                    put(new Position(File.d, Rank.SEVEN), new Bishop(BLACK));
                    put(new Position(File.b, Rank.SIX), new BlackPawn());
                    put(new Position(File.e, Rank.SIX), new Queen(BLACK));
                    put(new Position(File.f, Rank.FOUR), new Knight(Team.WHITE));
                    put(new Position(File.g, Rank.FOUR), new Queen(Team.WHITE));
                    put(new Position(File.f, Rank.THREE), new WhitePawn());
                    put(new Position(File.h, Rank.THREE), new WhitePawn());
                    put(new Position(File.f, Rank.TWO), new WhitePawn());
                    put(new Position(File.g, Rank.TWO), new WhitePawn());
                    put(new Position(File.e, Rank.ONE), new Rook(Team.WHITE));
                    put(new Position(File.f, Rank.ONE), new King(Team.WHITE));
                }}
        );

        var result = sut.calculateTotalScore();

        assertThat(result.get(WHITE)).isEqualTo(new Score(19.5));
        assertThat(result.get(BLACK)).isEqualTo(new Score(20));
    }
}
