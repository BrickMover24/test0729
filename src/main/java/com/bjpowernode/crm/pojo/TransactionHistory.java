package com.bjpowernode.crm.pojo;

import lombok.Data;

import java.util.PrimitiveIterator;

@Data
public class TransactionHistory {
    private String id;
    private String stage;
    private String amountOfMoney;
    private String expectedClosingDate;
    private String editTime;
    private String editBy;
    private String transactionId;


}
