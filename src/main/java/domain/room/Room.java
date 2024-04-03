package domain.room;

public class Room {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 20;

    private final int roomId;
    private final String name;

    public Room(String name) {
        this(0, name);
    }

    public Room(int roomId, String name) {
        validateEmptyName(name);
        validateNameLength(name);
        this.roomId = roomId;
        this.name = name;
    }

    private void validateEmptyName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("룸 이름이 비어있습니다.");
        }
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH || MAX_NAME_LENGTH < name.length()) {
            throw new IllegalArgumentException(
                    String.format("룸 이름은 %d자 이상 %d자 이하여야 합니다.", MIN_NAME_LENGTH, MAX_NAME_LENGTH));
        }
    }

    public int getRoomId() {
        return this.roomId;
    }

    public String getName() {
        return this.name;
    }
}
