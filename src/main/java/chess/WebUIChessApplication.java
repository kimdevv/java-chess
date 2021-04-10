package chess;

import chess.domain.board.Board;

import chess.domain.dao.ChessGameDAO;
import chess.domain.dao.PieceDAO;
import chess.domain.dto.ChessBoardDto;
import chess.domain.dto.EndGameDto;
import chess.domain.dto.MessageDto;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameEntity;
import chess.domain.piece.MovePositionInfo;
import chess.domain.piece.PieceFactory;

import chess.domain.piece.Position;
import chess.service.ChessGameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    private static ChessGame chessGame;

    public static void main(String[] args) {
        ChessGameService chessGameService = new ChessGameService(new PieceDAO(), new ChessGameDAO());

        Gson gson = new Gson();
        staticFiles.location("/static");
        get("/", (req, res) -> {
            chessGame = new ChessGame(new Board(PieceFactory.createPieces()));
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            ChessGameEntity chessGameID = mapper.readValue(req.body(), ChessGameEntity.class);
            return gson.toJson(chessGameService.createNewChessGame(chessGameID.getRoomID()));
        });

        post("/move", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            MovePositionInfo mpi = mapper.readValue(req.body(), MovePositionInfo.class);

            ChessBoardDto chessBoardDto = chessGameService.moveChessByRoomID(mpi);
            if("end".equals(chessBoardDto.getStatus())){
                return gson.toJson(new EndGameDto("왕을잡아 게임이 종료됩니다."));
            }
            return gson.toJson(chessBoardDto);
        });

        post("/end", (req, res) -> {
            ObjectMapper mapper = new ObjectMapper();
            ChessGameEntity chessGameID = mapper.readValue(req.body(), ChessGameEntity.class);
            return chessGameService.endChessGame(chessGameID.getRoomID());

        });

        post("/status", (req, res) -> {
            if (chessGame == null || chessGame.isReady() || chessGame.isFinished()) {
                return gson.toJson(new MessageDto("게임을 실행시켜주세요."));
            }
            return gson.toJson(new ChessBoardDto(chessGame));
        });

        post("/save", (req, res) -> {
            PieceDAO pieceDAO = new PieceDAO();
            String roomID = req.queryParams("roomID");
            pieceDAO.saveAll(roomID, chessGame.getBoard().getPieces());
            pieceDAO.saveTurn(chessGame.getStatus());

            return gson.toJson(new ChessBoardDto(chessGame));

        });

        post("/load", (req, res) -> {
            PieceDAO pieceDAO = new PieceDAO();
            if (pieceDAO.loadPieces() == null) {
                return gson.toJson(new MessageDto("저장된 게임이 없습니다. "));
            }
            chessGame = new ChessGame(pieceDAO.loadPieces());
            chessGame.changeState(pieceDAO.loadTurn(chessGame));

            return gson.toJson(new ChessBoardDto(chessGame));
        });

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
