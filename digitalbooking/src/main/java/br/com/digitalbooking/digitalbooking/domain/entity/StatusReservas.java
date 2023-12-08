package br.com.digitalbooking.digitalbooking.domain.entity;

public enum StatusReservas {
    CANCEL("Cancelada"),
    EMSAIDA("Checkout"),
    EMENTRADA("Checkin"),
    VAIVIAJAR("Pendente"),
    USADO("Arquivada");

    private String status;

    StatusReservas(String status){this.status = status;}

    public String getStatus(){return status;}

}
