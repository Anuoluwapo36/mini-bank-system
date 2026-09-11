package contract;

public interface BankOperation {
    void deposit(double amount);
    void withdraw(double amount);
    default void  showBankPolicy(){
        System.out.println("The Bank of Africa " + "\n" + "Bank Payment Policy\n" +
                "\n" +
                "Customers must provide accurate bank account information when making or receiving payments. All bank details are securely handled and used only for authorized transactions.\n" +
                "\n" +
                "Payments are processed according to the selected payment method and applicable banking requirements. The platform is not responsible for delays, failed transactions, or additional charges caused by incorrect bank information provided by the user.\n" +
                "\n" +
                "Users should verify all bank details before confirming a transaction. Any suspicious or unauthorized transaction should be reported to the platform immediately.\n" +
                "\n" +
                "Refunds, where applicable, will be processed according to the platform's refund policy and may take additional time depending on the customer's bank.\n");
    }

    static boolean validateAmount(double amount){
        return amount > 0;
    }
}
