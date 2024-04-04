package view;

import domain.Side;
import domain.board.ChessBoard;
import domain.board.File;
import domain.board.Rank;
import domain.piece.Piece;
import domain.position.Position;
import domain.status.GameStatus;
import view.mapper.PieceMapper;
import view.mapper.SideMapper;

public class MessageResolver {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY = ".";

    public String resolveChessBoardMessage(ChessBoard chessBoard) {
        StringBuilder message = new StringBuilder();
        for (int vertical = File.max(); vertical > 0; vertical--) {
            StringBuilder rank = resolveRankMessage(Rank.find(vertical), chessBoard);
            message.append(rank);
        }
        return message.toString();
    }

    private StringBuilder resolveRankMessage(Rank rank, ChessBoard chessBoard) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int horizontal = 1; horizontal <= File.max(); horizontal++) {
            Position position = Position.valueOf(File.find(horizontal), rank);
            String square = resolveSquareMessage(chessBoard, position);
            stringBuilder.append(square);
        }
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder;
    }

    private String resolveSquareMessage(ChessBoard chessBoard, Position position) {
        if (chessBoard.hasPiece(position)) {
            Piece piece = chessBoard.findPiece(position);
            return PieceMapper.toSymbol(piece);
        }
        return EMPTY;
    }

    public String resolveStatusMessage(GameStatus gameStatus) {
        return String.format("%s팀 스코어: %.1f" + LINE_SEPARATOR, SideMapper.toName(Side.BLACK), gameStatus.blackScore()) +
                String.format("%s팀 스코어: %.1f" + LINE_SEPARATOR, SideMapper.toName(Side.WHITE), gameStatus.whiteScore()) +
                String.format("승리 팀: %s" + LINE_SEPARATOR, SideMapper.toName(gameStatus.winner()));
    }

    public String resolveLoadGameMessage(String roomName) {
        return "기존의 게임 " + roomName + "을(를) 불러옵니다." + LINE_SEPARATOR;
    }

    public String resolveNewGameMessage(String roomName) {
        return "새로운 게임 " + roomName + "을(를) 생성합니다." + LINE_SEPARATOR;
    }
}
