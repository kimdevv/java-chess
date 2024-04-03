package application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.board.ChessBoard;
import domain.board.Winner;
import dto.MovementDto;
import fixture.PositionFixture;
import java.util.List;
import org.junit.jupiter.api.Test;
import support.FakeBoardDao;

class BoardServiceTest {
    @Test
    void 저장된_보드가_없으면_새로_생성한_보드를_로드한다() {
        int roomId = 1;
        BoardService boardService = new BoardService(new FakeBoardDao());

        boardService.loadBoard(roomId);

        assertThat(boardService.getChessBoard(roomId)).isNotNull();
    }

    @Test
    void 저장되어_있는_보드를_로드한다() {
        List<MovementDto> movementDtos = List.of(
                new MovementDto("a2", "a3"),
                new MovementDto("a7", "a6"));
        int roomId = 1;
        BoardService boardService = new BoardService(new FakeBoardDao(roomId, movementDtos));

        boardService.loadBoard(roomId);

        ChessBoard chessBoard = boardService.getChessBoard(roomId);
        assertThat(chessBoard.getPositionAndPieces())
                .doesNotContainKey(PositionFixture.A2)
                .containsKey(PositionFixture.A3)
                .doesNotContainKey(PositionFixture.A7)
                .containsKey(PositionFixture.A6);
    }

    @Test
    void 말을_움직인다() {
        BoardService boardService = new BoardService(new FakeBoardDao());
        int roomId = 1;

        boardService.loadBoard(roomId);
        boardService.move(new MovementDto("a2", "a3"), roomId);

        ChessBoard chessBoard = boardService.getChessBoard(roomId);
        assertThat(chessBoard.getPositionAndPieces()).containsKey(PositionFixture.A3);
    }

    @Test
    void 킹이_잡히면_게임이_끝난_상태이다() {
        List<MovementDto> movementDtos = List.of(
                new MovementDto("e2", "e4"),
                new MovementDto("e7", "e5"),
                new MovementDto("f1", "b5"),
                new MovementDto("d7", "d6"));
        int roomId = 1;
        BoardService boardService = new BoardService(new FakeBoardDao(roomId, movementDtos));

        boardService.loadBoard(roomId);
        boardService.move(new MovementDto("b5", "e8"), roomId);

        assertThat(boardService.isGameOver(roomId)).isTrue();
    }

    @Test
    void 승자를_반환한다() {
        List<MovementDto> movementDtos = List.of(
                new MovementDto("e2", "e4"),
                new MovementDto("e7", "e5"),
                new MovementDto("f1", "b5"),
                new MovementDto("d7", "d6"));
        int roomId = 1;
        BoardService boardService = new BoardService(new FakeBoardDao(roomId, movementDtos));

        boardService.loadBoard(roomId);
        boardService.move(new MovementDto("b5", "e8"), roomId);

        assertThat(boardService.getWinner(roomId)).isEqualTo(Winner.WHITE);
    }

    @Test
    void 보드를_삭제한다() {
        int roomId = 1;
        BoardService boardService = new BoardService(new FakeBoardDao());

        boardService.loadBoard(roomId);
        boardService.removeBoard(roomId);

        assertThatThrownBy(() -> boardService.getChessBoard(roomId))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("보드가 존재하지 않습니다.");
    }
}
