import java.util.*;

/**
 * Represents the bank and controls top level actions. This class can identify
 * what a player needs to do based on the property the player lands on, and in
 * addition, it controls how many players are added into the game.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class Bank
{
    private ArrayList<Player>                players;
    private TreeMap<Integer, Space>          spaces;
    private HashMap<Integer, TreeSet<Space>> spacesByGroup;
    private BoardGUI                         boardGUI;

    /**
     * Create a new Bank object.
     */
    public Bank()
    {
        players = new ArrayList<Player>();
        spaces = new TreeMap<Integer, Space>();
        spacesByGroup = new HashMap<Integer, TreeSet<Space>>();
        boardGUI = new BoardGUI(this);
    }


    /**
     * Adds a player into the game.
     *
     * @param player
     *            player to add
     * @return true if successfully added, false otherwise
     */
    public boolean addPlayer(Player player)
    {
        if (players.size() < 4)
        {
            players.add(player);
            boardGUI.createPlayerDataWindow(player);
            return true;
        }
        return false;
    }


    /**
     * Removes a player from the bank.
     *
     * @param player
     *            player to remove
     * @return true if player was removed, false otherwise
     */
    public boolean removePlayer(Player player)
    {
        return players.remove(player);
    }


    /**
     * Getter for players.
     *
     * @return players
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }


    /**
     * Returns the first instance of a player object that has a specific name.
     *
     * @param name
     *            name of player to look for
     * @return Player object with name
     */
    public Player getPlayer(String name)
    {
        for (Player player : players)
        {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }


    /**
     * Get player based on index within list.
     *
     * @param idx
     *            index of player
     * @return Player object at index
     */
    public Player getPlayer(int idx)
    {
        return players.get(idx);
    }


    /**
     * Adds a space into the board.
     *
     * @param position
     *            position to add the space in
     * @param space
     *            space object to add
     */
    public void addSpace(int position, Space space)
    {
        spaces.put(position, space);
        if (spacesByGroup.containsKey(space.getGroup()))
        {
            spacesByGroup.get(space.getGroup()).add(space);
        }
        else
        {
            TreeSet<Space> spaceTreeSet = new TreeSet<Space>();
            spaceTreeSet.add(space);
            spacesByGroup.put(space.getGroup(), spaceTreeSet);
        }
    }


    /**
     * Gets the owner of a given property.
     *
     * @param property
     *            property to find owner of
     * @return Player object that owns the property
     */
    public Player getPropertyOwner(Property property)
    {
        for (Player player : players)
        {
            for (Property property1 : player.getProperties())
            {
                if (property.equals(property1))
                    return player;
            }
        }
        return null;
    }


    /**
     * Gets the number of spaces in the board.
     *
     * @return number of spaces
     */
    public int getNumberOfSpaces()
    {
        return spaces.size();
    }


    /**
     * Gets all spaces in a group.
     *
     * @param group
     *            group to retrieve spaces of
     * @return set of spaces in group
     */
    public TreeSet<Space> getSpacesByGroup(int group)
    {
        return spacesByGroup.get(group);
    }


    /**
     * Gets the space at a specific position
     *
     * @param position
     *            position of space
     * @return space
     */
    public Space getSpaceAtPosition(int position)
    {
        return spaces.get(position);
    }


    /**
     * Determines and executes the GUI windows for a player that lands on a
     * certain space based on who owns the space and the type of space.
     *
     * @param player
     *            player that landed on space
     * @param diceRoll
     *            roll that landed the player on the space
     */
    public void checkPlayerOptions(Player player, int diceRoll)
    {
        Space space = getSpaceAtPosition(player.getPosition());
        sendMessage(player, "You have landed on " + space.getName());
        if (space instanceof Property)
        {
            Property property = (Property)space;
            if (getPropertyOwner(property) == null)
            {
                PropertyTransaction transaction =
                    new PropertyTransaction(property, property.getListPrice(), player, this);
                transaction.askToAuction();
                transaction.acceptTransaction();
            }
            else if (!property.isMortgaged() && !getPropertyOwner(property).equals(player))
            {
                getPropertyOwner(property).receive(property.getRent(diceRoll));
                sendMessage(
                    getPropertyOwner(property),
                    player.getName() + " paid you " + property.getRent(diceRoll)
                        + " for landing on " + property.getName());
                player.pay(property.getRent(diceRoll));
                sendMessage(
                    player,
                    "You paid " + getPropertyOwner(property).getName() + " "
                        + property.getRent(diceRoll) + " for landing on " + property.getName());
            }
        }
        else if (space instanceof ChanceCard)
        {
            ChanceCard.executeAction(player);
        }
        else if (space instanceof CommunityCard)
        {
            CommunityCard.executeAction(player);
        }
        else if (space instanceof TaxCard)
        {
            TaxCard taxCard = (TaxCard)space;
            taxCard.setPlayer(player);
            taxCard.payTax();
        }
        boardGUI.updatePlayerDataWindows();
    }


    /**
     * Add installment to a property
     *
     * @param property
     *            property to add installment to
     */
    public void addInstallment(Property property)
    {
        // Check to make sure that property is of Building type, which is the
        // only class that can have installments.
        if (property instanceof Building)
        {
            Building building = (Building)property;
            building.addInstallment();
        }
        else
        {
            sendMessage(
                getPropertyOwner(property),
                "Installments cannot be added to this property.");
        }
    }


    /**
     * Prints data of players in the bank.
     */
    public void getData()
    {
        for (Player player : getPlayers())
        {
            sendMessage(
                player.toString() + " They are located at "
                    + getSpaceAtPosition(player.getPosition()) + ". They own:");
            for (Property property : player.getProperties())
            {
                sendMessage(property.toString());
            }
        }
    }


    /**
     * Requests a yes/no question to a player
     *
     * @param target
     *            player to ask question to
     * @param message
     *            question to ask
     * @return true if yes, false otherwise
     */
    public boolean sendRequest(Player target, String message)
    {
        // Determine response based on type of player
        if (target instanceof ComputerPlayer)
        {
            return Math.random() < 0.5 ? true : false;
        }
        return target.getPlayerGUI().displayDropDown(new String[] { "Yes", "No" }, message) == 0;
    }


    /**
     * Sends a yes/no question with a numerical entry if no to a player
     *
     * @param target
     *            player to ask question to
     * @param message
     *            question to ask
     * @param price
     *            starting numerical entry
     * @return price if yes, numerical entry if false
     */
    public int sendRequest(Player target, String message, int price)
    {
        // Determine decision based on type of player
        if (target instanceof ComputerPlayer)
        {
            System.out.println("HIHIHI");
            return !((ComputerPlayer)target).shouldAuctionProperty() ? price : 0;
        }
        else if (target.getPlayerGUI().displayDropDown(new String[] { "Yes", "No" }, message) == 0)
        {
            return price;
        }

        return target.getPlayerGUI().displayNumerical("What price would you like to buy at?");
    }


    /**
     * Sends a message to a player.
     *
     * @param target
     *            player to send message to
     * @param message
     *            message to send
     */
    public void sendMessage(Player target, String message)
    {
// System.out.print("To " + target.getName() + ": ");
// System.out.println(message);
        target.getPlayerGUI().addMessage(message);
    }


    /**
     * Sends a message to the main message board.
     *
     * @param message
     *            message to send
     */
    public void sendMessage(String message)
    {
        boardGUI.sendMessageToEveryone(message);
// for (Player player: players) {
// player.getPlayerGUI().addMessage(message);
// }
// System.out.println("To everyone: " + message);
    }

}
