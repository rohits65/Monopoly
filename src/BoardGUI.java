
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the GUI for the board. Contains methods to add button layouts over
 * the map and create windows for each user.
 *
 * @author rohit
 * @version May 26, 2021
 */
public class BoardGUI
    extends JFrame
{
    private JLayeredPane               pane;
    private JTextArea                  everyoneMessages;
    private HashMap<Player, JTextArea> playerJPanelHashMap;

    // Player panels
    private ArrayList<JTextArea>       playerPanelArray;
    private JPanel                     playerPanels;

    /**
     * Create a new BoardGUI object.
     *
     * @param bank
     *            bank associated with BoardGUI
     */
    public BoardGUI(Bank bank)
    {
        super("Monopoly");

        playerJPanelHashMap = new HashMap<Player, JTextArea>();

        setSize(750, 800);
        pane = getLayeredPane();

        // Creating background board
        JLabel imageLabel = new JLabel(new ImageIcon("mono.png"));
        imageLabel.setBounds(0, 0, 750, 800);

        everyoneMessages = new JTextArea(1, 1);
        everyoneMessages.setEditable(false);
        JScrollPane previousMessagesScroll = new JScrollPane(everyoneMessages);
        previousMessagesScroll
            .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        previousMessagesScroll.setBounds(100, 125, 550, 160);

        // adding buttons on pane
        pane.add(previousMessagesScroll, 2);
        pane.add(imageLabel, 3);

        // Create player status windows
        playerPanelArray = new ArrayList<JTextArea>();

        GridLayout playerGrid = new GridLayout(2, 2);
        playerPanels = new JPanel(playerGrid);

        playerPanels.setBounds(100, 285, 550, 390);
        pane.add(playerPanels, 2);

        // Add transparent buttons over each property
        ArrayList<JButton> properties = new ArrayList<JButton>();

        int startX = 588;
        for (int i = 0; i < 9; i++)
        {
            JButton button = new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBounds(startX - i * 61, 673, 66, 107);
            properties.add(button);
            pane.add(button, 1);
        }

        JButton jailSpace = new JButton();
        jailSpace.setOpaque(false);
        jailSpace.setContentAreaFilled(false);
        jailSpace.setBorderPainted(false);
        jailSpace.setBounds(0, 673, 100, 107);
        properties.add(jailSpace);
        pane.add(jailSpace, 1);

        int startY = 613;
        for (int i = 0; i < 9; i++)
        {
            JButton button = new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBounds(-5, startY - i * 61, 107, 66);
            properties.add(button);
            pane.add(button, 1);
        }

        JButton fp = new JButton();
        fp.setOpaque(false);
        fp.setContentAreaFilled(false);
        fp.setBorderPainted(false);
        fp.setBounds(-5, 25, 107, 100);
        properties.add(fp);
        pane.add(fp, 1);

        int startX2 = 96;
        for (int i = 0; i < 9; i++)
        {
            JButton button = new JButton();
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setBounds(startX2 + i * 61, 20, 66, 107);
            properties.add(button);
            pane.add(button, 1);
        }

        int b = 0;
        for (b = 0; b < properties.size(); b++)
        {
            int finalB = b;
            properties.get(b).addActionListener(e -> {
                sendMessageToEveryone(bank.getSpaceAtPosition(finalB + 1).toString());
            });
        }

        this.setVisible(true);
    }


    /**
     * Adds a message to the main message board.
     *
     * @param message
     *            message to add
     */
    public void sendMessageToEveryone(String message)
    {
        everyoneMessages.setText(everyoneMessages.getText() + message + "\n");
    }


    /**
     * Creates a window with player data.
     *
     * @param player
     *            player to create window for
     */
    public void createPlayerDataWindow(Player player)
    {
        JTextArea playerPanel = new JTextArea();
        playerPanel.setEditable(false);
        JScrollPane playerPanelScroll = new JScrollPane(playerPanel);
        playerPanelScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        playerJPanelHashMap.put(player, playerPanel);

        playerPanelArray.add(playerPanel);
        playerPanels.add(playerPanelScroll);

        playerPanel.setText(player.toString());
        this.revalidate();
    }


    /**
     * Updates player windows with the most recent information.
     */
    public void updatePlayerDataWindows()
    {
        for (Player player : playerJPanelHashMap.keySet())
        {
            playerJPanelHashMap.get(player).setText(player.toString());
        }
        this.revalidate();
    }
}
