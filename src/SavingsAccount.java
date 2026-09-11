import contract.BankOperation;
import model.BankAccount;

public class SavingsAccount extends BankAccount {

    private double interestRate;
    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public double calculateInterest() {
        double interest = getBalance() * interestRate / 100;


        return interest;
    }

    @Override
    public void deposit(double amount) {
      super.deposit(amount);

    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);

    }

    @Override
    public void showBankPolicy() {
        super.showBankPolicy();
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
