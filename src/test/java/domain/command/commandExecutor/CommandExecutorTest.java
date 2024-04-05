package domain.command.commandExecutor;

import command.commandExecutor.CommandExecutor;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ChessGameService;
import service.PieceService;
import state.chessGame.InitialChessGameState;

class CommandExecutorTest {

    @DisplayName("존재하지 않는 Command일 경우, 에러를 발생한다.")
    @Test
    void notExistCommandFind() {
        CommandExecutor commandExecutor = new CommandExecutor(new ChessGameService(), new PieceService());

        Assertions.assertThatThrownBy(
                        () -> commandExecutor.executeCommand(List.of("fail", "a", "1"), new InitialChessGameState()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당하는 Command가 없습니다.");
    }
}
