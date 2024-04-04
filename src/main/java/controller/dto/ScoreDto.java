package controller.dto;

import domain.piece.Color;

public record ScoreDto(String colorName, double score) {

    public static ScoreDto from(Color color, double score) {
        return new ScoreDto(color.name(), score);
    }
}
