package chess.service;

import chess.domain.game.Game;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.repository.piece.PieceRepository;
import chess.repository.turn.TurnRepository;
import java.util.Map;
import java.util.Optional;

public class ChessGameService {

    private final PieceRepository pieceRepository;
    private final TurnRepository turnRepository;

    public ChessGameService(final PieceRepository pieceRepository, final TurnRepository turnRepository) {
        this.pieceRepository = pieceRepository;
        this.turnRepository = turnRepository;
    }

    public Map<Position, Piece> findAllPiece() {
        return pieceRepository.findAllPiece();
    }

    public Optional<Color> findTurn() {
        return turnRepository.findAny();
    }

    public void saveGame(final Game game) {
        delete();
        saveAllPiece(game.getBoard());
        saveTurn(game.getTurn());
    }

    private void saveAllPiece(final Map<Position, Piece> pieces) {
        pieces.entrySet()
                .forEach(entry -> pieceRepository.save(entry.getValue(), entry.getKey()));
    }

    private void saveTurn(final Color color) {
        turnRepository.save(color.name());
    }

    public void delete() {
        pieceRepository.deleteAll();
        turnRepository.deleteAll();
    }

    public boolean existSavedGame() {
        return pieceRepository.existPieces();
    }
}
