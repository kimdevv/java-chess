package dao.mapper;

import domain.board.File;
import domain.board.Rank;
import domain.piece.Piece;
import domain.position.Position;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDaoMapper {

    public static String boardToData(Map<Position, Piece> board) {
        return board.entrySet().stream()
                .map(entry -> PositionDaoMapper.positionToData(entry.getKey()) + PieceDaoMapper.pieceToData(entry.getValue()))
                .collect(Collectors.joining(","));
    }

    public static Map<Position, Piece> dataToBoard(String gameStatus) {
        Map<Position, Piece> board = new LinkedHashMap<>();

        List<String> dataList = List.of(gameStatus.split(","));
        for (String data : dataList) {
            File file = FileDaoMapper.dataToFile(data.substring(0, 1));
            Rank rank = RankDaoMapper.dataToRank(data.substring(1, 2));
            Position position = Position.valueOf(file, rank);

            Piece piece = PieceDaoMapper.dataToPiece(data.substring(2, 3));

            board.put(position, piece);
        }
        return board;
    }
}
