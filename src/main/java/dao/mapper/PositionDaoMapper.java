package dao.mapper;

import domain.board.File;
import domain.board.Rank;
import domain.position.Position;

import java.util.Arrays;

public enum PositionDaoMapper {

    A1(Position.valueOf(File.A, Rank.ONE), "a1"),
    A2(Position.valueOf(File.A, Rank.TWO), "a2"),
    A3(Position.valueOf(File.A, Rank.THREE), "a3"),
    A4(Position.valueOf(File.A, Rank.FOUR), "a4"),
    A5(Position.valueOf(File.A, Rank.FIVE), "a5"),
    A6(Position.valueOf(File.A, Rank.SIX), "a6"),
    A7(Position.valueOf(File.A, Rank.SEVEN), "a7"),
    A8(Position.valueOf(File.A, Rank.EIGHT), "a8"),

    B1(Position.valueOf(File.B, Rank.ONE), "b1"),
    B2(Position.valueOf(File.B, Rank.TWO), "b2"),
    B3(Position.valueOf(File.B, Rank.THREE), "b3"),
    B4(Position.valueOf(File.B, Rank.FOUR), "b4"),
    B5(Position.valueOf(File.B, Rank.FIVE), "b5"),
    B6(Position.valueOf(File.B, Rank.SIX), "b6"),
    B7(Position.valueOf(File.B, Rank.SEVEN), "b7"),
    B8(Position.valueOf(File.B, Rank.EIGHT), "b8"),

    C1(Position.valueOf(File.C, Rank.ONE), "c1"),
    C2(Position.valueOf(File.C, Rank.TWO), "c2"),
    C3(Position.valueOf(File.C, Rank.THREE), "c3"),
    C4(Position.valueOf(File.C, Rank.FOUR), "c4"),
    C5(Position.valueOf(File.C, Rank.FIVE), "c5"),
    C6(Position.valueOf(File.C, Rank.SIX), "c6"),
    C7(Position.valueOf(File.C, Rank.SEVEN), "c7"),
    C8(Position.valueOf(File.C, Rank.EIGHT), "c8"),

    D1(Position.valueOf(File.D, Rank.ONE), "d1"),
    D2(Position.valueOf(File.D, Rank.TWO), "d2"),
    D3(Position.valueOf(File.D, Rank.THREE), "d3"),
    D4(Position.valueOf(File.D, Rank.FOUR), "d4"),
    D5(Position.valueOf(File.D, Rank.FIVE), "d5"),
    D6(Position.valueOf(File.D, Rank.SIX), "d6"),
    D7(Position.valueOf(File.D, Rank.SEVEN), "d7"),
    D8(Position.valueOf(File.D, Rank.EIGHT), "d8"),

    E1(Position.valueOf(File.E, Rank.ONE), "e1"),
    E2(Position.valueOf(File.E, Rank.TWO), "e2"),
    E3(Position.valueOf(File.E, Rank.THREE), "e3"),
    E4(Position.valueOf(File.E, Rank.FOUR), "e4"),
    E5(Position.valueOf(File.E, Rank.FIVE), "e5"),
    E6(Position.valueOf(File.E, Rank.SIX), "e6"),
    E7(Position.valueOf(File.E, Rank.SEVEN), "e7"),
    E8(Position.valueOf(File.E, Rank.EIGHT), "e8"),

    F1(Position.valueOf(File.F, Rank.ONE), "f1"),
    F2(Position.valueOf(File.F, Rank.TWO), "f2"),
    F3(Position.valueOf(File.F, Rank.THREE), "f3"),
    F4(Position.valueOf(File.F, Rank.FOUR), "f4"),
    F5(Position.valueOf(File.F, Rank.FIVE), "f5"),
    F6(Position.valueOf(File.F, Rank.SIX), "f6"),
    F7(Position.valueOf(File.F, Rank.SEVEN), "f7"),
    F8(Position.valueOf(File.F, Rank.EIGHT), "f8"),

    G1(Position.valueOf(File.G, Rank.ONE), "g1"),
    G2(Position.valueOf(File.G, Rank.TWO), "g2"),
    G3(Position.valueOf(File.G, Rank.THREE), "g3"),
    G4(Position.valueOf(File.G, Rank.FOUR), "g4"),
    G5(Position.valueOf(File.G, Rank.FIVE), "g5"),
    G6(Position.valueOf(File.G, Rank.SIX), "g6"),
    G7(Position.valueOf(File.G, Rank.SEVEN), "g7"),
    G8(Position.valueOf(File.G, Rank.EIGHT), "g8"),

    H1(Position.valueOf(File.H, Rank.ONE), "h1"),
    H2(Position.valueOf(File.H, Rank.TWO), "h2"),
    H3(Position.valueOf(File.H, Rank.THREE), "h3"),
    H4(Position.valueOf(File.H, Rank.FOUR), "h4"),
    H5(Position.valueOf(File.H, Rank.FIVE), "h5"),
    H6(Position.valueOf(File.H, Rank.SIX), "h6"),
    H7(Position.valueOf(File.H, Rank.SEVEN), "h7"),
    H8(Position.valueOf(File.H, Rank.EIGHT), "h8"),
    ;

    private final Position position;
    private final String symbol;

    PositionDaoMapper(Position position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }

    public static String positionToData(Position position) {
        return Arrays.stream(values())
                .filter(positionDaoMapper -> position.equals(positionDaoMapper.position))
                .findFirst()
                .map(positionDaoMapper -> positionDaoMapper.symbol)
                .orElseThrow(() -> new IllegalArgumentException("data로 변환 가능한 position이 아닙니다."));
    }
}
