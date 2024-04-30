package main.java.bg.tu_varna.sit.b1.f22621631.models.books;

import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;

public class Book {
    private Author author;
    private String title;
    private Genre genre;
    private String description;
    private Integer publishingYear;
    private String keyWords;
    private Rating rating;
    private String isbn;

    private Book(Builder builder) {
        this.author = builder.author;
        this.title = builder.title;
        this.genre = builder.genre;
        this.description = builder.description;
        this.publishingYear = builder.publishingYear;
        this.keyWords = builder.keyWords;
        this.rating = builder.rating;
        this.isbn = builder.isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPublishingYear() {
        return publishingYear;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public Rating getRating() {
        return rating;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        StringBuilder bookInformation = new StringBuilder();

        bookInformation.append("Author: ")
                .append(author.toString())
                .append("\nTitle: ")
                .append(title)
                .append(genre == null ? "" : "\nGenre: ")
                .append(genre == null ? "" : genre.getText())
                .append(description == null || description.isBlank() ? "" : "\nDescription: ")
                .append(description == null || description.isBlank() ? "" : description)
                .append("\nYear published: ")
                .append(publishingYear)
                .append(keyWords == null || keyWords.isEmpty() ? "" : "\nKey words: ")
                .append(keyWords == null || keyWords.isEmpty() ? "" : keyWords)
                .append(rating == null ? "" : "\nRating: ")
                .append(rating == null ? "" : rating.getText())
                .append("\nISBN: ")
                .append(isbn);

        return bookInformation.toString();
    }

    public static class Builder {
        private Author author;
        private String title;
        private Genre genre;
        private String description;
        private Integer publishingYear;
        private String keyWords;
        private Rating rating;
        private String isbn;

        public Builder(Author author, String title, Integer publishingYear, String isbn) {
            this.author = author;
            this.title = title;
            this.publishingYear = publishingYear;
            this.isbn = isbn;
        }

        public Builder genre(Genre genre) {
            this.genre = genre;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder keyWords(String keyWords) {
            this.keyWords = keyWords;
            return this;
        }

        public Builder rating(Rating rating) {
            this.rating = rating;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
