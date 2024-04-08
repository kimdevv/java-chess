package chess.repository.piece;

import static chess.repository.DatabaseConfig.getConnection;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JdbcPieceRepository implements PieceRepository {

    @Override
    public void save(final Piece piece, final Position position) {
        final var query = "INSERT INTO piece (color, pieceType, `rank`, file) VALUES(?, ?, ?, ?)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.getColor().name());
            preparedStatement.setString(2, piece.getPieceType().name());
            preparedStatement.setInt(3, position.rank().getValue());
            preparedStatement.setInt(4, position.file().getValue());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] save [TABLE] piece" + e.getMessage(), e);
        }
    }

    @Override
    public Map<Position, Piece> findAllPiece() {
        final var query = "SELECT * FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            return mapToPieces(resultSet);
        } catch (final SQLException e) {
            throw new RuntimeException("[METHOD] findAll [TABLE] piece" + e.getMessage(), e);
        }
    }

    private Map<Position, Piece> mapToPieces(final ResultSet resultSet) throws SQLException {
        Map<Position, Piece> pieces = new LinkedHashMap<>();
        while (resultSet.next()) {
            var file = resultSet.getInt("file");
            var rank = resultSet.getInt("rank");
            var color = resultSet.getString("color");
            var pieceType = resultSet.getString("pieceType");

            Position position = Position.of(file, rank);
            Piece piece = PieceFactory.create(PieceType.valueOf(pieceType), Color.valueOf(color));
            pieces.put(position, piece);
        }

        return pieces;
    }

    @Override
    public void deleteAll() {
        final var query = "DELETE FROM piece";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[METHOD] deleteAll [TABLE] piece" + e.getMessage(), e);
        }
    }

    @Override
    public boolean existPieces() {
        final var query = "SELECT EXISTS(SELECT 1 FROM PIECE)";
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            return checkPieceExist(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("[METHOD] existPieces [TABLE] piece" + e.getMessage(), e);
        }
    }

    private boolean checkPieceExist(final ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getBoolean(1);
        }
        return false;
    }
}
