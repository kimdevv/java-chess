package chess.dao;

import chess.dto.PieceDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceSimpleDao implements PieceDao {
    private Map<String, PieceDto> store = new HashMap<>();

    @Override
    public void save(final PieceDto piece) {
        store.put(toKey(piece), piece);
    }

    private String toKey(final PieceDto piece) {
        return piece.file() + piece.rank();
    }

    @Override
    public PieceDto findOne(final String file, final String rank) {
        return store.get(file + rank);
    }

    @Override
    public List<PieceDto> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteAll() {
        store.clear();
    }

    @Override
    public boolean hasRecords() {
        return !store.isEmpty();
    }
}
