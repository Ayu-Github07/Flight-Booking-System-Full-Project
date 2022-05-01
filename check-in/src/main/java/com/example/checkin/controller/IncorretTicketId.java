package com.example.checkin.controller;

public class IncorretTicketId extends Exception {

    private static String msg;

    IncorretTicketId() {
        super(msg);
    }
}
