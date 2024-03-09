package bg.tu_varna.sit.b1.f22621631.models.books.enums;

public enum Genre {
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science fiction"),
    DYSTOPIAN("Dystopian"),
    ACTION("Action"),
    MYSTERY("Mystery"),
    HORROR("Horror"),
    THRILLER("Thriller"),
    HISTORICAL("Historical"),
    ROMANCE("Romance"),
    CONTEMPORARY("Contemporary"),
    CHILDRENS("Children's"),
    CHICK_LIT("Chick lit"),
    COMING_OF_AGE("Coming-of-age"),
    POETRY("Poetry"),


    BIOGRAPHY("Biography"),
    PSYCHOLOGY("Psychology"),
    SELF_HELP("Self-help"),
    TAROT("Tarot"),
    RELIGION("Religion"),
    TEXTBOOK("Textbook");

    private final String genre;

    private Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
