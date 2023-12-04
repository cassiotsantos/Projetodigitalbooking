package br.com.digitalbooking.digitalbooking.domain.entity;

public enum TypeFuncoesUser {

    ADMIN ("admin"),

    USER("USER");

    private String role;

    TypeFuncoesUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
