/**
 * Represents any type of building.
 *
 * @author rohit
 * @version Apr 11, 2021
 */
public class Installment
{
    private String type;
    private int    price;

    /**
     * Create a new Installment object.
     *
     * @param price
     *            price of the installment
     */
    public Installment(int price)
    {
        type = "house";
        this.price = price;
    }


    /**
     * Gets the type of installment (house or hotel)
     *
     * @return type
     */
    public String getType()
    {
        return type;
    }


    /**
     * Returns price of installment.
     *
     * @return price
     */
    public int getPrice()
    {
        return price;
    }
}
