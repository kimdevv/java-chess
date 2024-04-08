package chess.repository.turn;

import chess.domain.piece.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeTurnRepository implements TurnRepository {
    private List<String> colors = new ArrayList<>();

    @Override
    public void save(final String color) {
        colors.add(color);
    }

    @Override
    public Optional<Color> findAny() {
        if (colors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(Color.valueOf(colors.get(0)));
    }

    @Override
    public void deleteAll() {
        colors = new ArrayList<>();
    }
}
