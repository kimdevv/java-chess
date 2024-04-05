package domain.room;

public class GameRoom {

    public static final int MAX_ROOM_LENGTH = 20;

    private final String name;

    public GameRoom(String name) {
        validateRoomName(name);
        this.name = name;
    }

    private void validateRoomName(String inputRoomName) {
        if (inputRoomName.length() > MAX_ROOM_LENGTH) {
            throw new IllegalArgumentException("설정 가능한 방 이름이 아닙니다.");
        }
    }

    public String getName() {
        return name;
    }
}
