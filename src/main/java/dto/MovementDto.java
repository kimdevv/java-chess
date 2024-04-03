package dto;

import java.util.List;

public record MovementDto(String source, String target) {
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;

    public static MovementDto from(List<String> commands) {
        String source = commands.get(SOURCE_INDEX);
        String target = commands.get(TARGET_INDEX);
        return new MovementDto(source, target);
    }
}
