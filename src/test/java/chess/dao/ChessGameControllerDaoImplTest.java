package chess.dao;

import static chess.domain.GameStatus.FINISHED;
import static chess.domain.GameStatus.PLAYING;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.GameStatus;
import chess.dto.ChessGameRequest;
import chess.dto.ChessGameResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameControllerDaoImplTest {
    private ChessGameDao chessGameDao = new FakeChessGameDaoImpl();

    @DisplayName("진행중인 체스 게임을 저장할 수 있다.")
    @Test
    void savePlayingChessGame() {
        ChessGameRequest chessGameRequest = new ChessGameRequest(GameStatus.PLAYING.name());
        Long gameId = chessGameDao.save(chessGameRequest);

        assertThat(gameId).isEqualTo(1L);
    }

    @DisplayName("진행중인 마지막 게임을 불러올 수 있다")
    @Test
    void findRecentGame() {
        chessGameDao.save(new ChessGameRequest(GameStatus.PLAYING.name()));
        Optional<ChessGameResponse> recentPlayableGame = chessGameDao.findRecentPlayableGame();

        assertThat(recentPlayableGame.isPresent()).isTrue();
        assertThat(recentPlayableGame.get().gameStatus()).isEqualTo(GameStatus.PLAYING.name());
    }

    @DisplayName("킹이 죽으면 이전 게임들의 상태를 모두 Finished 상태로 업데이트한다")
    @Test
    void givenKingIsDeadWhenUpdateGameFinishedThenPreviousGamesFinished() {
        chessGameDao.updateGameFinished();

        assertThat(chessGameDao.findRecentPlayableGame().isEmpty()).isTrue();
    }

    static class FakeChessGameDaoImpl implements ChessGameDao {
        private List<ChessGameRequest> requests = new ArrayList<>();

        @Override
        public Long save(ChessGameRequest chessGameRequest) {
            requests.add(chessGameRequest);
            return (long) requests.size();
        }

        @Override
        public Optional<ChessGameResponse> findRecentPlayableGame() {
            return requests.stream()
                    .filter(request -> PLAYING.name().equals(request.gameStatus()))
                    .map(request -> new ChessGameResponse((long) requests.indexOf(request) + 1, request.gameStatus()))
                    .reduce((first, second) -> second);
        }

        @Override
        public void updateGameFinished() {
            List<ChessGameRequest> updatedRequests = requests.stream()
                    .map(request -> new ChessGameRequest(FINISHED.name()))
                    .collect(Collectors.toList());
            this.requests = updatedRequests;
        }
    }
}
