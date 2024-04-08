package chess.service;

import static chess.domain.pixture.PieceFixture.BLACK_PAWN;
import static chess.domain.pixture.PieceFixture.WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.SavedBoardInitializer;
import chess.domain.game.Game;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.piece.FakePieceRepository;
import chess.repository.piece.PieceRepository;
import chess.repository.turn.FakeTurnRepository;
import chess.repository.turn.TurnRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameServiceTest {
    private ChessGameService chessGameService;
    private PieceRepository pieceRepository;
    private TurnRepository turnRepository;
    private Game game;

    @BeforeEach
    void init() {
        pieceRepository = new FakePieceRepository();
        turnRepository = new FakeTurnRepository();
        chessGameService = new ChessGameService(pieceRepository, turnRepository);

        game = new Game(new SavedBoardInitializer(
                Map.of(Position.of(1, 1), BLACK_PAWN.getPiece(), Position.of(3, 1), WHITE_PAWN.getPiece())));
    }

    @Test
    @DisplayName("현재 Board를 저장한다.")
    void save() {
        chessGameService.saveGame(game);
        Map<Position, Piece> pieces = new LinkedHashMap<>();
        pieces.put(Position.of(1, 1), new Pawn(Color.BLACK));
        pieces.put(Position.of(3, 1), new Pawn(Color.WHITE));

        assertAll(
                () -> assertThat(pieceRepository.findAllPiece()).containsAllEntriesOf(pieces),
                () -> assertThat(turnRepository.findAny()).isEqualTo(Optional.of(Color.BLACK)));
    }

    @Test
    @DisplayName("저장된 위치별 피스를 조회한다.")
    void findAllPiece() {
        chessGameService.saveGame(game);
        assertThat(chessGameService.findAllPiece()).containsExactlyInAnyOrderEntriesOf(
                Map.of(Position.of(1, 1), BLACK_PAWN.getPiece(),
                        Position.of(3, 1), WHITE_PAWN.getPiece()));
    }

    @Test
    @DisplayName("전체 내역을 삭제한다.")
    void delete() {
        chessGameService.delete();
        assertThat(chessGameService.findAllPiece()).isEmpty();
    }

    @Test
    @DisplayName("저장된 게임이 있는지 확인한다.")
    void existGame() {
        chessGameService.saveGame(game);
        assertThat(chessGameService.existSavedGame()).isTrue();
    }

    @Test
    @DisplayName("저장된 게임이 있는지 확인한다.")
    void notExistGame() {
        chessGameService.delete();
        assertThat(chessGameService.existSavedGame()).isFalse();
    }
}
