package br.com.digitalbooking.digitalbooking.domain.entity;

public enum StatusResersas {
    CANCEL("Cancelada"),
    EMSAIDA("Checkout"),
    EMENTRADA("Checkin"),
    VAIVIAJAR("Pendente"),
    USADO("Arquivada");

    private String status;

    StatusResersas(String status){this.status = status;}

    public String getStatus(){return status;}

}
