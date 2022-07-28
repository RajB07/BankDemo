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
       // Customer c1;
//        Customer c1 = createCustomer("Aftab","9821","test@test1");
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        String currDateTime = dateFormat.format(date);
       // Accounts a = createAccount(currDateTime, c);
     //   Savings savings = new Savings(0,"1","2",c,"savings");
        //System.out.println(savings.getClass().getName());
//        for(Accounts c2: accounts){
//            System.out.println(c2);
//        }
//        System.out.println("Enter uuid to link");
//        String uuid = sc.nextLine();
//        System.out.println("Enter acc num to link");
//        String acctId =sc.nextLine();
//
//        Accounts accLink = getAccObj(acctId);
//        Customer cusLink = getCustObj(uuid);
//        linkAccount(accLink,cusLink);
//        System.out.println(customers);
//        System.out.println(accounts);
//
//        System.out.println(accounts.getClass().getName());

        boolean loop = true;
        while(loop){
            loop= mainMenuPrompt(sc);

        }

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

    private static boolean mainMenuPrompt(Scanner sc) {
            int choice;

            System.out.println("Welcome to Demo Bank.");
            System.out.println("Please select options to perform operations");
            System.out.println("1) Create Customer");
            //System.out.println("2) Login");
            System.out.println("2) Create New Account");
            System.out.println("3) Credit Money");
            System.out.println("4) Debit Money");
            System.out.println("5) Show Details of CUSTOMER");
            System.out.println("6) Show Details of ACCOUNT");
            System.out.println("7) Link Customer and Accounts");
            do{
                 choice = sc.nextInt();
                switch (choice) {

                case 1:
                    sc.nextLine();
                    System.out.println("Welcome to create customer");
                    String name;
                    System.out.println("Enter Name");
                    name = sc.nextLine();
                    String phoneNum;
                    System.out.println("Enter Phone num");
                    phoneNum = sc.nextLine();
                    String email;
                    System.out.println("Enter email");
                    email = sc.nextLine();
                    createCustomer(name, phoneNum, email);
                    break;

                case 2:
                    sc.nextLine();
                    System.out.println("Welcome to create Acc");
                    double balance;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date date = new Date();
                    String currDateTime = dateFormat.format(date);
                    createAccount(currDateTime);
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Enter the Account Number to credit money");
                    String acctNum = sc.nextLine();
                    Accounts acc = getAccObj(acctNum);
                    if(acc==null){
                        System.out.println("No such acc num exists");
                        break;
                    }
                    credit(acc,sc);

                        break;


                case 5:
                    sc.nextLine();
                    System.out.println("Enter Customer ID to get Details");
                    String id = sc.nextLine();
                    Customer c = getCustObj(id);
                    if(c==null){
                        System.out.println("Customer ID DOES NOT EXIST");
                        break;
                    }
                    getCustomerDetails(id,c);
                    break;

                case 6:
                    sc.nextLine();
                    System.out.println("Enter Account number to get details");
                    String accNum = sc.nextLine();
                    Accounts a = getAccObj(accNum);
                    if(a==null){
                        System.out.println("No such acc num exists");
                        break;
                    }
                    getAccountDetails(accNum,a);
                    break;

                case 7:
                   sc.nextLine();
                   System.out.println("Link your accounts by entering User ID and account ID to which you want to link");
                   System.out.println("Enter uuid to link");
                   String uuid = sc.nextLine();
                   System.out.println("Enter acc num to link");
                   String acctId =sc.nextLine();

                   Accounts accLink = getAccObj(acctId);
                   Customer cusLink = getCustObj(uuid);
                   linkAccount(accLink,cusLink);
                   break;
            }
        } while (choice < 1 || choice > 7);

        return true;
    }


    private static Customer createCustomer (String name, String phoneNum, String email){

        String uuid = getUniqueCustomerid();
        Customer customer = new Customer(name,phoneNum,email,uuid);
        customers.add(customer);
        System.out.println("Customer with ID "+ uuid + " is created");
        return customer;
    }

    //Fetch details using ID , hence only ID is passed as parameter

    private static void getCustomerDetails(String id,Customer customer) {
        //for (Customer c: customers){
        if (id.compareTo(customer.getUuid()) == 0) {
            System.out.println("The name of customer is " + customer.getName() + " with ID " + customer.getUuid()
                    + " and has email id " + customer.getEmailId());
            // }
            for (Accounts a : accounts) {
                if (a == null) {
                    System.out.println("User has no accounts");
                }
                System.out.println("The customer has account number " + a.getAccountNumber() + " and has " + a.getAccountType() + " account with balance " + a.getBalance());
            }
            return;
            // System.out.println("User not found");
        }
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

    private static void createAccount(String currDateTime){

        String acctId = getUniqueAccountId();
        double balance = 0;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the type of account you want to choose");
        System.out.println("1) Savings Account");
        System.out.println("2) Term Deposit Account");
        int choice = sc.nextInt() ;
        switch (choice){
            case 1 :
                sc.nextLine();
                String uuid;
                Customer customer;
                System.out.println("Savings acc chosen");
                System.out.println("Enter the customer uuid:");
                uuid= sc.nextLine();
                customer = getCustObj(uuid);
                if(customer==null){
                    System.out.println("There is no account");
                }
                Accounts savings = new Savings(balance,acctId,currDateTime,customer,"Savings");
                accounts.add(savings);
                //System.out.println("Account with Account Number: "+acctId +" created on "+currDateTime);
                System.out.println("Customer's new account with Account Number "+ acctId + " is created on "+ currDateTime);
                System.out.println("Current Balance is "+balance);
                break;
            case 2:
                sc.nextLine();
                System.out.println("Term Deposit chosen");
                System.out.println("Enter Customer ID to continue");
                String uuid1=sc.nextLine();
                customer = getCustObj(uuid1);
                if(customer==null){
                    System.out.println("There is no account");
                }
              else {
                    Accounts termDeposit = new TermDeposit(balance, acctId, currDateTime, customer, "TermDeposit");
                    accounts.add(termDeposit);
                    System.out.println("Customer's new Account with Account Number " + acctId + " is created on " + currDateTime);
                    System.out.println("Current Balance is " + balance);
                    System.out.println("Please credit money and forget about it till your term comes");
                    double balance1 = termDeposit.getBalance();
                    System.out.println("Enter amount to credit");
                    double creditAmount = sc.nextDouble();
                    termDeposit.updateCreditBalance(balance1, creditAmount);
                    break;
                }
        }
//        Accounts account = new Accounts(balance,acctId,currDateTime, customer,"Savings");
//        accounts.add(account);



        //return account;
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

    private static void getAccountDetails(String acctNum, Accounts a){

       // for (Accounts a: accounts){
            if(acctNum.compareTo(a.getAccountNumber()) == 0){
                ArrayList<Customer> customer = a.getCustomers();
                System.out.println("Account number is "+ acctNum +" Type of acc is "+a.getAccountType());
                for (Customer c: customer)
                {
                    System.out.println("The account holder name is "+ c.getName()+" and id is "+c.getUuid());

                }
              //  System.out.println(customer);
            }
       // }
       // System.out.println("User not found");

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
        System.out.println("Accounts are linked");
    }

    private static void withDraw(Accounts accounts, Scanner sc){

        System.out.println("Enter the Account Number from which you want to withdraw");
        String acctNum = sc.nextLine();
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

    private static void credit(Accounts accounts,Scanner sc) {

            if(accounts.getAccountType()=="TermDeposit") {
                System.out.println("You cannot credit money again");
            }
            else {
                double balance = accounts.getBalance();
                System.out.println("Enter amount to credit");
                double creditAmount = sc.nextDouble();
                accounts.updateCreditBalance(balance, creditAmount);
            }
        }
    }



