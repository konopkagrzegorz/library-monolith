package pl.edu.pk.ztp.librarymonolith.model;

import org.springframework.context.annotation.Bean;

public enum Role {
    USER("user"),
    ADMIN("admin");

    private String title;

    Role(String title) {
        this.title = title;
    }
}
