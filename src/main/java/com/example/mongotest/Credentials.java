package com.example.mongotest;

import org.springframework.data.annotation.Id;

public class Credentials {
    @Id
    public String id;

    public String email;
    public String password;

    public Credentials() {}

    public Credentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format(
                "Credentials[id=%s, email='%s', password='%s']",
                id, email, password);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
