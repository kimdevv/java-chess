package application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.room.Room;
import java.util.List;
import org.junit.jupiter.api.Test;
import support.FakeRoomDao;

class RoomServiceTest {

    private final RoomService roomService = new RoomService(new FakeRoomDao());

    @Test
    void 룸을_저장한다() {

        roomService.save("초보만");

        List<Room> rooms = roomService.findAll();
        assertThat(rooms.get(0).getName()).isEqualTo("초보만");
    }

    @Test
    void 모든_룸을_찾는다() {
        roomService.save("초고수 환영");
        roomService.save("초보만");
        roomService.save("체스마스터");

        List<Room> rooms = roomService.findAll();

        assertThat(rooms).hasSize(3);
    }

    @Test
    void 룸을_삭제한다() {
        roomService.save("초보만");
        roomService.save("체스마스터");

        roomService.deleteById(1);

        List<Room> rooms = roomService.findAll();
        assertThat(rooms).hasSize(1);
    }

    @Test
    void 존재하지_않는_룸을_삭제하려고_하면_예외가_발생한다() {
        roomService.save("초보만");

        assertThatThrownBy(() -> roomService.deleteById(2))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 룸입니다.");
    }
}
