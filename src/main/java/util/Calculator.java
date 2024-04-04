package util;

public class Calculator {

    private Calculator() {
    }

    /**
     * @param value 계산할 값
     * @return value가 0이면 0, 음수이면 -1, 양수이면 1
     */
    public static int normalize(int value) {
        if (value == 0) {
            return value;
        }
        return value / Math.abs(value);
    }
}
