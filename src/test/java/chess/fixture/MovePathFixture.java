package chess.fixture;

import chess.domain.route.Route;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.Arrays;

public class MovePathFixture {

    public static Route noPieces() {
        return new Route(new ArrayList<>(), new Empty());
    }

    public static Route hasPathPieces(Piece... pieces) {
        return new Route(Arrays.asList(pieces), new Empty());
    }

    public static Route hasTargetPiece(Piece target) {
        return new Route(new ArrayList<>(), target);
    }
}
