package service;

import static domain.Fixture.Positions.F5;
import static domain.Fixture.PredefinedBoardsOfEachScore.BOARD_WHITE_19_5_BLACK_20;
import static domain.game.PieceType.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import dao.FakeGameDao;
import dao.FakePieceDao;
import domain.game.ChessGame;
import domain.game.TeamColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBServiceTest {
    private TestableDBService dbService;

    @BeforeEach
    void setUp() {
        dbService = new TestableDBService(new FakeGameDao(), new FakePieceDao());
    }

    @Test
    @DisplayName("게임의 현재 상태를 불러올 수 있다.")
    void loadGameTest() {
        // Given
        ChessGame chessGameId1 = dbService.loadGame(1);
        ChessGame chessGameId2 = dbService.loadGame(2);

        // Then
        assertAll(
                () -> assertThat(chessGameId1.currentPlayingTeam()).isEqualTo(TeamColor.BLACK),
                () -> assertThat(chessGameId2.currentPlayingTeam()).isEqualTo(TeamColor.WHITE),
                () -> assertThat(chessGameId1.getPositionsOfPieces().get(F5).getPieceType()).isEqualTo(WHITE_PAWN)
        );
    }

    @Test
    @DisplayName("게임을 저장하고 불러올 수 있다.")
    void saveGameTest() {
        // Given
        ChessGame chessGame = ChessGame.of(TeamColor.BLACK, BOARD_WHITE_19_5_BLACK_20);

        // When
        int gameId = dbService.saveGame(chessGame);

        // Then
        assertAll(
                () -> assertThat(dbService.loadGame(gameId).currentPlayingTeam()).isEqualTo(TeamColor.BLACK),
                () -> assertThat(dbService.loadGame(gameId).currentScoreOf(TeamColor.WHITE)).isEqualTo(19.5),
                () -> assertThat(dbService.loadGame(gameId).currentScoreOf(TeamColor.BLACK)).isEqualTo(20)
        );
    }
}
