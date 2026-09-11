import exception.InsufficientBalance;
import model.BankAccount;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SavingsAccount savingAccount = new SavingsAccount("1294373","majie",100,5);

        savingAccount.deposit(2000);

        try{
            savingAccount.withdraw(50);
            savingAccount.withdraw(5);
            savingAccount.withdraw(7);

        }catch (InsufficientBalance e){
            throw new InsufficientBalance(e.getMessage());
        }catch (IllegalArgumentException e){
            throw new ArithmeticException("Invalid amount: " + e.getMessage());
        }


        CurrentAccount currentAccount = new CurrentAccount("1294373","ruth",1000,2000);
        currentAccount.deposit(500);
        try{
            currentAccount.withdraw(3000);
            currentAccount.deposit(1000);
        }catch (InsufficientBalance e){
            throw new InsufficientBalance("Bank error: " + e.getMessage());
        }


        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(savingAccount);
        bankAccounts.add(currentAccount);
        modifyAmount(savingAccount);
        modifyAmount(currentAccount);

        for (BankAccount bp : bankAccounts) {
            bp.showAccountInformation();
            bp.applyInterest();




        }








    }

    public static void modifyAmount(BankAccount bankAccount){
        bankAccount.deposit(100);
        System.out.println("balance after deposit: " + bankAccount.getBalance());
        bankAccount = null;
    }
}