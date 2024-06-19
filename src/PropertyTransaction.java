/**
 *  Represents a property transaction between two player through the bank.
 *  Figures out if both parties are interested and executes the transaction with the bank if so.
 *
 *  @author rohit
 *  @version May 26, 2021
 */
public class PropertyTransaction {
    private Player currentOwner;
    private Player targetedOwner;
    private Property property;
    private Bank bank;

    private boolean auction;
    private int price;
    private boolean liftMortgage;

    /**
     * Create a new PropertyTransaction object.
     * @param property property that is being moved
     * @param price price of transaction
     * @param targetedOwner owner to send to
     * @param bank bank associated with transaction
     */
    public PropertyTransaction(Property property, int price, Player targetedOwner, Bank bank) {
        this.targetedOwner = targetedOwner;
        this.property = property;
        this.price = price;
        this.bank = bank;
        this.liftMortgage = true;
        this.auction = false;
        currentOwner = bank.getPropertyOwner(this.property);
    }

    /**
     * Gets property in transaction.
     * @return property
     */
    public Property getProperty() {
        return property;
    }

    /**
     * Gets the current owner of the property
     * @return owner
     */
    public Player getCurrentOwner() {
        return currentOwner;
    }

    /**
     * Gets the player that the transaction is selling to.
     * @return player
     */
    public Player getTargetedOwner() {
        return targetedOwner;
    }

    /**
     * Gets the price of the transaction.
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Determines if the player will accept or deny the transaction.
     * @return true if accepted, false otherwise
     */
    public boolean processTransaction() {
        int requestResult = requestTargetedOwner();
        if (requestResult == getPrice()) {
            if (getProperty().isMortgaged()) {
                requestTargetedOwnerLiftMortgage();
            }
            acceptTransaction();
            return true;
        }
        currentOwner.getPlayerGUI().addMessage(getTargetedOwner().getName() + " will only buy the property for $" + requestResult);
        return false;
    }

    /**
     * Requests player to buy the property at a price
     * @return price the player wants to buy at
     */
    public int requestTargetedOwner() {
        return bank.sendRequest(targetedOwner, "Buy " + property.getName() + " for $" + price + "?", price);
    }

    /**
     * Asks if the targeted owner wants to lift the mortgage of the property.
     */
    public void requestTargetedOwnerLiftMortgage() {
        liftMortgage = bank.sendRequest(targetedOwner, "Lift mortagage of property?");
    }

    /**
     * Asks to auction the property.
     */
    public void askToAuction() {
        if (targetedOwner instanceof ComputerPlayer) auction = ((ComputerPlayer) targetedOwner).shouldAuctionProperty();
        else auction = bank.sendRequest(targetedOwner, "Do you want to auction this property?");
    }

    /**
     * Accepts the transaction and executes it based on its fields.
     */
    public void acceptTransaction() {
        if (auction) {
            int currentBid = 0;
            Player highestBidder = targetedOwner;

            int declineCnt = 0;

            while (declineCnt < bank.getPlayers().size()-1) {
                for (Player player : bank.getPlayers()) {
                    if (!player.equals(targetedOwner)) {
                        int recentBid = bank.sendRequest(player, "Current bid at " + currentBid + ". Pass on bidding?", currentBid);
                        if (recentBid > currentBid) {
                            highestBidder = player;
                            currentBid = recentBid;
                        } else {
                            declineCnt++;
                        }
                    }
                }
            }
            targetedOwner = highestBidder;
            price = currentBid;

            bank.sendMessage(highestBidder.getName() + " won the bid for " + property.getName() + " at " + currentBid);
        }

        requestTargetedOwnerLiftMortgage();

        if (currentOwner != null) {
            currentOwner.removeProperty(property);
            currentOwner.receive(price);
            // TODO add case where currentOwner does not have mortgage
            if (liftMortgage) {
                property.liftMortgage();
                targetedOwner.pay((int) (property.getMortgageAmt() * 1.10 + 0.5));
                targetedOwner.pay(price);
                targetedOwner.addProperty(property);
                property.setOwnership(false);
                bank.sendMessage(currentOwner, property.getName() + " has been sold to " + targetedOwner.getName() + " for " + price);
                bank.sendMessage(targetedOwner, "You have bought " + property.getName() + " from " + currentOwner.getName() + " for " + price);
            } else {
                targetedOwner.pay((int) (property.getMortgageAmt() * 0.10 + 0.5));
                targetedOwner.addProperty(property);
                property.setOwnership(true);
                bank.sendMessage(currentOwner, property.getName() + " has been mortgaged to " + targetedOwner.getName() + " for " + price);
                bank.sendMessage(targetedOwner, "You have mortgaged " + property.getName() + " from " + currentOwner.getName() + " for " + price);
            }
        }

        else if (liftMortgage) {
            property.liftMortgage();

            targetedOwner.pay(price);
            targetedOwner.addProperty(property);
            property.setOwnership(false);
            bank.sendMessage(targetedOwner, "You have bought " + property.getName() + " from the bank for " + price);
        } else {
            targetedOwner.pay(property.getMortgageAmt());
            targetedOwner.addProperty(property);
            property.setOwnership(true);
            bank.sendMessage(targetedOwner, "You have mortgaged " + property.getName() + " from the bank");
        }
        property.setSellPrice(price);
    }
}
