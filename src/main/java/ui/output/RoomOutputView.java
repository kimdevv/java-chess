package ui.output;

import domain.room.Room;
import java.util.List;

public class RoomOutputView {
    public void printCommandMessage() {
        System.out.println("> 룸 생성 : create 룸 이름 ex. create 초보 환영 (최대 20자)");
        System.out.println("> 룸 삭제 : delete 룸 번호 ex. delete 1");
        System.out.println("> 룸 입장 : enter 룸 번호 ex. enter 1");
        System.out.println("> 룸 조회 : show");
        System.out.println("> 돌아가기 : end");
    }

    public void printRooms(List<Room> rooms) {
        System.out.println("> 룸 목록");
        if (rooms.isEmpty()) {
            System.out.println("룸이 비어있습니다.");
            return;
        }
        for (Room room : rooms) {
            System.out.printf("룸 번호: %d - 룸 이름: %s%n", room.getRoomId(), room.getName());
        }
    }

    public void printCreateRoomMessage(String name) {
        System.out.printf("'%s' 이름으로 룸을 생성하였습니다.%n", name);
    }

    public void printDeleteRoomMessage(int roomId) {
        System.out.printf("룸 번호 %d를 삭제하였습니다.%n", roomId);
    }

    public void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
