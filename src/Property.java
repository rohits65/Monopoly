import java.util.HashMap;

/**
 * Represents a building on the board. Contains information about buildings,
 * sell price, and rent price.
 *
 * @author rohit
 * @version Apr 11, 2021
 */
/**
 * Represents a template for a property. Contains basic setters and getters to
 * create a property.
 *
 * @author rohit
 * @version May 26, 2021
 */
abstract class Property
    extends Space
{
    private boolean                  mortgage;
    private int                      amtOwed;
    protected Bank                   bank;

    private HashMap<String, Integer> moneyTable;

    /**
     * Create a new Property object.
     *
     * @param name
     *            name of property
     * @param group
     *            group of property
     * @param position
     *            position on board
     * @param moneyTable
     *            hashTable with money values
     * @param bank
     *            bank associated with property
     */
    public Property(
        String name,
        int group,
        int position,
        HashMap<String, Integer> moneyTable,
        Bank bank)
    {
        super(name, group, position);
        this.moneyTable = moneyTable;
        this.bank = bank;
        mortgage = true;
        amtOwed = 0;
    }


    /**
     * Sets the ownership of a property to mortgaged or not mortgaged
     *
     * @param mortgageStatus
     *            new mortgage status
     */
    public void setOwnership(boolean mortgageStatus)
    {
        if (mortgageStatus)
        {
            amtOwed = getSellPrice();
        }
        mortgage = mortgageStatus;
    }


    /**
     * Gets the list price of the property.
     *
     * @return list price
     */
    public int getListPrice()
    {
        return moneyTable.get("listPrice");
    }


    /**
     * Sets sell price of property
     *
     * @param sellPrice price to sell at
     * @return sell price
     */
    public int setSellPrice(int sellPrice)
    {
        moneyTable.put("sellPrice", sellPrice);
        return sellPrice;
    }


    /**
     * Get sell price
     *
     * @return sell price
     */
    public int getSellPrice()
    {
        if (moneyTable.containsKey("sellPrice"))
            return moneyTable.get("sellPrice");
        return 0;
    }


    /**
     * Returns the mortgage status of the property
     *
     * @return true if mortgaged, false otherwise
     */
    public boolean isMortgaged()
    {
        return mortgage;
    }


    /**
     * Get price of mortgaging a property
     *
     * @return price to mortgage
     */
    public int getMortgageAmt()
    {
        return moneyTable.get("mortgage");
    }


    /**
     * Lifts mortgage on property
     *
     * @return amount owed for mortgage
     */
    public int liftMortgage()
    {
        amtOwed = 0;
        mortgage = false;
        return amtOwed;
    }


    /**
     * Gets money table of property
     *
     * @return money table
     */
    public HashMap<String, Integer> getMoneyTable()
    {
        return moneyTable;
    }


    /**
     * Gets the bank associated with the property
     *
     * @return bank
     */
    public Bank getBank()
    {
        return bank;
    }


    /**
     * Get rent
     *
     * @param diceRoll
     *            roll of dice
     * @return rent
     */
    abstract int getRent(int diceRoll);
}
