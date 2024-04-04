package view.mapper;

import domain.Side;

import java.util.Arrays;

public enum SideMapper {

    BLACK(Side.BLACK, "블랙"),
    WHITE(Side.WHITE, "화이트"),
    NONE(Side.NONE, "무승부"),
    ;

    private final Side side;
    private final String name;

    SideMapper(Side side, String name) {
        this.side = side;
        this.name = name;
    }

    public static String toName(Side other) {
        return Arrays.stream(SideMapper.values())
                .filter(value -> value.side == other)
                .findFirst()
                .map(value -> value.name)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 side가 없습니다."));
    }
}
