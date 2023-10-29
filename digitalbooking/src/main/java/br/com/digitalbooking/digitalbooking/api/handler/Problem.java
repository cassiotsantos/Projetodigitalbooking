package br.com.digitalbooking.digitalbooking.api.handler;

public record Problem(Integer status,
                      String message,
                      String detail) {
}
