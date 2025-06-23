public class CreditCardPayment extends AbstractPayment implements PaymentGateway
{
    public CreditCardPayment(String accountIdentifier)
    {
        super(accountIdentifier);
    }

    @Override
    boolean validateDetails()
    {
        int count = 0;
        char[] str = getAccountIdentifier().toCharArray();
        for (char c : str)
        {
            if (c >= '0' && c <= '9') count++;

            else if (c != '-') return false;
        }
        return count == 16;
    }

    @Override
    public boolean processPayment(double amount)
    {
        if (validateDetails())
        {
            System.out.println("Charged $" + amount + " to credit card " + getAccountIdentifier());
            recordTransaction(amount);
            return true;
        }

        else
        {
            System.out.println("Invalid credit card details: " + getAccountIdentifier());
            return false;
        }
    }
}
