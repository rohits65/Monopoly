import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a community card. Utilizes static references to retrieve the next
 * card in the pile and places the old one on the bottom.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class CommunityCard
    extends Space
{
    private static ArrayList<Integer> ids;
    private static int                idPosition;
    private static Bank               bank;

    /**
     * Create a new CommunityCard object.
     *
     * @param name
     *            name of card
     * @param group
     *            group of card
     * @param position
     *            position on board
     * @param bank
     *            bank that represents the card
     */
    public CommunityCard(String name, int group, int position, Bank bank)
    {
        super(name, group, position);
        CommunityCard.bank = bank;
    }


    /**
     * Create a randomized array of values analogous to shuffling the deck prior
     * to playing.
     */
    public static void initiateInternals()
    {
        ids = new ArrayList<Integer>();
        for (int i = 0; i < 15; i++)
        {
            ids.add(i + 1);
        }
        Collections.shuffle(ids);
        idPosition = 0;
    }


    /**
     * Executes the next card on the pile for the player provided.
     *
     * @param player
     *            player to execute action on
     */
    public static void executeAction(Player player)
    {
        ArrayList<Player> players = bank.getPlayers();
        int caseNum = ids.get(idPosition);
        switch (caseNum)
        {
            case 1:
                bank.sendMessage(player, "Advance to \"Go\"");
                player.move(bank.getNumberOfSpaces() - player.getPosition());
                break;
            case 2:
                bank.sendMessage(player, "Bank error in your favor. Collect $200.");
                player.receive(200);
                break;
            case 3:
                bank.sendMessage(player, "Doctor's fees. Pay $50.");
                player.pay(50);
                break;
            case 4:
                bank.sendMessage(player, "From sale of stock you get $50.");
                player.receive(50);
                break;
            case 5:
                player.canLeaveJailFree();
                break;
            case 6:
                bank.sendMessage(
                    player,
                    "Go to Jail. Go directly to Jail. Do not pass GO, do not collect $200.");
                player.move(10 - player.getPosition());
                player.goToJail();
                break;
            case 7:
                bank.sendMessage(
                    player,
                    "Grand Opera Night. Collect $50 from every player for opening night seats.");

                for (Player playerInGroup : players)
                {
                    if (!playerInGroup.equals(player))
                    {
                        playerInGroup.pay(50);
                        player.receive(50);
                    }
                }
                break;
            case 8:
                bank.sendMessage(player, "Holiday Fund matures. Receive $100.");
                player.receive(100);
                break;
            case 9:
                bank.sendMessage(player, "Income tax refund. Collect $20.");
                player.receive(20);
                break;
            case 10:
                bank.sendMessage(player, "It is your birthday. Collect $10 from every player.");
                for (Player playerInGroup : players)
                {
                    if (!playerInGroup.equals(player))
                    {
                        playerInGroup.pay(10);
                        player.receive(10);
                    }
                }
                break;
            case 11:
                bank.sendMessage(player, "Life insurance matures – Collect $100");
                player.receive(100);
                break;
            case 12:
                bank.sendMessage(player, "Hospital Fees. Pay $50.");
                player.pay(50);
                break;
            case 13:
                bank.sendMessage(player, "School fees. Pay $50.");
                player.pay(50);
                break;
            case 14:
                bank.sendMessage(player, "Receive $25 consultancy fee.");
                player.receive(25);
                break;
            case 15:
                bank.sendMessage(
                    player,
                    "You are assessed for street repairs: Pay $40 per house and $115 per hotel you own.");
                int totalPrice = 0;
                for (Property property : player.getOwnedProperties())
                {
                    if (property instanceof Building)
                    {
                        Building building = (Building)property;
                        totalPrice += building.getNumHotels() * 115;
                        totalPrice += building.getNumHouses() * 40;
                    }
                }
                player.pay(totalPrice);
                break;
            case 16:
                bank.sendMessage(
                    player,
                    "You have won second prize in a beauty contest. Collect $10.");
                player.receive(10);
                break;
        }
        idPosition = (++idPosition % 16);
    }
}
