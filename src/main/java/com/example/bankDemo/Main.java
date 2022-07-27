package com.example.bankDemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;


public class Main {
    static ArrayList<Customer> customers;
    static ArrayList<Accounts> accounts;

    public static void main(String[] args) {

        customers = new ArrayList<>();
        accounts = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        Customer c = createCustomer("Raj","982912","test@test");
        Customer c1 = createCustomer("Aftab","9821","test@test1");

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String currDateTime = dateFormat.format(date);
        Accounts a = createAccount(1200.67,currDateTime, c);
        Savings savings = new Savings(0,"1","2",c,"savings");
        //System.out.println(savings.getClass().getName());
//        for(Accounts c2: accounts){
//            System.out.println(c2);
//        }
        System.out.println("Enter uuid to link");
        String uuid = sc.nextLine();
        System.out.println("Enter acc num to link");
        String acctId =sc.nextLine();

        Accounts accLink = getAccObj(acctId);
        Customer cusLink = getCustObj(uuid);
        linkAccount(accLink,cusLink);
        System.out.println(customers);
        System.out.println(accounts);

        System.out.println(accounts.getClass().getName());

     //   a.linkAccountToCustomer(c,a,sc);
//        System.out.println(a);
       // System.out.println(c);
//        System.out.println(c1);
//
//        System.out.println("Enter Account Number of account and UUID to link them");
//        System.out.println("**********************");
//        System.out.println("Enter ID");
//        String inputUuid = sc.nextLine();
//        System.out.println("Enter Acc num");
//
//        Accounts temp = ;
//        Customer tempCustomer=
//
//        String inputAccountNumber = sc.nextLine();
//        String uuid = c.getUuid();
//        String accountNumber = a.getAccountNumber();
//
//       a.linkAccountToCustomer();


//
//        System.out.println("Enter ID of account to be fetched");
//        String id = sc.nextLine();
//        getCustomerDetails(id);
        // Customer c = new Customer("Raj","982912","test@test","000001");
      //  Bank bank = new Bank(new ArrayList<>(), new ArrayList<>());
    }

    private static Customer createCustomer (String name, String phoneNum, String email){

        String uuid = getUniqueCustomerid();
        Customer customer = new Customer(name,phoneNum,email,uuid);
        customers.add(customer);
        System.out.println("Customer with ID "+ uuid + " is created");
        return customer;
    }

    //Fetch details using ID , hence only ID is passed as parameter

    private static void getCustomerDetails(String id){

        for (Customer c: customers){
            if(id.compareTo(c.getUuid()) == 0){
                System.out.println(c.getName());
                return;
            }
        }
        System.out.println("User not found");

    }

    private static String getUniqueCustomerid() {
        String id;
        Random random = new Random();

        int len = 6;
        boolean nonUnique;

        do{
            id = "";
            for(int c=0;c<len;c++){
                id+=((Integer)random.nextInt(10)).toString();
            }
            nonUnique=false;
            for(Customer c: customers){
                if(id.compareTo(c.getUuid())==0){
                    nonUnique=true;
                    break;
                }
            }

        }while (nonUnique);

        return id;
    }

    private static Accounts createAccount(double balance, String currDateTime, Customer customer){
        String acctId = getUniqueAccountId();
        Accounts account = new Accounts(balance,acctId,currDateTime, customer,"Savings");
        accounts.add(account);
        System.out.println("Customer with Account Number "+ acctId + " is created on "+ currDateTime);

        return account;
    }

    private static String getUniqueAccountId() {
        String id;
        Random random = new Random();

        int len = 10;
        boolean nonUnique;

        do{
            id = "";
            for(int c=0;c<len;c++){
                id+=((Integer)random.nextInt(10)).toString();
            }
            nonUnique=false;
            for(Accounts a: accounts){
                if(id.compareTo(a.getAccountNumber())==0){
                    nonUnique=true;
                    break;
                }
            }

        }while (nonUnique);
        return id;
    }

    private static void getAccountDetails(String acctNum){

        for (Accounts a: accounts){
            if(acctNum.compareTo(a.getAccountNumber()) == 0){
                System.out.println(a.getCustomers());
                return;
            }
        }
        System.out.println("User not found");

    }
    private static Accounts getAccObj(String acctId){

        for(Accounts acc : accounts){
            if(acctId.compareTo(acc.getAccountNumber())==0){
                return acc;
            }
        }

    return null;
    }
    private static Customer getCustObj(String uuid){

        for(Customer c : customers){
            if(uuid.compareTo(c.getUuid())==0){
                return c;
            }
        }
        return null;
    }
    private static void linkAccount(Accounts accLink,Customer cusLink){

        accLink.addCustomer(cusLink);
        cusLink.addAccount(accLink);
        System.out.println("Accounts linked");
    }

    private static void withDraw(Accounts accounts, Scanner sc){

        double balance = accounts.getBalance();
        System.out.println("Enter amount to withdraw");
        double withdrawAmount = sc.nextDouble();

    //    accounts.updateWithdrawalBalance(balance,withdrawAmount);
       // double currBalance = balance - withdrawAmount;

        if(accounts.getAccountType()=="Savings"){
            System.out.println("You can withdraw now");
            accounts.updateWithdrawalBalance(balance,withdrawAmount);
        }

        if(accounts.getAccountType()=="Term Deposit"){
            if(accounts.term <=1 ){
                System.out.println("5% INTEREST WILL BE CHARGED");

            }
            if( accounts.term>=1 &&  accounts.term<=5){
                System.out.println("6% INTEREST WILL BE CHARGED");
            }
            if(accounts.term> 5){
                System.out.println("6.5% INTEREST WILL BE CHARGED");
            }

        }
    }

    private static void credit(Accounts accounts,Scanner sc){
        double balance = accounts.getBalance();
        System.out.println("Enter amount to withdraw");
        double creditAmount = sc.nextDouble();
       accounts.updateCreditBalance(balance,creditAmount);
    }



}
