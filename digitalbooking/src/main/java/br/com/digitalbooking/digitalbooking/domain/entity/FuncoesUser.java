package br.com.digitalbooking.digitalbooking.domain.entity;

public enum FuncoesUser {

    ADMIN ("admin"),

    USER("USER");

    private String role;

    FuncoesUser(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
