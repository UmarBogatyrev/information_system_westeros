package ru.itmo.WesterosTax.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_LORD("Лорд"),
    ROLE_LANDOWNER("Землевладелец"),
    ROLE_COURIER("Гонец"),
    ROLE_ADMIN("Админ");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
