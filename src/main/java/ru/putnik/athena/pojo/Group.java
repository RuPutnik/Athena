package ru.putnik.athena.pojo;

/**
 * Создано 19.08.2019 в 22:30
 */
public class Group {
    private Integer number;
    private String name;
    private String address;
    private String login;
    private String password;
    private String email;
    private String comment;

    public Group(Integer number, String name, String address, String login, String password, String email, String comment) {
        this.number = number;
        this.name = name;
        this.address = address;
        this.login = login;
        this.password = password;
        this.email = email;
        this.comment = comment;
    }

    public Group() {}

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
