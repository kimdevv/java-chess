package chess.domain.initializer;

import chess.domain.dao.PieceDao;
import chess.domain.dto.PieceDto;

public class BoardInitializer {

    private BoardInitializer() {
    }

    public static void initialize(PieceDao pieceDao, Long chess_room_id) {
        placeInitialPieces(pieceDao, chess_room_id);
    }

    private static void placeInitialPieces(PieceDao pieceDao, Long chess_room_id) {
        pieceDao.addPieceByChessRoomId(new PieceDto("a1", "ROOK", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("b1", "KNIGHT", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("c1", "BISHOP", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("d1", "QUEEN", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("e1", "KING", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("f1", "BISHOP", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("g1", "KNIGHT", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("h1", "ROOK", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("a2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("b2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("c2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("d2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("e2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("f2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("g2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("h2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("a8", "ROOK", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("b8", "KNIGHT", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("c8", "BISHOP", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("d8", "QUEEN", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("e8", "KING", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("f8", "BISHOP", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("g8", "KNIGHT", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("h8", "ROOK", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("a7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("b7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("c7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("d7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("e7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("f7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("g7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(new PieceDto("h7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
    }
}
