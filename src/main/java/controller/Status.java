package controller;

public enum Status {
    READY, RUNNING, END;

    public boolean isContinue() {
        return this != Status.END;
    }
}
