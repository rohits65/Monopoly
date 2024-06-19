import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a player in the game. Contains all relevant information for game
 * play that is may or may not be stored through the bank.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class Player
{
    private String               name;
    private int                  balance;
    private LinkedList<Property> mortgages;
    private ArrayList<Property>  ownedProperties;
    private ArrayList<Property>  properties;
    private int                  position;
    private int                  inJailFor;
    private boolean              canLeaveJailFree;
    private PlayerGUI            playerGUI;
    private Bank                 bank;

    /**
     * Create a new Player object.
     *
     * @param name
     *            name of the player
     * @param bank
     *            bank associated with the player
     */
    public Player(String name, Bank bank)
    {
        if (name.strip().equals(""))
            this.name = "No name";
        else
            this.name = name;
        balance = 1500;
        mortgages = new LinkedList<Property>();
        ownedProperties = new ArrayList<Property>();
        properties = new ArrayList<Property>();
        inJailFor = 0;
        canLeaveJailFree = false;
        position = 0;
        playerGUI = new PlayerGUI(name);
        this.bank = bank;
    }


    /**
     * Gets the name of the player.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets the balance of the player.
     *
     * @return balance
     */
    public int getBalance()
    {
        return balance;
    }


    /**
     * Gets the GUI associated with the player.
     *
     * @return GUI
     */
    public PlayerGUI getPlayerGUI()
    {
        return playerGUI;
    }


    /**
     * Get properties that are either owned/mortgaged by the player.
     *
     * @return properties
     */
    public ArrayList<Property> getProperties()
    {
        return properties;
    }


    /**
     * Get owned properties of the player.
     *
     * @return owned properties
     */
    public ArrayList<Property> getOwnedProperties()
    {
        return ownedProperties;
    }


    /**
     * Move the player a given amount.
     *
     * @param amt
     *            amount to move player
     * @return new position
     */
    public int move(int amt)
    {
        position += amt;
        if (position >= 40)
        {
            balance += 200;
            // Pay mortgages
        }
        position = position % 40;
        return position;
    }


    /**
     * Send player to jail.
     */
    public void goToJail()
    {
        inJailFor = 4;
    }


    /**
     * Get jail time remaining of the player.
     *
     * @return jail time
     */
    public int getJailTime()
    {
        if (canLeaveJailFree)
            inJailFor = 0;
        else if (inJailFor > 0)
            inJailFor--;
        return inJailFor;
    }


    /**
     * Checks if player can leave jail immediately.
     */
    public void canLeaveJailFree()
    {
        canLeaveJailFree = true;
    }


    /**
     * Pays an amount of money to the bank.
     *
     * @param amt
     *            amount of money to send
     * @return new balance
     */
    public int pay(int amt)
    {
        balance -= amt;
        return balance;
    }


    /**
     * Receive amount from the bank.
     *
     * @param amt
     *            amount to receive
     * @return new balance
     */
    public int receive(int amt)
    {
        balance += amt;
        return balance;
    }


    /**
     * Get position of the player.
     *
     * @return position
     */
    public int getPosition()
    {
        return position;
    }


    /**
     * Add property to the player's property lists.
     *
     * @param property
     *            property to add
     * @return property added
     */
    public Property addProperty(Property property)
    {
        // Insert property into ordered list
        properties.add(property);
        if (property.isMortgaged())
        {
            for (int i = 0; i < mortgages.size() - 1; i++)
            {
                if (property.compareTo(mortgages.get(i)) >= 0
                    && property.compareTo(mortgages.get(i + 1)) <= 0)
                {
                    mortgages.add(i + 1, property);
                    return property;
                }
            }
            mortgages.add(property);
            return property;
        }
        for (int i = 0; i < ownedProperties.size() - 1; i++)
        {
            if (property.compareTo(ownedProperties.get(i)) >= 0
                && property.compareTo(ownedProperties.get(i + 1)) <= 0)
            {
                ownedProperties.add(i + 1, property);
                return property;
            }
        }
        ownedProperties.add(property);
        return property;
    }


    /**
     * Gets the property at index.
     *
     * @param idx
     *            index of property
     * @return property at index
     */
    public Property getProperty(int idx)
    {
        return properties.get(idx);
    }


    /**
     * Gets the bank of the player.
     *
     * @return bank of the player
     */
    public Bank getBank()
    {
        return bank;
    }


    /**
     * Get owned property at index.
     *
     * @param idx
     *            index of property
     * @return owned property at index
     */
    public Property getOwnedProperty(int idx)
    {
        return ownedProperties.get(idx);
    }


    /**
     * Remove property at index and all occurrences with player.
     *
     * @param property
     *            property to remove
     * @return removed property
     */
    public Property removeProperty(Property property)
    {
        if (property.isMortgaged())
        {
            mortgages.remove(property);
        }
        else
        {
            ownedProperties.remove(property);
        }
        properties.remove(property);
        return property;
    }


    public String toString()
    {
        String output = "";
        output += name;

        output += "\nCurrent location: " + bank.getSpaceAtPosition(position).getName();

        output += "\nBalance: " + getBalance();

        output += "\nOwns: ";
        int group = 0;

        for (Property property : ownedProperties)
        {
            if (property.getGroup() != group)
            {
                output += "\nGroup " + property.getGroup() + ": ";
                group = property.getGroup();
            }
            output += property.getName() + " ";
        }

        output += "\nMortgages: ";
        group = 0;

        for (Property property : mortgages)
        {
            if (property.getGroup() != group)
            {
                output += "\nGroup " + property.getGroup() + ": ";
                group = property.getGroup();
            }
            output += property.getName() + " ";
        }

        return output;
    }

    public boolean equals(Object other) {
        return name == ((Player) other).getName();
    }
}
