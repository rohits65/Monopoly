import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a building on the bank. Contains information about installments,
 * sell price, and rent price.
 *
 * @author rohit
 * @version Apr 11, 2021
 */
public class Building
    extends Property
{

    private HashMap<String, ArrayList<Installment>> buildings;

    /**
     * Create a new Property object.
     *
     * @param name
     *            name of building
     * @param group
     *            property group
     * @param position
     *            position on board
     * @param moneyTable
     *            hashTable with money values
     * @param bank
     *            bank that represents this building
     */
    public Building(
        String name,
        int group,
        int position,
        HashMap<String, Integer> moneyTable,
        Bank bank)
    {
        super(name, group, position, moneyTable, bank);
        buildings = new HashMap<String, ArrayList<Installment>>();
        buildings.put("houses", new ArrayList<Installment>());
        buildings.put("hotels", new ArrayList<Installment>());
    }


    /**
     * Gets the price of this building.
     *
     * @return price
     */
    public int getBuildingPrice()
    {
        return getMoneyTable().get("buildingPrice");
    }


    /**
     * Adds an installment to this building.
     *
     * @return true if installment was successfully added, false otherwise
     */
    public boolean addInstallment()
    {
        // Make sure building is owned and has space before adding an
        // installment
        if (!isMortgaged())
        {
            if (getNumHouses() <= 3 && getNumHotels() == 0)
            {
                buildings.get("houses").add(new Installment(getBuildingPrice()));
                bank.sendMessage(
                    bank.getPropertyOwner(this),
                    "Added one house. This property now has " + buildings.get("houses").size()
                        + " houses.");
                return true;
            }
            else if (getNumHouses() == 4 && getNumHotels() == 0)
            {
                buildings.remove("houses");
                buildings.put("houses", new ArrayList<Installment>());
                buildings.get("hotels").add(new Installment(getBuildingPrice()));
                bank.sendMessage(bank.getPropertyOwner(this), "Added hotel and removed houses.");
                return true;
            }
            else
            {
                bank.sendMessage(
                    bank.getPropertyOwner(this),
                    "This property already has a hotel. Cannot improve further.");
                return false;
            }
        }
        bank.sendMessage(
            bank.getPropertyOwner(this),
            "This property is currently mortgaged, so a property cannot be added.");
        return false;
    }


    /**
     * Get number of houses on this building.
     *
     * @return number of houses
     */
    public int getNumHouses()
    {
        return buildings.get("houses").size();
    }


    /**
     * Get number of hotels on this building.
     *
     * @return number of hotels
     */
    public int getNumHotels()
    {
        return buildings.get("hotels").size();
    }


    public int getRent(int diceroll)
    {
        // Get rent based on number of houses and catch exceptions if
        // installments aren't added
        try
        {
            if (buildings.get("houses").size() + buildings.get("hotels").size() == 0)
            {
                if (bank.getPropertyOwner(this).getProperties()
                    .containsAll(getBank().getSpacesByGroup(getGroup())))
                    return getMoneyTable().get("rent0") * 2;
                return getMoneyTable().get("rent0");
            }
            int add = getNumHouses() + getNumHotels();
            return getMoneyTable().get("rent" + add);
        }
        catch (NullPointerException e)
        {
            try
            {
                return getMoneyTable().get("rent" + getNumHouses());
            }
            catch (NullPointerException ex)
            {
                return getMoneyTable().get("rent0");
            }
        }
    }


    public String toString()
    {
        String owner;

        if (bank.getPropertyOwner(this) != null)
            owner = bank.getPropertyOwner(this).getName();
        else
            owner = "None";

        return getName() + "\nList Price: " + getListPrice() + ", Sell Price: " + getSellPrice()
            + "\nOwner: " + owner + ", Houses: " + getNumHouses() + ", Hotels: " + getNumHotels()
            + "\nRent: " + getRent(0) + "\n-------------------------------";
    }
}
