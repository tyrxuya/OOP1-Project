package main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums;

public enum Genre {
    ACTION("Action"),
    CHICK_LIT("Chick lit"),
    CHILDRENS("Children's"),
    CLASSIC("Classic"),
    COMING_OF_AGE("Coming-of-age"),
    CONTEMPORARY("Contemporary"),
    DYSTOPIAN("Dystopian"),
    FAIRY_TALE("Fairy tale"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    MYSTERY("Mystery"),
    POETRY("Poetry"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science fiction"),
    SHORT_STORY("Short story"),
    THRILLER("Thriller"),


    BIOGRAPHY("Biography"),
    PSYCHOLOGY("Psychology"),
    RELIGION("Religion"),
    SELF_HELP("Self-help"),
    TAROT("Tarot"),
    TEXTBOOK("Textbook");

    private final String genre;

    private Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
