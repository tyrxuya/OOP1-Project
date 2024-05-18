package main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books.InvalidRatingException;

public enum Rating {
    NO_RATING(0.0),
    ONE(1.0),
    ONE_POINT_FIVE(1.5),
    TWO(2.0),
    TWO_POINT_FIVE(2.5),
    THREE(3.0),
    THREE_POINT_FIVE(3.5),
    FOUR(4.0),
    FOUR_POINT_FIVE(4.5),
    FIVE(5.0);

    private final Double rating;

    Rating(Double rating) {
        this.rating = rating;
    }

    public String getText() {
        return rating.toString();
    }

    public static Rating getRating(String rating) {
        return switch (rating) {
            case "0.0", "0" -> Rating.NO_RATING;
            case "1.0", "1" -> Rating.ONE;
            case "1.5" -> Rating.ONE_POINT_FIVE;
            case "2.0", "2" -> Rating.TWO;
            case "2.5" -> Rating.TWO_POINT_FIVE;
            case "3.0", "3" -> Rating.THREE;
            case "3.5" -> Rating.THREE_POINT_FIVE;
            case "4.0", "4" -> Rating.FOUR;
            case "4.5" -> Rating.FOUR_POINT_FIVE;
            case "5.0", "5" -> Rating.FIVE;
            default -> throw new InvalidRatingException("Unexpected value: " + rating); //InvalidRatingException
        };
    }
}
