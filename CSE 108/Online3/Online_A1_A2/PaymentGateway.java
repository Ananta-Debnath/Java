public interface PaymentGateway
{
    boolean processPayment(double amount);
    default boolean refund(double amount)
    {
        System.out.println("Refund of $" + amount + " processed via " + getClass().getSimpleName());
        return true;
    }
}
