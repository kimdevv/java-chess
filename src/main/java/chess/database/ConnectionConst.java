package chess.database;

public abstract class ConnectionConst {
    private ConnectionConst() {
    }

    public static final String SERVER = "localhost:13306"; // MySQL 서버 주소
    public static final String DATABASE = "chess"; // MySQL DATABASE 이름
    public static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    public static final String USERNAME = "root"; //  MySQL 서버 아이디
    public static final String PASSWORD = "root"; // MySQL 서버 비밀번호
}
