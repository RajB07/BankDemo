package com.example.bankDemo;

import java.util.Date;

public  class Savings extends Accounts {


    public Savings(double balance, String accountNumber, Date timeStamp, Customer customer, String accountType,double interest) {
        super(balance, accountNumber,timeStamp, customer,accountType,interest);
    }

}
