package chess.fen.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import chess.fen.Fen;

public class FenDao {

    private static final String SERVER = "jdbc:mysql://localhost:13306/"; // MySQL 서버 주소
    private static final String DATABASE = "chess"; // MySQL DATABASE 이름
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번호

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS fen ("
            + "id INT PRIMARY KEY,"
            + "value VARCHAR(72) NOT NULL"
            + ")";

    public FenDao() {
        initialize();
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(SERVER + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void initialize() {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_SQL);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public Fen find(final int id) {
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement("SELECT value FROM fen WHERE id=?");
            statement.setInt(1, id);
            final var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Fen(resultSet.getString("value"));
            }
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void save(final int id, final Fen fen) {
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement("INSERT INTO fen VALUES (?, ?)");
            statement.setInt(1, id);
            statement.setString(2, fen.value());
            statement.execute();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(final int id, final Fen fen) {
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement("UPDATE fen SET value=? WHERE id=?");
            statement.setString(1, fen.value());
            statement.setInt(2, id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete() {
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement("DELETE FROM fen");
            statement.execute();
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean exists(final int id) {
        try (final var connection = getConnection()) {
            final var statement = connection.prepareStatement("SELECT EXISTS (SELECT ? FROM fen)");
            statement.setInt(1, id);
            final var resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
