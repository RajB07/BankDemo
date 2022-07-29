package com.example.bankDemo;

import java.util.ArrayList;

public class Customer {

    private String name;
    private String phoneNum;
    private String emailId;
    private String uuid;

    private ArrayList<Accounts> accounts;

    public Customer(String name, String phoneNum, String emailId,String uuid) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.emailId = emailId;
        this.uuid = uuid;
        this.accounts = new ArrayList<Accounts>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ArrayList<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Accounts> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Accounts account){
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
               // ", Accounts='" + accounts + '\'' +
                ", emailId='" + emailId + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

//    public ArrayList<Accounts> getAccountRelatedToCustomer(String uuid){
//
//    }


}
