package com.paymybuddy.paymybuddy.transfer.ui;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class SendMoneyForm {

    @NotNull
    private String friend;

    @NotNull
    private BigDecimal amount;

    public SendMoneyForm() {
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
