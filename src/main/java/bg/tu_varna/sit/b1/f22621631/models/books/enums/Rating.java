package bg.tu_varna.sit.b1.f22621631.models.books.enums;

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

    private Rating(Double rating) {
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }
}
