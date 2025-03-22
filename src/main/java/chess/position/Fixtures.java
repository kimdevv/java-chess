package chess.position;

import java.util.Arrays;

public enum Fixtures {

    A1(new Position(Column.A, Row.ONE), "a1"),
    A2(new Position(Column.A, Row.TWO), "a2"),
    A3(new Position(Column.A, Row.THREE), "a3"),
    A4(new Position(Column.A, Row.FOUR), "a4"),
    A5(new Position(Column.A, Row.FIVE), "a5"),
    A6(new Position(Column.A, Row.SIX), "a6"),
    A7(new Position(Column.A, Row.SEVEN), "a7"),
    A8(new Position(Column.A, Row.EIGHT), "a8"),
    B1(new Position(Column.B, Row.ONE), "b1"),
    B2(new Position(Column.B, Row.TWO), "b2"),
    B3(new Position(Column.B, Row.THREE), "b3"),
    B4(new Position(Column.B, Row.FOUR), "b4"),
    B5(new Position(Column.B, Row.FIVE), "b5"),
    B6(new Position(Column.B, Row.SIX), "b6"),
    B7(new Position(Column.B, Row.SEVEN), "b7"),
    B8(new Position(Column.B, Row.EIGHT), "b8"),
    C1(new Position(Column.C, Row.ONE), "c1"),
    C2(new Position(Column.C, Row.TWO), "c2"),
    C3(new Position(Column.C, Row.THREE), "c3"),
    C4(new Position(Column.C, Row.FOUR), "c4"),
    C5(new Position(Column.C, Row.FIVE), "c5"),
    C6(new Position(Column.C, Row.SIX), "c6"),
    C7(new Position(Column.C, Row.SEVEN), "c7"),
    C8(new Position(Column.C, Row.EIGHT), "c8"),
    D1(new Position(Column.D, Row.ONE), "d1"),
    D2(new Position(Column.D, Row.TWO), "d2"),
    D3(new Position(Column.D, Row.THREE), "d3"),
    D4(new Position(Column.D, Row.FOUR), "d4"),
    D5(new Position(Column.D, Row.FIVE), "d5"),
    D6(new Position(Column.D, Row.SIX), "d6"),
    D7(new Position(Column.D, Row.SEVEN), "d7"),
    D8(new Position(Column.D, Row.EIGHT), "d8"),
    E1(new Position(Column.E, Row.ONE), "e1"),
    E2(new Position(Column.E, Row.TWO), "e2"),
    E3(new Position(Column.E, Row.THREE), "e3"),
    E4(new Position(Column.E, Row.FOUR), "e4"),
    E5(new Position(Column.E, Row.FIVE), "e5"),
    E6(new Position(Column.E, Row.SIX), "e6"),
    E7(new Position(Column.E, Row.SEVEN), "e7"),
    E8(new Position(Column.E, Row.EIGHT), "e8"),
    F1(new Position(Column.F, Row.ONE), "f1"),
    F2(new Position(Column.F, Row.TWO), "f2"),
    F3(new Position(Column.F, Row.THREE), "f3"),
    F4(new Position(Column.F, Row.FOUR), "f4"),
    F5(new Position(Column.F, Row.FIVE), "f5"),
    F6(new Position(Column.F, Row.SIX), "f6"),
    F7(new Position(Column.F, Row.SEVEN), "f7"),
    F8(new Position(Column.F, Row.EIGHT), "f8"),
    G1(new Position(Column.G, Row.ONE), "g1"),
    G2(new Position(Column.G, Row.TWO), "g2"),
    G3(new Position(Column.G, Row.THREE), "g3"),
    G4(new Position(Column.G, Row.FOUR), "g4"),
    G5(new Position(Column.G, Row.FIVE), "g5"),
    G6(new Position(Column.G, Row.SIX), "g6"),
    G7(new Position(Column.G, Row.SEVEN), "g7"),
    G8(new Position(Column.G, Row.EIGHT), "g8"),
    H1(new Position(Column.H, Row.ONE), "h1"),
    H2(new Position(Column.H, Row.TWO), "h2"),
    H3(new Position(Column.H, Row.THREE), "h3"),
    H4(new Position(Column.H, Row.FOUR), "h4"),
    H5(new Position(Column.H, Row.FIVE), "h5"),
    H6(new Position(Column.H, Row.SIX), "h6"),
    H7(new Position(Column.H, Row.SEVEN), "h7"),
    H8(new Position(Column.H, Row.EIGHT), "h8");

    private final Position position;
    private final String text;

    Fixtures(final Position position, final String text) {
        this.position = position;
        this.text = text;
    }

    public static Position positionOf(final String fixtureText) {
        Fixtures fixtures = Arrays.stream(values())
                .filter(value -> value.text.equalsIgnoreCase(fixtureText))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 위치를 입력하셨습니다."));
        return fixtures.position;
    }

    public Position getPosition() {
        return position;
    }
}
