package db.entity;

import db.booleanTranslator.BooleanTranslator;
import domain.room.GameRoom;

public class ChessGame {
    private final int isRunning;
    private final String roomName;
    private final String turn;

    public ChessGame(int isRunning, String roomName, String turn) {
        this.isRunning = isRunning;
        this.roomName = roomName;
        this.turn = turn;
    }

    public static ChessGame create(GameRoom gameRoom, String turn) {
        return new ChessGame(BooleanTranslator.translate(false), gameRoom.getName(), turn);
    }

    public String getRoomName() {
        return roomName;
    }

    public String getTurn() {
        return turn;
    }
}
