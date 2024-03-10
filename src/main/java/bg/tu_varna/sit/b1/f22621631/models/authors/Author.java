package main.java.bg.tu_varna.sit.b1.f22621631.models.authors;

public class Author {
    private String firstName;
    private String lastName;
    private String country;

    public Author(String firstName, String lastName, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        StringBuilder authorInformation = new StringBuilder();
        authorInformation.append(firstName)
                .append(" ")
                .append(lastName)
                .append(", ")
                .append(country);
        return authorInformation.toString();
    }
}
