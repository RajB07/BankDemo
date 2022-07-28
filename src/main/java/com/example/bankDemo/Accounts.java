package com.example.bankDemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Accounts {
    double term;
   private  double balance;
    private String accountNumber;
    private ArrayList<Customer> customers;
    private Date timeStamp;
    private String accountType;
    private double interest;
  //  private Customer holder;
    public Accounts(double balance, double term, double interest,String accountNumber, Date timeStamp,Customer customer, String accountType) {
        this.term = term;
        this.interest = interest;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customers = new ArrayList<Customer>();
        this.timeStamp = new Date();
        this.accountType = accountType;
        this.addCustomer(customer);
        customer.addAccount(this);
    }
    public Accounts(double balance, String accountNumber, Date timeStamp, Customer customer,String accountType, double interest) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.customers = new ArrayList<Customer>();
        this.timeStamp = new Date();
        this.accountType = accountType;
        this.interest = interest;
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

//    public double getTerm() {
//        return term;
//    }

    public double getTerm() {
        return term;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setTerm(double term) {
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
        System.out.println("After crediting money, your balance is : " + this.balance);
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
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

    public void updateWithdrawalBalance(double withdrawAmount) {
        //double currBalance = balance-withdrawAmount;
        this.balance -= withdrawAmount;
        System.out.println("After withdrawal your balance is : "+this.balance);
    }

    public double updateCreditBalance(double balance,double creditAmount) {

        double currBalance = balance + creditAmount;
        return currBalance;
    }

    public void addInterest() {
        long diff = timeStamp.getTime() - new Date().getTime();

        long days = TimeUnit.MILLISECONDS.toDays(diff);
        if(days>365) {
            balance = balance + (balance * interest);

            System.out.println(accountNumber + " " + balance);
        }
        else {
            System.out.println("Cannot add interest ");
        }
    }
}
