import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a railroad property.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class Railroad
    extends Property
{
    /**
     * Create a new Property object.
     *
     * @param group
     *            property group
     */
    /**
     * Create a new Railroad object.
     *
     * @param name
     *            name of railroad
     * @param group
     *            group of railroad
     * @param position
     *            position on board
     * @param moneyTable
     *            hashTable of money values
     * @param bank
     *            bank associated with railroad
     */
    public Railroad(
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
        System.out.println(ownedSpaces.size());
        return getMoneyTable().get("rent" + ownedSpaces.size());
    }
}
