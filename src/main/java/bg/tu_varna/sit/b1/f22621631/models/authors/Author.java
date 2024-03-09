package main.java.bg.tu_varna.sit.b1.f22621631.models.authors;

public class Author {
    private String firstName;
    private String lastName;

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        StringBuilder authorInformation = new StringBuilder();
        authorInformation.append(firstName)
                .append(" ")
                .append(lastName);
        return authorInformation.toString();
    }
}
