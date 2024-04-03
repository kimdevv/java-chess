package chess.service;

import chess.domain.ChessGame;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Score;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.repository.PieceRepository;
import chess.repository.TurnRepository;
import chess.repository.entity.PieceEntity;
import chess.repository.mapper.ChessBoardMapper;
import chess.service.dto.BoardDto;
import chess.service.dto.ScoreStatusDto;
import java.util.List;

public class ChessGameService {
    private final PieceRepository pieceRepository;
    private final TurnRepository turnRepository;

    public ChessGameService(PieceRepository pieceRepository, TurnRepository turnRepository) {
        this.pieceRepository = pieceRepository;
        this.turnRepository = turnRepository;
    }

    public BoardDto startChessGame() {
        if (isChessGameInProgress()) {
            return BoardDto.from(loadChessGame().getChessBoard());
        }
        return createNewChessGame();
    }

    public BoardDto movePiece(Position start, Position destination) {
        ChessGame chessGame = loadChessGame();
        chessGame.move(start, destination);
        saveChessGame(chessGame);
        return BoardDto.from(chessGame.getChessBoard());
    }

    public ScoreStatusDto calculateScoreStatus() {
        ChessGame chessGame = loadChessGame();
        Score blackScore = chessGame.calculateTeamScore(Team.BLACK);
        Score whiteScore = chessGame.calculateTeamScore(Team.WHITE);
        return ScoreStatusDto.of(whiteScore, blackScore, chessGame.selectHigherScoreTeam());
    }

    public ChessGame loadChessGame() {
        List<PieceEntity> pieceEntities = pieceRepository.findAll()
                .orElseThrow(() -> new IllegalStateException("진행 중인 체스 게임이 존재하지 않습니다"));
        ChessBoard chessBoard = ChessBoardMapper.mapToBoard(pieceEntities);
        Team currentTurn = turnRepository.findCurrentTurn()
                .orElseThrow(() -> new IllegalStateException("진행 중인 체스 게임이 존재하지 않습니다"));
        return new ChessGame(chessBoard, currentTurn);
    }

    public boolean isChessGameNotEnd() {
        return loadChessGame().isNotEnd();
    }

    private BoardDto createNewChessGame() {
        ChessGame newChessGame = ChessGame.createNewChessGame();
        saveChessGame(newChessGame);
        return BoardDto.from(newChessGame.getChessBoard());
    }

    private void saveChessGame(ChessGame chessGame) {
        deleteSavedChessGame();
        ChessBoard chessBoard = chessGame.getChessBoard();
        pieceRepository.savePieces(ChessBoardMapper.mapToEntities(chessBoard));
        turnRepository.saveTurn(chessGame.getTurn());
    }

    private void deleteSavedChessGame() {
        pieceRepository.deleteAll();
        turnRepository.deleteAll();
    }

    private boolean isChessGameInProgress() {
        return pieceRepository.findAll().isPresent();
    }
}
