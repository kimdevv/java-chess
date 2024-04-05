package db.booleanTranslator;

public enum BooleanTranslator {

    TRUE(true, 1),
    FALSE(false, 0);

    private final boolean booleanValue;
    private final int tinyInt;

    BooleanTranslator(boolean booleanValue, int tinyInt) {
        this.booleanValue = booleanValue;
        this.tinyInt = tinyInt;
    }

    public static int translate(boolean booleanValue) {
        if (TRUE.booleanValue == booleanValue) {
            return TRUE.tinyInt;
        }
        return FALSE.tinyInt;
    }
}
