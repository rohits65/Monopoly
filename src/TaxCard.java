/**
 * Represents a tax card.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class TaxCard
    extends Space
{
    private Bank    bank;
    private boolean incomeTax;
    private Player  player;

    /**
     * Create a new TaxCard object.
     *
     * @param name
     *            name of tax card
     * @param group
     *            group of card
     * @param position
     *            position on board
     * @param bank
     *            bank associated with card
     * @param incomeTax
     *            true if income tax, false if luxury tax
     */
    public TaxCard(String name, int group, int position, Bank bank, boolean incomeTax)
    {
        super(name, group, position);
        this.bank = bank;
        this.player = null;
        this.incomeTax = incomeTax;
    }


    /**
     * Sets player being affected by card.
     *
     * @param player
     *            player to tax
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }


    /**
     * Pays tax of the player who is being taxed
     */
    public void payTax()
    {
        if (incomeTax)
        {
            if (bank.sendRequest(player, "10% (y) or $200 (n)"))
            {
                player.pay((int)(player.getBalance() * 0.1 + 0.5));
                bank.sendMessage(
                    player,
                    "You paid " + (int)(player.getBalance() * 0.1 + 0.5) + " in tax.");
            }
            else
            {
                player.pay(200);
                bank.sendMessage(player, "You paid $200.");
            }
        }
        else
        {
            bank.sendMessage(player, "You are paying an luxury tax of $75");
            player.pay(75);
        }
        player = null;
    }
}
