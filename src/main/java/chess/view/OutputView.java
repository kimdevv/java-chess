package chess.view;

import chess.piece.Piece;
import chess.player.Team;
import chess.position.Position;

import java.util.Arrays;
import java.util.List;

public class OutputView {

    public void outputCurrentChessBoard(List<Piece> blackPieces, List<Piece> whitePieces) {
        String[][] pieceNames = new String[8][8];
        for (String[] pieceName : pieceNames) {
            Arrays.fill(pieceName, "－");
        }
        for (Piece blackPiece : blackPieces) {
            Position position = blackPiece.getPosition();
            pieceNames[position.getRowNumber()][position.getColumnNumber()] = PieceTextMaker.generateBlackTextOf(blackPiece);
        }
        for (Piece whitePiece : whitePieces) {
            Position position = whitePiece.getPosition();
            pieceNames[position.getRowNumber()][position.getColumnNumber()] = PieceTextMaker.generateWhiteTextOf(whitePiece);
        }

        System.out.println("   ＡＢＣＤＥＦＧＨ");
        for (int i=0; i<pieceNames.length; i++) {
            System.out.print(8-i + "  ");
            for (int j=0; j<pieceNames[i].length; j++) {
                System.out.print(pieceNames[i][j]);
            }
            System.out.println();
        }
    }

    public void outputWinnerCongratulation(final Team team) {
        if (team == Team.BLACK) {
            System.out.println("흑 팀이 승리하였습니다.");
        }
        System.out.println("백 팀이 승리하였습니다.");
    }
}
