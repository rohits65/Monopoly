import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

/**
 * Represents the main class for Monopoly. Generates information for all
 * properties and creates the bank and board.
 *
 * @author rohit
 * @version May 27, 2021
 */
public class Monopoly
{
    /**
     * Main method.
     *
     * @param args
     *            none
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome");
        Bank myBank = new Bank();

        // Go Space
        Space goSpace = new Space("Go", 13, 0);

        // Mediterranean Avenue
        HashMap<String, Integer> mediterraneanAvenueMoneyTable = new HashMap<String, Integer>();
        mediterraneanAvenueMoneyTable.put("listPrice", 60);
        mediterraneanAvenueMoneyTable.put("mortgage", 30);
        mediterraneanAvenueMoneyTable.put("buildingPrice", 50);
        mediterraneanAvenueMoneyTable.put("rent0", 2);
        mediterraneanAvenueMoneyTable.put("rent1", 10);
        mediterraneanAvenueMoneyTable.put("rent2", 30);
        mediterraneanAvenueMoneyTable.put("rent3", 90);
        mediterraneanAvenueMoneyTable.put("rent4", 160);
        mediterraneanAvenueMoneyTable.put("rent5", 250);
        Property mediterraneanAvenue =
            new Building("Mediterranean Avenue", 1, 1, mediterraneanAvenueMoneyTable, myBank);

        // Community Chest
        CommunityCard firstCommunityCard = new CommunityCard("Community Chest", 11, 2, myBank);

        // Baltic Avenue
        HashMap<String, Integer> balticAvenueMoneyTable = new HashMap<String, Integer>();
        balticAvenueMoneyTable.put("listPrice", 60);
        balticAvenueMoneyTable.put("mortgage", 30);
        balticAvenueMoneyTable.put("buildingPrice", 50);
        balticAvenueMoneyTable.put("rent0", 4);
        balticAvenueMoneyTable.put("rent1", 20);
        balticAvenueMoneyTable.put("rent2", 60);
        balticAvenueMoneyTable.put("rent3", 180);
        balticAvenueMoneyTable.put("rent4", 320);
        balticAvenueMoneyTable.put("rent5", 450);
        Property balticAvenue = new Building("Baltic Avenue", 1, 3, balticAvenueMoneyTable, myBank);

        // Income Tax
        TaxCard incomeTaxCard = new TaxCard("Income Tax", 13, 4, myBank, true);

        // Reading Railroad
        HashMap<String, Integer> readingRailroadMoneyTable = new HashMap<String, Integer>();
        readingRailroadMoneyTable.put("listPrice", 200);
        readingRailroadMoneyTable.put("mortgage", 100);
        readingRailroadMoneyTable.put("rent1", 25);
        readingRailroadMoneyTable.put("rent2", 50);
        readingRailroadMoneyTable.put("rent3", 100);
        readingRailroadMoneyTable.put("rent4", 200);
        Railroad readingRailroad =
            new Railroad("Reading Railroad", 9, 5, readingRailroadMoneyTable, myBank);

        // Oriental Avenue
        HashMap<String, Integer> orientalAvenueMoneyTable = new HashMap<String, Integer>();
        orientalAvenueMoneyTable.put("listPrice", 100);
        orientalAvenueMoneyTable.put("mortgage", 50);
        orientalAvenueMoneyTable.put("buildingPrice", 50);
        orientalAvenueMoneyTable.put("rent0", 6);
        orientalAvenueMoneyTable.put("rent1", 30);
        orientalAvenueMoneyTable.put("rent2", 90);
        orientalAvenueMoneyTable.put("rent3", 270);
        orientalAvenueMoneyTable.put("rent4", 400);
        orientalAvenueMoneyTable.put("rent5", 550);
        Property orientalAvenue =
            new Building("Oriental Avenue", 2, 6, orientalAvenueMoneyTable, myBank);

        // Chance Chest
        ChanceCard firstChanceCard = new ChanceCard("Chance Chest", 12, 7, myBank);

        // Vermont Avenue
        HashMap<String, Integer> vermontAvenueMoneyTable = new HashMap<String, Integer>();
        vermontAvenueMoneyTable.put("listPrice", 100);
        vermontAvenueMoneyTable.put("mortgage", 50);
        vermontAvenueMoneyTable.put("buildingPrice", 50);
        vermontAvenueMoneyTable.put("rent0", 6);
        vermontAvenueMoneyTable.put("rent1", 30);
        vermontAvenueMoneyTable.put("rent2", 90);
        vermontAvenueMoneyTable.put("rent3", 270);
        vermontAvenueMoneyTable.put("rent4", 400);
        vermontAvenueMoneyTable.put("rent5", 550);
        Property vermontAvenue =
            new Building("Vermont Avenue", 2, 8, vermontAvenueMoneyTable, myBank);

        // Connecticut Avenue
        HashMap<String, Integer> connecticutAvenueMoneyTable = new HashMap<String, Integer>();
        connecticutAvenueMoneyTable.put("listPrice", 100);
        connecticutAvenueMoneyTable.put("mortgage", 50);
        connecticutAvenueMoneyTable.put("buildingPrice", 50);
        connecticutAvenueMoneyTable.put("rent0", 6);
        connecticutAvenueMoneyTable.put("rent1", 30);
        connecticutAvenueMoneyTable.put("rent2", 90);
        connecticutAvenueMoneyTable.put("rent3", 270);
        connecticutAvenueMoneyTable.put("rent4", 400);
        connecticutAvenueMoneyTable.put("rent5", 550);
        Property connecticutAvenue =
            new Building("Connecticut Avenue", 2, 9, connecticutAvenueMoneyTable, myBank);

        // Jail Space
        Space jailSpace = new Space("Jail", 13, 10);

        // St. Charles Place
        HashMap<String, Integer> stCharlesPlaceMoneyTable = new HashMap<String, Integer>();
        stCharlesPlaceMoneyTable.put("listPrice", 140);
        stCharlesPlaceMoneyTable.put("mortgage", 70);
        stCharlesPlaceMoneyTable.put("buildingPrice", 100);
        stCharlesPlaceMoneyTable.put("rent0", 10);
        stCharlesPlaceMoneyTable.put("rent1", 50);
        stCharlesPlaceMoneyTable.put("rent2", 150);
        stCharlesPlaceMoneyTable.put("rent3", 450);
        stCharlesPlaceMoneyTable.put("rent4", 625);
        stCharlesPlaceMoneyTable.put("rent5", 750);
        Property stCharlesPlace =
            new Building("St. Charles Place", 3, 11, stCharlesPlaceMoneyTable, myBank);

        // Electric Company
        HashMap<String, Integer> electricCompanyPlaceMoneyTable = new HashMap<String, Integer>();
        electricCompanyPlaceMoneyTable.put("listPrice", 100);
        electricCompanyPlaceMoneyTable.put("mortgage", 50);
        electricCompanyPlaceMoneyTable.put("buildingPrice", 50);
        electricCompanyPlaceMoneyTable.put("rent0", -1);
        Property electricCompany =
            new Building("Electric Company", 14, 12, electricCompanyPlaceMoneyTable, myBank);

        // States Avenue
        HashMap<String, Integer> statesAvenuePlaceMoneyTable = new HashMap<String, Integer>();
        statesAvenuePlaceMoneyTable.put("listPrice", 140);
        statesAvenuePlaceMoneyTable.put("mortgage", 70);
        statesAvenuePlaceMoneyTable.put("buildingPrice", 100);
        statesAvenuePlaceMoneyTable.put("rent0", 10);
        statesAvenuePlaceMoneyTable.put("rent1", 50);
        statesAvenuePlaceMoneyTable.put("rent2", 150);
        statesAvenuePlaceMoneyTable.put("rent3", 450);
        statesAvenuePlaceMoneyTable.put("rent4", 625);
        statesAvenuePlaceMoneyTable.put("rent5", 750);
        Property statesAvenue =
            new Building("States Avenue", 3, 13, statesAvenuePlaceMoneyTable, myBank);

        // Virginia Avenue
        HashMap<String, Integer> virginiaAvenuePlaceMoneyTable = new HashMap<String, Integer>();
        virginiaAvenuePlaceMoneyTable.put("listPrice", 100);
        virginiaAvenuePlaceMoneyTable.put("mortgage", 50);
        virginiaAvenuePlaceMoneyTable.put("buildingPrice", 50);
        virginiaAvenuePlaceMoneyTable.put("rent0", 6);
        virginiaAvenuePlaceMoneyTable.put("rent1", 30);
        virginiaAvenuePlaceMoneyTable.put("rent2", 90);
        virginiaAvenuePlaceMoneyTable.put("rent3", 270);
        virginiaAvenuePlaceMoneyTable.put("rent4", 400);
        virginiaAvenuePlaceMoneyTable.put("rent5", 550);
        Property virginiaAvenue =
            new Building("Virginia Avenue", 3, 14, virginiaAvenuePlaceMoneyTable, myBank);

        // Pennsylvania Railroad
        HashMap<String, Integer> pennsylvaniaRailroadMoneyTable = new HashMap<String, Integer>();
        pennsylvaniaRailroadMoneyTable.put("listPrice", 200);
        pennsylvaniaRailroadMoneyTable.put("mortgage", 100);
        pennsylvaniaRailroadMoneyTable.put("rent1", 25);
        pennsylvaniaRailroadMoneyTable.put("rent2", 50);
        pennsylvaniaRailroadMoneyTable.put("rent3", 100);
        pennsylvaniaRailroadMoneyTable.put("rent4", 200);
        Railroad pennsylvaniaRailroad =
            new Railroad("Pennsylvania Railroad", 9, 15, pennsylvaniaRailroadMoneyTable, myBank);

        // St. James Place
        HashMap<String, Integer> stJamesPlaceMoneyTable = new HashMap<String, Integer>();
        stJamesPlaceMoneyTable.put("listPrice", 180);
        stJamesPlaceMoneyTable.put("mortgage", 90);
        stJamesPlaceMoneyTable.put("buildingPrice", 100);
        stJamesPlaceMoneyTable.put("rent0", 14);
        stJamesPlaceMoneyTable.put("rent1", 70);
        stJamesPlaceMoneyTable.put("rent2", 200);
        stJamesPlaceMoneyTable.put("rent3", 550);
        stJamesPlaceMoneyTable.put("rent4", 750);
        stJamesPlaceMoneyTable.put("rent5", 950);
        Property stJamesPlace =
            new Building("St. James Place", 4, 16, stJamesPlaceMoneyTable, myBank);

        // Community Chest
        CommunityCard secondCommunityCard = new CommunityCard("Community Chest", 11, 17, myBank);

        // Tennessee Avenue
        HashMap<String, Integer> tennesseeAvenueMoneyTable = new HashMap<String, Integer>();
        tennesseeAvenueMoneyTable.put("listPrice", 180);
        tennesseeAvenueMoneyTable.put("mortgage", 90);
        tennesseeAvenueMoneyTable.put("buildingPrice", 100);
        tennesseeAvenueMoneyTable.put("rent0", 14);
        tennesseeAvenueMoneyTable.put("rent1", 70);
        tennesseeAvenueMoneyTable.put("rent2", 200);
        tennesseeAvenueMoneyTable.put("rent3", 550);
        tennesseeAvenueMoneyTable.put("rent4", 750);
        tennesseeAvenueMoneyTable.put("rent5", 950);
        Property tennesseeAvenue =
            new Building("Tennessee Avenue", 4, 18, tennesseeAvenueMoneyTable, myBank);

        // New York Avenue
        HashMap<String, Integer> nyMoneyTable = new HashMap<String, Integer>();
        nyMoneyTable.put("listPrice", 200);
        nyMoneyTable.put("mortgage", 100);
        nyMoneyTable.put("buildingPrice", 100);
        nyMoneyTable.put("rent0", 16);
        nyMoneyTable.put("rent1", 80);
        nyMoneyTable.put("rent2", 220);
        nyMoneyTable.put("rent3", 600);
        nyMoneyTable.put("rent4", 800);
        nyMoneyTable.put("rent5", 1000);
        Property ny = new Building("New York Avenue", 4, 19, nyMoneyTable, myBank);

        // Free Parking
        Space freeParking = new Space("Free Parking", 13, 20);

        // Kentucky Avenue
        HashMap<String, Integer> kMoneyTable = new HashMap<String, Integer>();
        kMoneyTable.put("listPrice", 220);
        kMoneyTable.put("mortgage", 110);
        kMoneyTable.put("buildingPrice", 150);
        kMoneyTable.put("rent0", 18);
        kMoneyTable.put("rent1", 90);
        kMoneyTable.put("rent2", 250);
        kMoneyTable.put("rent3", 700);
        kMoneyTable.put("rent4", 875);
        kMoneyTable.put("rent5", 1050);
        Property kentuckyAvenue = new Building("Kentucky Avenue", 5, 21, kMoneyTable, myBank);

        // Chance Chest
        ChanceCard secondChanceCard = new ChanceCard("Chance Chest", 12, 22, myBank);

        // Indiana Avenue
        HashMap<String, Integer> iaMoneyTable = new HashMap<String, Integer>();
        iaMoneyTable.put("listPrice", 220);
        iaMoneyTable.put("mortgage", 110);
        iaMoneyTable.put("buildingPrice", 150);
        iaMoneyTable.put("rent0", 18);
        iaMoneyTable.put("rent1", 90);
        iaMoneyTable.put("rent2", 250);
        iaMoneyTable.put("rent3", 700);
        iaMoneyTable.put("rent4", 875);
        iaMoneyTable.put("rent5", 1050);
        Property indianaAvenue = new Building("Indiana Avenue", 5, 23, iaMoneyTable, myBank);

        // Illinois Avenue
        HashMap<String, Integer> ilaMoneyTable = new HashMap<String, Integer>();
        ilaMoneyTable.put("listPrice", 240);
        ilaMoneyTable.put("mortgage", 120);
        ilaMoneyTable.put("buildingPrice", 150);
        ilaMoneyTable.put("rent0", 20);
        ilaMoneyTable.put("rent1", 100);
        ilaMoneyTable.put("rent2", 300);
        ilaMoneyTable.put("rent3", 750);
        ilaMoneyTable.put("rent4", 925);
        ilaMoneyTable.put("rent5", 1100);
        Property illinoisAvenue = new Building("Illinois Avenue", 5, 24, ilaMoneyTable, myBank);

        // BO Railroad
        HashMap<String, Integer> BOMoneyTable = new HashMap<String, Integer>();
        BOMoneyTable.put("listPrice", 200);
        BOMoneyTable.put("mortgage", 100);
        BOMoneyTable.put("rent1", 25);
        BOMoneyTable.put("rent2", 50);
        BOMoneyTable.put("rent3", 100);
        BOMoneyTable.put("rent4", 200);
        Railroad boRailroad = new Railroad("B & O Railroad", 9, 25, BOMoneyTable, myBank);

        // Atlantic Avenue
        HashMap<String, Integer> aaMoneyTable = new HashMap<String, Integer>();
        aaMoneyTable.put("listPrice", 260);
        aaMoneyTable.put("mortgage", 130);
        aaMoneyTable.put("buildingPrice", 150);
        aaMoneyTable.put("rent0", 22);
        aaMoneyTable.put("rent1", 110);
        aaMoneyTable.put("rent2", 330);
        aaMoneyTable.put("rent3", 800);
        aaMoneyTable.put("rent4", 975);
        aaMoneyTable.put("rent5", 1150);
        Property atlanticAvenue = new Building("Atlantic Avenue", 6, 26, aaMoneyTable, myBank);

        // Ventnor Avenue
        HashMap<String, Integer> vaMoneyTable = new HashMap<String, Integer>();
        vaMoneyTable.put("listPrice", 260);
        vaMoneyTable.put("mortgage", 130);
        vaMoneyTable.put("buildingPrice", 150);
        vaMoneyTable.put("rent0", 22);
        vaMoneyTable.put("rent1", 110);
        vaMoneyTable.put("rent2", 330);
        vaMoneyTable.put("rent3", 800);
        vaMoneyTable.put("rent4", 975);
        vaMoneyTable.put("rent5", 1150);
        Property ventorAvenue = new Building("Ventor Avenue", 6, 27, vaMoneyTable, myBank);

        // Water Works
        HashMap<String, Integer> wwPlaceMoneyTable = new HashMap<String, Integer>();
        wwPlaceMoneyTable.put("listPrice", 100);
        wwPlaceMoneyTable.put("mortgage", 50);
        wwPlaceMoneyTable.put("buildingPrice", 50);
        wwPlaceMoneyTable.put("rent0", -1);
        Property waterWorks = new Building("Water Works", 14, 28, wwPlaceMoneyTable, myBank);

        // Mavin Gardens
        HashMap<String, Integer> mgMoneyTable = new HashMap<String, Integer>();
        mgMoneyTable.put("listPrice", 280);
        mgMoneyTable.put("mortgage", 140);
        mgMoneyTable.put("buildingPrice", 150);
        mgMoneyTable.put("rent0", 24);
        mgMoneyTable.put("rent1", 120);
        mgMoneyTable.put("rent2", 360);
        mgMoneyTable.put("rent3", 850);
        mgMoneyTable.put("rent4", 1025);
        mgMoneyTable.put("rent5", 1200);
        Property marvinGardens = new Building("Marvin Gardens", 6, 29, mgMoneyTable, myBank);

        // Go to jail
        Space goJailSpace = new Space("Jail", 13, 30);

        // Pacific Avenue
        HashMap<String, Integer> paMoneyTable = new HashMap<String, Integer>();
        paMoneyTable.put("listPrice", 300);
        paMoneyTable.put("mortgage", 150);
        paMoneyTable.put("buildingPrice", 200);
        paMoneyTable.put("rent0", 26);
        paMoneyTable.put("rent1", 130);
        paMoneyTable.put("rent2", 390);
        paMoneyTable.put("rent3", 900);
        paMoneyTable.put("rent4", 1100);
        paMoneyTable.put("rent5", 1275);
        Property pacificAvenue = new Building("Pacific Avenue", 7, 31, paMoneyTable, myBank);

        // North Carolina Avenue
        HashMap<String, Integer> ncaMoneyTable = new HashMap<String, Integer>();
        ncaMoneyTable.put("listPrice", 300);
        ncaMoneyTable.put("mortgage", 150);
        ncaMoneyTable.put("buildingPrice", 200);
        ncaMoneyTable.put("rent0", 26);
        ncaMoneyTable.put("rent1", 130);
        ncaMoneyTable.put("rent2", 390);
        ncaMoneyTable.put("rent3", 900);
        ncaMoneyTable.put("rent4", 1100);
        ncaMoneyTable.put("rent5", 1275);
        Property northCAvenue = new Building("North Carolina Avenue", 7, 32, ncaMoneyTable, myBank);

        // Community Chest
        CommunityCard thirdCommunityCard = new CommunityCard("Community Chest", 11, 33, myBank);

        // Pennsylvania Avenue
        HashMap<String, Integer> peaMoneyTable = new HashMap<String, Integer>();
        peaMoneyTable.put("listPrice", 300);
        peaMoneyTable.put("mortgage", 150);
        peaMoneyTable.put("buildingPrice", 200);
        peaMoneyTable.put("rent0", 26);
        peaMoneyTable.put("rent1", 130);
        peaMoneyTable.put("rent2", 390);
        peaMoneyTable.put("rent3", 900);
        peaMoneyTable.put("rent4", 1100);
        peaMoneyTable.put("rent5", 1275);
        Property pennsylvaniaAvenue =
            new Building("Pennsylvania Avenue", 7, 34, peaMoneyTable, myBank);

        // Short Line
        HashMap<String, Integer> slMoneyTable = new HashMap<String, Integer>();
        slMoneyTable.put("listPrice", 200);
        slMoneyTable.put("mortgage", 100);
        slMoneyTable.put("rent1", 25);
        slMoneyTable.put("rent2", 50);
        slMoneyTable.put("rent3", 100);
        slMoneyTable.put("rent4", 200);
        Railroad slRailroad = new Railroad("Short Line", 9, 35, BOMoneyTable, myBank);

        // Chance Chest
        ChanceCard thirdChanceCard = new ChanceCard("Chance Chest", 12, 36, myBank);

        // Park Place
        HashMap<String, Integer> ppMoneyTable = new HashMap<String, Integer>();
        ppMoneyTable.put("listPrice", 350);
        ppMoneyTable.put("mortgage", 175);
        ppMoneyTable.put("buildingPrice", 200);
        ppMoneyTable.put("rent0", 35);
        ppMoneyTable.put("rent1", 175);
        ppMoneyTable.put("rent2", 500);
        ppMoneyTable.put("rent3", 1100);
        ppMoneyTable.put("rent4", 1300);
        ppMoneyTable.put("rent5", 1500);
        Property parkPlace = new Building("Park Place", 8, 37, ppMoneyTable, myBank);

        // Luxury Tax
        TaxCard luxincomeCard = new TaxCard("Luxury Tax", 13, 38, myBank, false);

        // Boardwalk
        HashMap<String, Integer> bMoneyTable = new HashMap<String, Integer>();
        bMoneyTable.put("listPrice", 400);
        bMoneyTable.put("mortgage", 200);
        bMoneyTable.put("buildingPrice", 200);
        bMoneyTable.put("rent0", 50);
        bMoneyTable.put("rent1", 200);
        bMoneyTable.put("rent2", 600);
        bMoneyTable.put("rent3", 1400);
        bMoneyTable.put("rent4", 1700);
        bMoneyTable.put("rent5", 2000);
        Property boardwalk = new Building("Boardwalk", 8, 39, bMoneyTable, myBank);

        // Set up Community and Chance Cards
        CommunityCard.initiateInternals();
        ChanceCard.initiateInternals();

        // Add properties to myBank
        myBank.addSpace(0, goSpace);
        myBank.addSpace(1, mediterraneanAvenue);
        myBank.addSpace(2, firstCommunityCard);
        myBank.addSpace(3, balticAvenue);
        myBank.addSpace(4, incomeTaxCard);
        myBank.addSpace(5, readingRailroad);
        myBank.addSpace(6, orientalAvenue);
        myBank.addSpace(7, firstChanceCard);
        myBank.addSpace(8, vermontAvenue);
        myBank.addSpace(9, connecticutAvenue);
        myBank.addSpace(10, jailSpace);
        myBank.addSpace(11, stCharlesPlace);
        myBank.addSpace(12, electricCompany);
        myBank.addSpace(13, statesAvenue);
        myBank.addSpace(14, virginiaAvenue);
        myBank.addSpace(15, pennsylvaniaRailroad);
        myBank.addSpace(16, stJamesPlace);
        myBank.addSpace(17, secondCommunityCard);
        myBank.addSpace(18, tennesseeAvenue);
        myBank.addSpace(19, ny);
        myBank.addSpace(20, freeParking);
        myBank.addSpace(21, kentuckyAvenue);
        myBank.addSpace(22, secondChanceCard);
        myBank.addSpace(23, indianaAvenue);
        myBank.addSpace(24, illinoisAvenue);
        myBank.addSpace(25, boRailroad);
        myBank.addSpace(26, atlanticAvenue);
        myBank.addSpace(27, ventorAvenue);
        myBank.addSpace(28, waterWorks);
        myBank.addSpace(29, marvinGardens);
        myBank.addSpace(30, goJailSpace);
        myBank.addSpace(31, pacificAvenue);
        myBank.addSpace(32, northCAvenue);
        myBank.addSpace(33, thirdCommunityCard);
        myBank.addSpace(34, pennsylvaniaAvenue);
        myBank.addSpace(35, slRailroad);
        myBank.addSpace(36, thirdChanceCard);
        myBank.addSpace(37, parkPlace);
        myBank.addSpace(38, luxincomeCard);
        myBank.addSpace(39, boardwalk);

        System.out.println("Properties initialized");

        // Create variables related to game control
        AtomicBoolean startGame = new AtomicBoolean(false);
        ArrayList<String> names = new ArrayList<String>();

        JFrame frame = new JFrame("Lobby");
        JPanel controlStart = new JPanel();
        JButton addPlayerButton = new JButton("Add Player");
        JButton addComputerButton = new JButton("Add Computer Player");
        JButton startGameButton = new JButton("Start Game");

        controlStart.add(addPlayerButton);
        controlStart.add(addComputerButton);
        controlStart.add(startGameButton);

        frame.add(controlStart);

        // Create action listeners for adding player and starting game buttons
        addPlayerButton.addActionListener(addPlayer -> {
            JDialog addPlayerDialog = new JDialog(frame);
            JPanel addPlayerPanel = new JPanel();
            JLabel nameLabel = new JLabel("Enter name:");
            JTextField name = new JTextField();
            name.setColumns(10);
            JButton enterName = new JButton("Add");
            addPlayerPanel.add(nameLabel);
            addPlayerPanel.add(name);
            addPlayerPanel.add(enterName);
            addPlayerDialog.add(addPlayerPanel);
            enterName.addActionListener(goAddPlayer -> {
                if (!names.contains(name.getText()) && names.size() + myBank.getPlayers().size() < 4 )
                {
                    names.add(name.getText());
                } else {
                    myBank.sendMessage("That player or too many players have already joined.");
                }
                addPlayerDialog.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            });
            addPlayerDialog.setSize(200, 125);
            addPlayerDialog.setLocationRelativeTo(null);
            addPlayerDialog.setVisible(true);
        });

        addComputerButton.addActionListener(comp -> {
            if (names.size() + myBank.getPlayers().size() < 4 ) {
                myBank.addPlayer(new ComputerPlayer("Computer", myBank, 100, 500, 7));
            } else {
                myBank.sendMessage("That player or too many players have already joined.");
            }
        });


        startGameButton.addActionListener(start -> {
            startGame.set(true);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });

        // Position start frame
        frame.setSize(100, 100);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Wait for start game button to be pressed before continuing
        while (!startGame.get())
        {
            try
            {
                sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        // Add players that were added through the button to the bank
        for (String name : names)
        {
            myBank.addPlayer(new Player(name, myBank));
        }

        // UNCOMMENT FOR PRESENTATION
        // Sells first row of Monopoly board to the first added player
// Player presPlayer = myBank.getPlayers().get(0);
// for (int i = 1; i < 10; i++) {
// Space space = myBank.getSpaceAtPosition(i);
// if (space instanceof Property) {
// Property property = (Property) space;
// presPlayer.addProperty(property);
// PropertyTransaction transaction = new PropertyTransaction(property,
// property.getListPrice(), presPlayer, myBank);
// transaction.acceptTransaction();
// }
// }



        // Run through players as long as players exist
        while (true)
        {
            for (Player playerX : myBank.getPlayers())
            {
                if (myBank.getPlayers().size() == 1)
                {
                    myBank.sendMessage(playerX.getName() + " won the game!");
                }
                int moveOption = 0;
                if (playerX.getJailTime() != 0)
                {
                    myBank.sendMessage(playerX, "You are in jail.");
                    continue;
                }

                // Ask player for move until dice is rolled
                while (true)
                {

                    PlayerGUI playerGUI = playerX.getPlayerGUI();
                    if (playerX instanceof ComputerPlayer)
                        moveOption = ((ComputerPlayer)playerX).getMoveOption();
                    else
                        moveOption = playerGUI.displayDropDown(
                            new String[] { "Roll Dice", "Sell", "Improve Property" },
                            "It's your turn.");
                    if (moveOption == 1)
                    {
                        String[] propertyChoices = new String[playerX.getProperties().size()];
                        if (propertyChoices.length == 0)
                            continue;
                        for (int i = 0; i < playerX.getProperties().size(); i++)
                            propertyChoices[i] = playerX.getProperties().get(i).getName();
                        int propertyIndex = 0;
                        int price = 0;
                        if (playerX instanceof ComputerPlayer)
                        {
                            int[] sellInfo = ((ComputerPlayer)playerX).getPropertyIndexToSell();
                            propertyIndex = sellInfo[0];
                            price = sellInfo[1];
                        }
                        else
                        {
                            propertyIndex = playerGUI.displayDropDown(
                                propertyChoices,
                                "Which property would you like to sell?");
                            price = playerGUI.displayNumerical("For what price?");
                        }
                        Property propertyToSell = playerX.getProperty(propertyIndex);

                        String[] playerChoices = new String[myBank.getPlayers().size()];
                        for (int i = 0; i < myBank.getPlayers().size(); i++)
                            playerChoices[i] = myBank.getPlayers().get(i).getName();
                        Player sellToPlayer;
                        if (playerX instanceof ComputerPlayer)
                        {
                            sellToPlayer = ((ComputerPlayer)playerX).getPlayerToSellTo();
                        }
                        else
                        {
                            sellToPlayer = myBank.getPlayer(
                                playerGUI.displayDropDown(playerChoices, "To which player?"));
                        }
                        PropertyTransaction propertyTransaction =
                            new PropertyTransaction(propertyToSell, price, sellToPlayer, myBank);
                        propertyTransaction.processTransaction();
                    }
                    else if (moveOption == 2)
                    {
                        String[] propertyChoices = new String[playerX.getProperties().size()];
                        if (propertyChoices.length == 0)
                            continue;
                        for (int i = 0; i < playerX.getProperties().size(); i++)
                            propertyChoices[i] = playerX.getProperties().get(i).getName();
                        int propertyIndex;
                        if (playerX instanceof ComputerPlayer)
                        {
                            propertyIndex = ((ComputerPlayer)playerX).getPropertyToImprove();
                        }
                        else
                        {
                            propertyIndex = playerGUI.displayDropDown(
                                propertyChoices,
                                "Which property would you like to improve?");
                        }
                        Property propertyToImprove = playerX.getProperty(propertyIndex);

                        String[] improveOptions = new String[] { "Lift Mortgage", "Add Building" };
                        int improveIndex;
                        if (playerX instanceof ComputerPlayer)
                        {
                            improveIndex = (int)(Math.random() * 2);
                        }
                        else
                        {
                            improveIndex = playerGUI
                                .displayDropDown(improveOptions, "What would you like to do?");
                        }
                        switch (improveIndex)
                        {
                            case 0:
                                if (propertyToImprove.isMortgaged())
                                {
                                    PropertyTransaction propertyTransaction =
                                        new PropertyTransaction(
                                            propertyToImprove,
                                            0,
                                            playerX,
                                            myBank);
                                    propertyTransaction.acceptTransaction();
                                }
                                else
                                {
                                    myBank.sendMessage(playerX, "You own this property");
                                }
                                break;
                            case 1:
                                if (myBank.getPropertyOwner(propertyToImprove).getProperties()
                                    .containsAll(
                                        myBank.getSpacesByGroup(propertyToImprove.getGroup()))
                                    && !propertyToImprove.isMortgaged())
                                {
                                    myBank.addInstallment(propertyToImprove);
                                    myBank.sendMessage("Installment added to property.");
                                }
                                else
                                {
                                    myBank.sendMessage(
                                        playerX,
                                        "You don't own all properties in this group.");
                                }
                                break;
                        }
                    }
                    else if (moveOption == 0)
                    {
                        int moveAmt1 = (int)(Math.random() * 6) + 1;
                        int moveAmt2 = (int)(Math.random() * 6) + 1;
                        int moveAmt = moveAmt1 + moveAmt2;

                        // UNCOMMENT FOR PRESENTATION
                        // Provide manual input for dice roll
// int moveAmt = playerGUI.displayNumerical("Move how much?");
                        playerGUI.addMessage(
                            "You moved " + moveAmt + " spaces (" + moveAmt1 + " and " + moveAmt2
                                + ").");
                        playerX.move(moveAmt);
                        myBank.checkPlayerOptions(playerX, moveAmt);
                        for (Player player : myBank.getPlayers())
                        {
                            System.out
                                .println(player.getName() + " balance: " + player.getBalance());
                        }
                        break;
                    }
                    else if (moveOption == 3)
                    {
                        myBank.getData();
                    }
                    if (playerX.getBalance() <= 0)
                    {
                        myBank.removePlayer(playerX);
                        break;
                    }
                }
                if (playerX.getBalance() <= 0)
                {
                    myBank.removePlayer(playerX);
                    break;
                }
            }
        }
    }
}
