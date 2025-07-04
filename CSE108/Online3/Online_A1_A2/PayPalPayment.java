public class PayPalPayment extends AbstractPayment implements PaymentGateway
{
    public PayPalPayment(String accountIdentifier)
    {
        super(accountIdentifier);
    }

    @Override
    boolean validateDetails()
    {
        String str = getAccountIdentifier();
        return str.indexOf('@') > 0 && str.indexOf('.', str.indexOf('@')) < str.length()-1;
    }

    @Override
    public boolean processPayment(double amount)
    {
        if (validateDetails())
        {
            System.out.println("Processed $" + amount + " via PayPal account " + getAccountIdentifier());
            recordTransaction(amount);
            return true;
        }

        else
        {
            System.out.println("Invalid PayPal account email: " + getAccountIdentifier());
            return false;
        }
    }
}
