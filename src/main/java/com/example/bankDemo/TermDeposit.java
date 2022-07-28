package com.example.bankDemo;

import java.util.Date;

public class TermDeposit extends Accounts {


    public TermDeposit(double balance, double term, double interest, String accountNumber, Date timeStamp, Customer customer, String accountType) {
        super(balance,term,interest, accountNumber, timeStamp, customer,accountType );

        //this.setTimeStamp(timeStamp);
    }

    }

