public abstract class AbstractPayment
{
    private String accountIdentifier;
    private double lastTransactionAmount;

    public AbstractPayment(String accountIdentifier)
    {
        this.accountIdentifier = accountIdentifier;
        lastTransactionAmount = 0;
    }

    public String getAccountIdentifier()
    {
        return accountIdentifier;
    }

    public double getLastTransactionAmount()
    {
        return lastTransactionAmount;
    }

    void recordTransaction(double amount)
    {
        lastTransactionAmount = amount;
    }

    abstract boolean validateDetails();
}
