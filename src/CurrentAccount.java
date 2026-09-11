import contract.BankOperation;
import exception.InsufficientBalance;
import model.BankAccount;
import model.Transaction;
import model.TransactionType;

public class CurrentAccount extends BankAccount {

    private double overdraft;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance, double overdraft) {
        super(accountNumber, accountHolderName, balance);
        this.overdraft = overdraft;
    }

    /**
     *
     */
    @Override
    public double calculateInterest() {


        return 0;
    }

    @Override
    public void withdraw(double amount){



        if (!BankOperation.validateAmount(amount)) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }


        if (amount + getTransactionFee() > getBalance() + overdraft) {
            throw new InsufficientBalance("withdraw exist overdraft  limit");
        }
        setBalance(getBalance() - amount - getTransactionFee());


        Transaction currentAccTransaction = new Transaction("CA-TX" + System.nanoTime(), amount, TransactionType.WITHDRAW);
        transactions.add(currentAccTransaction);


        Transaction withdrawFess = new Transaction("CA-TX" + System.nanoTime(), getTransactionFee(), TransactionType.FEES);
        transactions.add(withdrawFess);

        calculateInterest();

    }
    
}
