package com.ymatin.samlib.dao.author;

public class AuthorDto {

    private Long authorId;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String shortName;
    private String pseudonym;
    private String samlibId;

    public AuthorDto() {
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public String getSamlibId() {
        return samlibId;
    }

    public void setSamlibId(String samlibId) {
        this.samlibId = samlibId;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", pseudonym='" + pseudonym + '\'' +
                ", samlibId='" + samlibId + '\'' +
                '}';
    }
}
