package chess.repository.turn;

import chess.domain.piece.Color;
import java.util.Optional;

public interface TurnRepository {

    void save(final String color);

    Optional<Color> findAny();

    void deleteAll();
}
