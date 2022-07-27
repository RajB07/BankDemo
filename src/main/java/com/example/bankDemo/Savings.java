package com.example.bankDemo;

import com.example.bankDemo.Accounts;

import java.util.ArrayList;
import java.util.Date;

public  class Savings extends Accounts {


    public Savings(double balance, String accountNumber, String timeStamp, Customer customer,String accountType) {
        super(balance, accountNumber,timeStamp, customer,accountType);
    }

}
