package model;

import contract.BankOperation;
import exception.InsufficientBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BankAccount implements BankOperation {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    private double transactionFee;




    public final List<Transaction> transactions = new ArrayList<>();

    private static  final   Map<String,String> branchCode = new HashMap<>();

    static {
        branchCode.put("NG-ABJ", "ABJ-234");
        branchCode.put("NG-BJ", "BJ-234");
        branchCode.put("NG-CJ", "CJ-234");
        branchCode.put("NG-DJ", "DJ-234");

    }


    {
        transactionFee = 10.0;
    }


    public BankAccount(String accountNumber, String accountHolderName, double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public  abstract double calculateInterest();

    public void  applyInterest(){
        double interestAmount = calculateInterest();

        if (interestAmount > 0){
            deposit(interestAmount);
            Transaction transaction = new Transaction("TX-INT" + System.nanoTime(), interestAmount , TransactionType.INTEREST);
            transactions.add(transaction);
            System.out.println("Interest Earn: " + interestAmount);
            System.out.println("Interest applied");
            System.out.println("\n");
        }else{
            System.out.println("No interest for this account");
        }
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void showAccountInformation(){
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Transaction Fees: " + transactionFee);
        showAccountInformation(transactions);
    }

    public void  showAccountInformation(List<Transaction> transactions){


        System.out.println("\n");
        System.out.println("Transactions History");

        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }

    }

    public static   Map<String,String> getBranchCode(){
        return branchCode;
    }

    @Override
    public void deposit(double amount) {
        if(!BankOperation.validateAmount(amount)) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        balance += amount;

        Transaction transaction = new Transaction("TX" + System.nanoTime(), amount , TransactionType.DEPOSIT);
        transactions.add(transaction);

    }



    @Override
    public void withdraw(double amount) {
        if(!BankOperation.validateAmount(amount)) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }


        if (amount + transactionFee > balance) {
            throw new InsufficientBalance("Insufficient Balance");
        }

        balance -= amount + getTransactionFee();


        Transaction transaction = new  Transaction("TX" + System.nanoTime(), amount , TransactionType.WITHDRAW);
        transactions.add(transaction);

        Transaction transactionFess = new Transaction("TX" + System.nanoTime(), transactionFee , TransactionType.FEES);
        transactions.add(transactionFess);
    }

}
