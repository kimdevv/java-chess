package ui.output;

public class GateOutputView {
    public void printStartMessage() {
        System.out.println("> 체스 게임에 오신 것을 환영합니다.");
    }

    public void printCommandMessage() {
        System.out.println("> 룸 관리 : room");
        System.out.println("> 나가기 : end");
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
