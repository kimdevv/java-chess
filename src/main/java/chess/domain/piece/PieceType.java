package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.movement.direction.DownDirection;
import chess.domain.movement.direction.DownLeftDirection;
import chess.domain.movement.direction.DownRightDirection;
import chess.domain.movement.direction.KnightDirection;
import chess.domain.movement.direction.LeftDirection;
import chess.domain.movement.direction.RightDirection;
import chess.domain.movement.direction.UpDirection;
import chess.domain.movement.direction.UpLeftDirection;
import chess.domain.movement.direction.UpRightDirection;
import chess.domain.movement.policy.ColorPolicy;
import chess.domain.movement.policy.CombinationPolicy;
import chess.domain.movement.policy.EnemyExistPolicy;
import chess.domain.movement.policy.NoRestrictionPolicy;
import chess.domain.movement.policy.PawnFirstMovePolicy;
import chess.domain.position.Position;
import chess.domain.scorerule.NoScoreRule;
import chess.domain.scorerule.NormalScoreRule;
import chess.domain.scorerule.PawnScoreRule;
import chess.domain.scorerule.ScoreRule;
import java.util.List;

public enum PieceType {
    KING(new NoScoreRule(), new Movement(new NoRestrictionPolicy(), new UpDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(1)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(1)),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(1)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(1))
    ),

    QUEEN(new NormalScoreRule(9), new Movement(new NoRestrictionPolicy(), new UpDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(8)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(8))
    ),

    BISHOP(new NormalScoreRule(3), new Movement(new NoRestrictionPolicy(), new UpLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new UpRightDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownLeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownRightDirection(8))
    ),

    ROOK(new NormalScoreRule(5), new Movement(new NoRestrictionPolicy(), new UpDirection(8)),
            new Movement(new NoRestrictionPolicy(), new DownDirection(8)),
            new Movement(new NoRestrictionPolicy(), new LeftDirection(8)),
            new Movement(new NoRestrictionPolicy(), new RightDirection(8))
    ),

    KNIGHT(new NormalScoreRule(2.5), new Movement(new NoRestrictionPolicy(), new KnightDirection())
    ),

    PAWN(new PawnScoreRule(),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new PawnFirstMovePolicy()),
                    new UpDirection(2)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new EnemyExistPolicy()),
                    new UpLeftDirection(1)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.WHITE), new EnemyExistPolicy()),
                    new UpRightDirection(1)),
            new Movement(new ColorPolicy(Color.WHITE), new UpDirection(1)),

            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new PawnFirstMovePolicy()),
                    new DownDirection(2)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new EnemyExistPolicy()),
                    new DownLeftDirection(1)),
            new Movement(new CombinationPolicy(new ColorPolicy(Color.BLACK), new EnemyExistPolicy()),
                    new DownRightDirection(1)),
            new Movement(new ColorPolicy(Color.BLACK), new DownDirection(1))
    ),

    EMPTY(new NoScoreRule()),
    ;

    private final ScoreRule scoreRule;
    private final List<Movement> movements;

    PieceType(final ScoreRule scoreRule, final Movement... movements) {
        this.scoreRule = scoreRule;
        this.movements = List.of(movements);
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public double findScore(final List<Position> piecesPosition) {
        return scoreRule.findScore(piecesPosition);
    }
}
