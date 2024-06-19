import java.util.ArrayList;

/**
 * Represents a computer player. Mostly uses random logic if budget falls within
 * a certain boundary and a decision is not forced.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class ComputerPlayer
    extends Player
{

    private int               lowBalance;
    private int               highBalance;
    private int               minProperties;

    private ArrayList<Player> usedPlayers;

    /**
     * Create a new ComputerPlayer object.
     *
     * @param name
     *            name of the player
     * @param bank
     *            bank associated with the player
     * @param lowBalance
     *            minimum balance of budget
     * @param highBalance
     *            maximum balance of budget
     * @param minProperties
     *            minimum number of properties before improving owned properties
     */
    public ComputerPlayer(
        String name,
        Bank bank,
        int lowBalance,
        int highBalance,
        int minProperties)
    {
        super(name, bank);
        this.lowBalance = lowBalance;
        this.highBalance = highBalance;
        this.minProperties = minProperties;
        usedPlayers = new ArrayList<Player>();
    }


    /**
     * Gets the move option based on budget.
     *
     * @return move option
     */
    public int getMoveOption()
    {
        if (getBalance() < lowBalance && getProperties().size() > 0
            && usedPlayers.size() < getBank().getPlayers().size())
        {
            return 2;
        }
        else if (getBalance() > highBalance && getProperties().size() > minProperties)
        {
            return 1;
        }
        return 0;
    }


    /**
     * Returns a random property to improve
     *
     * @return index of property to improve
     */
    public int getPropertyToImprove()
    {
        ArrayList<Property> properties = getOwnedProperties();
        return (int)(Math.random() * properties.size());
    }


    /**
     * Gets the property index that should be sold from its owned properties
     *
     * @return index of property and price
     */
    public int[] getPropertyIndexToSell()
    {
        // Find most expensive property to maximize money to sell
        ArrayList<Property> properties = getProperties();

        int sellIdx = 0;

        for (int i = 0; i < properties.size(); i++)
        {
            Property property = properties.get(i);
            if (!getProperties().containsAll(super.getBank().getSpacesByGroup(property.getGroup())))
            {
                if (property.getSellPrice() > properties.get(0).getSellPrice())
                {
                    sellIdx = i;
                }
            }
        }

        return new int[] { sellIdx, properties.get(sellIdx).getSellPrice() };
    }


    /**
     * Returns a player that a property can be sold to.
     *
     * @return Player object for a player
     */
    public Player getPlayerToSellTo()
    {
        // Sell to a random player
        int idx = (int)(Math.random() * getBank().getPlayers().size());
        Player p = getBank().getPlayer(idx);
        if (usedPlayers.contains(p))
        {
            return null;
        }
        usedPlayers.add(p);
        return p;
    }


    /**
     * Determines if a property should be auctioned.
     *
     * @return true if something must be auctioned, false otherwise
     */
    public boolean shouldAuctionProperty()
    {
        return getBalance() < lowBalance;
    }

}
