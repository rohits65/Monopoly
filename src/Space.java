/**
 * Represents a space on the board.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class Space
    implements Comparable<Space>
{

    private String name;
    private int    group;
    private int    position;

    /**
     * Create a new Space object.
     *
     * @param name
     *            name of space
     * @param group
     *            group of space
     * @param position
     *            position on board
     */
    public Space(String name, int group, int position)
    {
        this.name = name;
        this.group = group;
        this.position = position;
    }


    /**
     * Get position of space.
     *
     * @return position
     */
    public int getPosition()
    {
        return position;
    }


    /**
     * Get group of space.
     *
     * @return group
     */
    public int getGroup()
    {
        return group;
    }


    /**
     * Get name of space.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }


    public int compareTo(Space other)
    {
        return getPosition() - other.getPosition();
    }


    public String toString()
    {
        return name + "\n-------------------------------";
    }
}
