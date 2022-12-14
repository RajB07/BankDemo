package com.example.bankDemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {
    static ArrayList<Customer> customers;
    static ArrayList<Accounts> accounts;

    public static void main(String[] args) {

        customers = new ArrayList<>();
        accounts = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        Customer c = createCustomer("Raj","982912","test@test");


        boolean loop = true;
        while(loop){
            loop= mainMenuPrompt(sc);

        }


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
            System.out.println("8) ADD INTEREST IN ALL ACCTS");

            System.out.println("9) Back to menu ");
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
                    Date currDateTime = new Date();
                   // Date currDateTime = dateFormat.format(date);
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

                case 4:
                    sc.nextLine();
                    System.out.println("Enter the Account Number from which you want to withdraw money");
                    String accNum = sc.nextLine();
                    Accounts acc1 = getAccObj(accNum);
                    if(acc1==null){
                        System.out.println("No such acc num exists");
                        break;
                    }
                    withDraw(acc1,sc);
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
                    getCustomerDetails(id);
                    break;

                case 6:
                    sc.nextLine();
                    System.out.println("Enter Account number to get details");
                    String accNum1 = sc.nextLine();
                    Accounts a = getAccObj(accNum1);
                    if(a==null){
                        System.out.println("No such acc num exists");
                        break;
                    }
                    getAccountDetails(accNum1,a);
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

               case 8:
                   addInterestInAllAccounts();
                   break;

               case 9:
                   mainMenuPrompt(sc);
                   break;
            }
        } while (choice < 1 || choice > 8);

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

    private static void getCustomerDetails(String id) {

        Customer customer = getCustObj(id);
        System.out.println("The name of customer is " + customer.getName() + " with ID " + customer.getUuid());

      //  System.out.println("");
        ArrayList<Accounts> accounts = customer.getAccounts();
        for (Accounts a : accounts) {
                if (a == null) {
                    System.out.println("User has no accounts");
                }
        System.out.println("The customer has account number " + a.getAccountNumber()
            + " and has " + a.getAccountType() + " account with balance "
            + a.getBalance());
            }
            return;

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

    private static void createAccount(Date currDateTime){

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
                Accounts savings = new Savings(balance,acctId,currDateTime,customer,"Savings",0.03 );
                accounts.add(savings);
                //System.out.println("Account with Account Number: "+acctId +" created on "+currDateTime);
                System.out.println("Customer's new account with Account Number "+ acctId + " is created on "+ currDateTime);

                break;
            case 2:

                sc.nextLine();
                Customer customer1;
                String uuid1;
                System.out.println("Term Deposit chosen");
                System.out.println("Enter Customer ID to continue");
                uuid1=sc.nextLine();

                System.out.println("Enter the term");
                double term = sc.nextInt();
                double interest;
                if (term >= 0 && term <= 1) {
                    interest = 0.05;
                } else if (term >= 1 && term <= 5) {
                    interest = 0.06;
                } else if (term > 5) {
                    interest = 0.065;
                }
                else {
                    interest = 0.00; // as we have put this else , we wont get interest not initialized error.
                }
                customer1 = getCustObj(uuid1);
                if(customer1==null){
                    System.out.println("There is no account");
                }
              else {
                    System.out.println("Enter initial balance");
                    balance = sc.nextDouble();
                    Accounts termDeposit = new TermDeposit(balance,term,interest,acctId, currDateTime, customer1, "TermDeposit");
                    System.out.println("Term chosen : "+term+" and interest is "+interest);
                    accounts.add(termDeposit);
                    System.out.println("Customer's new Account with Account Number " + acctId + " is created on " + currDateTime);
                    System.out.println("Current Balance is " + balance);
                    break;
                }
        }
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

            if(acctNum.compareTo(a.getAccountNumber()) == 0){
                ArrayList<Customer> customer = a.getCustomers();
                System.out.println("Account number is "+ acctNum +" Type of acc is "+a.getAccountType());
                for (Customer c: customer)
                {
                    System.out.println("The account holder name is "+ c.getName()+" and id is "+c.getUuid());
                    System.out.println("The customer "+c.getName() +" has account number " + a.getAccountNumber() + " and has " + a.getAccountType() + " account with balance " + a.getBalance());

                }
                for (Accounts a1 : accounts) {
                    if (a1 == null) {
                        System.out.println("User has no accounts");
                    }

                }
            }
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

    private static void withDraw(Accounts accounts, Scanner sc) {

        double balance = accounts.getBalance();

        if (accounts.getAccountType() == "Savings") {
            System.out.println("Enter amount to withdraw");
            double withdrawAmount = sc.nextDouble();
            if (accounts.getBalance() > 0 && withdrawAmount <accounts.getBalance() ) {
                System.out.println("You can withdraw now");

                accounts.updateWithdrawalBalance(withdrawAmount);
                return;
            }
            else{
                System.out.println("No Balance");
            }
        }
        if (accounts.getAccountType() == "TermDeposit") {

            long diff = accounts.getTimeStamp().getTime() - new Date().getTime();

            long days = TimeUnit.MILLISECONDS.toDays(diff);
            if (days < 365) {
                System.out.println("You cannot withdraw money until term ends.");
            } else {
                System.out.println("Enter amount to withdraw");
                double withdrawAmount = sc.nextDouble();
                accounts.updateWithdrawalBalance(withdrawAmount);
            }
        }
    }
    private static void credit(Accounts accounts,Scanner sc) {

            if(accounts.getAccountType()=="TermDeposit") {
                System.out.println("You cannot credit money again");
            }
            else {

                System.out.println("Enter amount to credit");
                double creditAmount = sc.nextDouble();
                double currBal = accounts.updateCreditBalance(accounts.getBalance(), creditAmount);
                accounts.setBalance(currBal); // redundant statement because you can setBalance in accounts class only!
            }
        }

    private static double calculateSavingsInterest(Accounts a,Scanner sc) {

        Date date = new Date();
        double balanceAfterInterest;
        // for(Accounts acct : accounts) {
        long diff = date.getTime() - a.getTimeStamp().getTime();
        if (diff < 2) {
            System.out.println("Balance would remain same. No interest " + a.getBalance());
            return a.getBalance();
        } else {
            double interest = 0.03;
            double balance = a.getBalance();
            System.out.println("Balance before interest " + balance);
            balanceAfterInterest = balance + (balance * interest);
            System.out.println("Balance after interest " + balanceAfterInterest);

            return balanceAfterInterest;
        }
    }

    private static double calculateTermDepositInterest(Accounts termDeposit){

        Date date = new Date();
        long diff = date.getTime() - termDeposit.getTimeStamp().getTime();
        System.out.println(diff);
        System.out.println(termDeposit.getBalance());
        System.out.println(termDeposit.getInterest());
        System.out.println(termDeposit.getTerm());
        if(diff >= termDeposit.getTerm()){
            termDeposit.setBalance(termDeposit.getBalance() + (termDeposit.getBalance() * termDeposit.getInterest()) );
        }
       else{
            System.out.println("you cannot avail interest");
        }
        return termDeposit.getBalance();
    }

    private  static  void addInterestInAllAccounts(){

        for(Accounts a : accounts){
            a.addInterest();
        }
    }

}







