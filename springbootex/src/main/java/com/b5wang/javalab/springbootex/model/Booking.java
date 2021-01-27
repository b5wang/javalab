package com.b5wang.javalab.springbootex.model;

import lombok.Data;

@Data
public class Booking {

    private String ticketId;

    private int amount;

    public Booking(){}

    public Booking(String ticketId,int amount){
        this.ticketId = ticketId;
        this.amount = amount;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{ ticketId: ").append(ticketId).append(", amount: ").append(amount).append("}");
        return sb.toString();
    }
}
