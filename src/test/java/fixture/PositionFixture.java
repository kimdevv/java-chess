package fixture;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

public class PositionFixture {
    public static final Position A1 = position(File.A, Rank.ONE);
    public static final Position A2 = position(File.A, Rank.TWO);
    public static final Position A3 = position(File.A, Rank.THREE);
    public static final Position A4 = position(File.A, Rank.FOUR);
    public static final Position A5 = position(File.A, Rank.FIVE);
    public static final Position A6 = position(File.A, Rank.SIX);
    public static final Position A7 = position(File.A, Rank.SEVEN);
    public static final Position A8 = position(File.A, Rank.EIGHT);
    public static final Position B1 = position(File.B, Rank.ONE);
    public static final Position B2 = position(File.B, Rank.TWO);
    public static final Position B3 = position(File.B, Rank.THREE);
    public static final Position B4 = position(File.B, Rank.FOUR);
    public static final Position B5 = position(File.B, Rank.FIVE);
    public static final Position B6 = position(File.B, Rank.SIX);
    public static final Position B7 = position(File.B, Rank.SEVEN);
    public static final Position B8 = position(File.B, Rank.EIGHT);
    public static final Position C1 = position(File.C, Rank.ONE);
    public static final Position C2 = position(File.C, Rank.TWO);
    public static final Position C3 = position(File.C, Rank.THREE);
    public static final Position C4 = position(File.C, Rank.FOUR);
    public static final Position C5 = position(File.C, Rank.FIVE);
    public static final Position C6 = position(File.C, Rank.SIX);
    public static final Position C7 = position(File.C, Rank.SEVEN);
    public static final Position C8 = position(File.C, Rank.EIGHT);
    public static final Position D1 = position(File.D, Rank.ONE);
    public static final Position D2 = position(File.D, Rank.TWO);
    public static final Position D3 = position(File.D, Rank.THREE);
    public static final Position D4 = position(File.D, Rank.FOUR);
    public static final Position D5 = position(File.D, Rank.FIVE);
    public static final Position D6 = position(File.D, Rank.SIX);
    public static final Position D7 = position(File.D, Rank.SEVEN);
    public static final Position D8 = position(File.D, Rank.EIGHT);
    public static final Position E1 = position(File.E, Rank.ONE);
    public static final Position E2 = position(File.E, Rank.TWO);
    public static final Position E3 = position(File.E, Rank.THREE);
    public static final Position E4 = position(File.E, Rank.FOUR);
    public static final Position E5 = position(File.E, Rank.FIVE);
    public static final Position E6 = position(File.E, Rank.SIX);
    public static final Position E7 = position(File.E, Rank.SEVEN);
    public static final Position E8 = position(File.E, Rank.EIGHT);
    public static final Position F1 = position(File.F, Rank.ONE);
    public static final Position F2 = position(File.F, Rank.TWO);
    public static final Position F3 = position(File.F, Rank.THREE);
    public static final Position F4 = position(File.F, Rank.FOUR);
    public static final Position F5 = position(File.F, Rank.FIVE);
    public static final Position F6 = position(File.F, Rank.SIX);
    public static final Position F7 = position(File.F, Rank.SEVEN);
    public static final Position F8 = position(File.F, Rank.EIGHT);
    public static final Position G1 = position(File.G, Rank.ONE);
    public static final Position G2 = position(File.G, Rank.TWO);
    public static final Position G3 = position(File.G, Rank.THREE);
    public static final Position G4 = position(File.G, Rank.FOUR);
    public static final Position G5 = position(File.G, Rank.FIVE);
    public static final Position G6 = position(File.G, Rank.SIX);
    public static final Position G7 = position(File.G, Rank.SEVEN);
    public static final Position G8 = position(File.G, Rank.EIGHT);
    public static final Position H1 = position(File.H, Rank.ONE);
    public static final Position H2 = position(File.H, Rank.TWO);
    public static final Position H3 = position(File.H, Rank.THREE);
    public static final Position H4 = position(File.H, Rank.FOUR);
    public static final Position H5 = position(File.H, Rank.FIVE);
    public static final Position H6 = position(File.H, Rank.SIX);
    public static final Position H7 = position(File.H, Rank.SEVEN);
    public static final Position H8 = position(File.H, Rank.EIGHT);

    private static Position position(File file, Rank rank) {
        return Position.of(file, rank);
    }
}
