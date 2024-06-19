import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a utility.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class Utility
    extends Property
{
    /**
     * Create a new Utility object.
     *
     * @param name
     *            name of utility
     * @param group
     *            property group
     * @param position
     *            position on board
     * @param moneyTable
     *            hashTable with moneyValues
     * @param bank
     *            bank associated with Utility
     */
    public Utility(
        String name,
        int group,
        int position,
        HashMap<String, Integer> moneyTable,
        Bank bank)
    {
        super(name, group, position, moneyTable, bank);
    }


    public int getRent(int diceRoll)
    {
        ArrayList<Property> ownedSpaces = bank.getPropertyOwner(this).getProperties();
        ownedSpaces.retainAll(getBank().getSpacesByGroup(getGroup()));
        if (ownedSpaces.size() == 1)
        {
            return diceRoll * 4;
        }
        else if (ownedSpaces.size() == 2)
        {
            return diceRoll * 10;
        }
        return 0;
    }
}
