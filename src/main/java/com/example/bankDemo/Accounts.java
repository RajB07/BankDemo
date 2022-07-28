package com.example.bankDemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Accounts {
    int term;
    private double balance=0.00;
    private String accountNumber;
    private ArrayList<Customer> customers;
    private String timeStamp;
    private String accountType;

  //  private Customer holder;
    public Accounts(int term, double balance, String accountNumber, ArrayList<Customer> customers, String timeStamp, String accountType) {
        this.term = term;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customers = customers;
        this.timeStamp = timeStamp;
        this.accountType = accountType;
    }
    public Accounts(double balance, String accountNumber, String timeStamp, Customer customer,String accountType) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customers = new ArrayList<Customer>();
        this.timeStamp = timeStamp;
        this.accountType = accountType;

//        customers.add(customer);
        this.addCustomer(customer);
        customer.addAccount(this);
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", customers'" + customers + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }

    public void updateWithdrawalBalance(double balance, double withdrawAmount) {
        double currBalance = balance-withdrawAmount;
        System.out.println("After withdrawal your balance is : "+currBalance);
    }

    public void updateCreditBalance(double balance,double creditAmount) {
        double currBalance = balance + creditAmount;
        System.out.println("After withdrawal your balance is : " + currBalance);
    }
}
