package database.exception;

public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException(String message) {
        super("DB 연결 오류: " + message);
    }
}
