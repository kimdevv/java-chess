package chess.view;

import chess.piece.Piece;
import chess.player.Team;

import java.util.Scanner;

public class InputView {

    private Scanner scanner = new Scanner(System.in);

    public String inputPiecePositionToMove(final Team team) {
        if (team == Team.BLACK) {
            System.out.println("흑 팀의 차례입니다. 이동하실 기물의 위치를 선택해 주세요. (ex: b7)");
            return scanner.nextLine();
        }
        System.out.println("백 팀의 차례입니다. 이동하실 기물의 위치를 선택해 주세요. (ex: b2)");
        return scanner.nextLine();
    }

    public String inputDestinationToMove(final Piece piece, final Team team) {
        if (team == Team.BLACK) {
            System.out.println(String.format("%s를 선택하셨습니다. 어디로 이동하시겠습니까? (ex: b7)", PieceTextMaker.generateBlackTextOf(piece)));
            return scanner.nextLine();
        }
        System.out.println(String.format("%s를 선택하셨습니다. 어디로 이동하시겠습니까? (ex: b7)", PieceTextMaker.generateWhiteTextOf(piece)));
        return scanner.nextLine();
    }
}
