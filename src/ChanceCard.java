import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a chance card. Utilizes static references to retrieve the next
 * card in the pile and places the old one on the bottom.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class ChanceCard
    extends Space
{
    private static ArrayList<Integer> ids;
    private static int                idPosition;
    private static Bank               bank;

    /**
     * Create a new ChanceCard object.
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
    public ChanceCard(String name, int group, int position, Bank bank)
    {
        super(name, group, position);
        ChanceCard.bank = bank;
    }


    /**
     * Create a randomized array of values analogous to shuffling the deck prior
     * to playing.
     */
    public static void initiateInternals()
    {
        ids = new ArrayList<Integer>();
        for (int i = 0; i < 14; i++)
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
        switch (ids.get(idPosition))
        {
            case 1:
                bank.sendMessage(player, "Advance to \"Go\"");
                player.move(bank.getNumberOfSpaces() - player.getPosition());
                bank.checkPlayerOptions(player, 7);
                break;
            case 2:
                bank.sendMessage(player, "Advance to Illinois Ave.");
                if (player.getPosition() >= 24)
                {
                    player.move(bank.getNumberOfSpaces() - player.getPosition());
                    player.move(24);
                }
                else
                {
                    player.move(24 - player.getPosition());
                }
                bank.checkPlayerOptions(player, 7);
                break;
            case 3:
                bank.sendMessage(player, "Advance to St. Charles Place.");
                if (player.getPosition() >= 11)
                {
                    player.move(bank.getNumberOfSpaces() - player.getPosition());
                    player.move(11);
                }
                else
                {
                    player.move(11 - player.getPosition());
                }
                bank.checkPlayerOptions(player, 7);
                break;
            case 4:
                bank.sendMessage(
                    player,
                    "Advance token to the nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total 10 (ten) times the amount thrown.");
                int i = player.getPosition() + 1;
                while (true)
                {
                    if (bank.getSpaceAtPosition(i).getGroup() == 10)
                    {
                        player.move(i - player.getPosition());
                        // TODO increase rent by correct amount if property is
                        // already owned
                        break;
                    }
                    i++;
                }
                bank.checkPlayerOptions(player, 7);
                break;
            case 5:
                bank.sendMessage(
                    player,
                    "Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the re tal to which they are otherwise entitled. If Railroad is unowned, you may buy it from the Bank.");
                i = player.getPosition() + 1;
                while (true)
                {
                    if (bank.getSpaceAtPosition(i).getGroup() == 9)
                    {
                        player.move(i - player.getPosition());
                        // TODO correct rent amount
                        break;
                    }
                    i++;
                }
                bank.checkPlayerOptions(player, 7);
                break;
            case 6:
                bank.sendMessage(player, "Bank pays you dividend of $50.");
                player.receive(50);
                break;
            case 7:
                // Replaced get out of jail with winning a crossword puzzle
                bank.sendMessage(player, "You have won a crossword competition. Collect $100.");
                player.receive(100);
                break;
            case 8:
                bank.sendMessage(player, "Go Back Three {3} Spaces.");
                player.move(-3);
                bank.checkPlayerOptions(player, 7);
                break;
            case 9:
                bank.sendMessage(
                    player,
                    "Go to Jail. Go directly to Jail. Do not pass GO, do not collect $200.");
                player.move(10 - player.getPosition());
                player.goToJail();
                break;
            case 10:
                bank.sendMessage(
                    player,
                    "Make general repairs on all your property: For each house pay $25, For each hotel pay $100.");
                // TODO fix properties
                break;
            case 11:
                bank.sendMessage(player, "Pay poor tax of $15");
                player.pay(15);
                break;
            case 12:
                bank.sendMessage(player, "Take a trip to Reading Railroad.");
                if (player.getPosition() >= 5)
                {
                    player.move(bank.getNumberOfSpaces() - player.getPosition());
                    player.move(5);
                }
                else
                {
                    player.move(5 - player.getPosition());
                }
                bank.checkPlayerOptions(player, 7);
                break;
            case 13:
                bank.sendMessage(player, "Take a walk on the baordwalk. Advance token to boardwalk.");
                if (player.getPosition() >= 39)
                {
                    player.move(bank.getNumberOfSpaces() - player.getPosition());
                    player.move(39);
                }
                else
                {
                    player.move(39 - player.getPosition());
                }
                bank.checkPlayerOptions(player, 7);
                break;
            case 14:
                bank.sendMessage(
                    player,
                    "You have been elected Chairman of the bank. Pay each player $50.");
                ArrayList<Player> players = bank.getPlayers();
                for (Player playerInGroup : players)
                {
                    if (!playerInGroup.equals(player))
                    {
                        playerInGroup.receive(50);
                        player.pay(50);
                    }
                }
                break;
            case 15:
                bank.sendMessage(
                    player,
                    "Your building {and} loan matures. Receive {Collect} $150.");
                player.receive(150);
                break;
        }
        idPosition = (++idPosition % 16);
    }
}
