package com.ymatin.samlib.model;

// TODO: 14.08.2018 добавить что-то вроде ID


import java.util.Date;

public class Author {

    /***/
    private String firstName;
    /***/
    private String lastName;
    /***/
    private String alias;
    /***/
    private UserStatus userStatus;
    /***/
    private AuthorInfo authorInfo;
    /***/
    private Date birthDate;

    public Author() {}

    public Author(String alias, UserStatus userStatus) {
        this.alias = alias;
        this.userStatus = userStatus;
    }

    public Author(String firstName, String lastName, String alias, UserStatus userStatus, AuthorInfo authorInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.userStatus = userStatus;
        this.authorInfo = authorInfo;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public AuthorInfo getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(AuthorInfo authorInfo) {
        this.authorInfo = authorInfo;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", alias='" + alias + '\'' +
                ", userStatus=" + userStatus +
                ", authorInfo=" + authorInfo +
                '}';
    }
}
