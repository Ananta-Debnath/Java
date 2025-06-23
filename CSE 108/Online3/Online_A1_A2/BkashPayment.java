public class BkashPayment extends AbstractPayment implements PaymentGateway
{
    public BkashPayment(String accountIdentifier)
    {
        super(accountIdentifier);
    }

    @Override
    boolean validateDetails()
    {
        String str = getAccountIdentifier();
        if (str.length() == 11 && str.charAt(0) == '0' && str.charAt(1) == '1')
        {
            for (int i = 2; i < 11; i++)
            {
                if (str.charAt(i) < '0' || str.charAt(i) > '9') return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean processPayment(double amount)
    {
        if (validateDetails())
        {
            System.out.println("Charged $" + amount + " from Bkash number " + getAccountIdentifier());
            recordTransaction(amount);
            return true;
        }

        else
        {
            System.out.println("Invalid Bkash number: " + getAccountIdentifier());
            return false;
        }
    }
}
