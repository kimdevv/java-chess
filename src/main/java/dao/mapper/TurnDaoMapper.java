package dao.mapper;

import domain.Side;
import domain.Turn;

import java.util.Arrays;
import java.util.function.Supplier;

public enum TurnDaoMapper {

    BLACK("BLACK", () -> new Turn(Side.BLACK)),
    WHITE("WHITE", () -> new Turn(Side.WHITE)),
    ;

    private final String side;
    private final Supplier<Turn> supplier;

    TurnDaoMapper(String side, Supplier<Turn> supplier) {
        this.side = side;
        this.supplier = supplier;
    }

    public static Turn dataToTurn(String data) {
        return Arrays.stream(values())
                .filter(turn -> data.equals(turn.side))
                .findFirst()
                .map(turn -> turn.supplier.get())
                .orElseThrow(() -> new IllegalArgumentException("DB의 데이터와 일치하는 Side가 없습니다."));
    }

    public static String turnToData(Turn turn) {
        return Arrays.stream(values())
                .filter(mapper -> turn.equals(mapper.supplier.get()))
                .findFirst()
                .map(mapper -> mapper.side)
                .orElseThrow();
    }
}
