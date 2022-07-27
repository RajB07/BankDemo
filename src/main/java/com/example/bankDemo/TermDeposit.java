package com.example.bankDemo;

import com.example.bankDemo.Accounts;

import java.util.ArrayList;
import java.util.Date;

public class TermDeposit extends Accounts {
    public TermDeposit(double balance, String accountNumber, String timeStamp, Customer customer,String accountType) {
        super(balance, accountNumber, timeStamp, customer,accountType);
    }



}
