import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import static java.lang.Thread.sleep;

/**
 * Represents a player GUI for a player. Contains various functions to display
 * input options as well as a player window
 *
 * @author rohit
 * @version May 26, 2021
 */
public class PlayerGUI
{
    private String    playerName;
    private JFrame    frame;
    private JPanel    previousMessagesPanel;
    private JTextArea previousMessages;
    private JButton   goButton;
    private int       numericalInput;
    private boolean   proceedNumerical;
    private int       dropdownInputIndex;
    private boolean   proceedDropdown;

    /**
     * Create a new PlayerGUI object.
     *
     * @param playerName
     *            name of the player
     */
    public PlayerGUI(String playerName)
    {
        this.playerName = playerName;
        frame = new JFrame(playerName);
        previousMessagesPanel = new JPanel();
        previousMessages = new JTextArea(16, 32);
        previousMessages.setEditable(false);
        JScrollPane previousMessagesScroll = new JScrollPane(previousMessages);
        previousMessagesScroll
            .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        previousMessagesPanel.add(previousMessagesScroll);

        frame.add(previousMessagesPanel, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Adds message to the player window.
     *
     * @param message
     *            message to add
     */
    public void addMessage(String message)
    {
        previousMessages.setText(previousMessages.getText() + message + "\n");
    }


    /**
     * Displays drop down with options for a player.
     *
     * @param choices
     *            choices to display
     * @param message
     *            message to show with dropdown
     * @return index of choice that was selected
     */
    public int displayDropDown(String[] choices, String message)
    {
        reset();
        JDialog askForDropdown = new JDialog(frame, playerName);
        JPanel dropdownPanel = new JPanel();
        JLabel label = new JLabel(message);
        JComboBox<String> dropdown = new JComboBox<String>(choices);
        goButton = new JButton("Go!");

        dropdownPanel.add(label);
        dropdownPanel.add(dropdown);
        dropdownPanel.add(goButton);

        askForDropdown.add(dropdownPanel);
        askForDropdown.setSize(300, 100);

        askForDropdown.setLocationRelativeTo(null);
        askForDropdown.setVisible(true);

        goButton.addActionListener(e -> {
            dropdownInputIndex = dropdown.getSelectedIndex();
            proceedDropdown = true;
            askForDropdown
                .dispatchEvent(new WindowEvent(askForDropdown, WindowEvent.WINDOW_CLOSING));
        });
        while (!proceedDropdown)
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
        return dropdownInputIndex;
    }


    /**
     * Display a numerical input option.
     *
     * @param message
     *            message to display with numerical input field
     * @return price
     */
    public int displayNumerical(String message)
    {
        reset();
        JDialog askForNumerical = new JDialog(frame, playerName);
        JPanel numericalPanel = new JPanel();
        JLabel label = new JLabel(message);
        NumberFormat numericalFormat = NumberFormat.getNumberInstance();
        JFormattedTextField numericalEntry = new JFormattedTextField(numericalFormat);
        numericalEntry.setColumns(10);
        numericalEntry.setValue(0);
        goButton = new JButton("Go!");

        numericalPanel.add(label);
        numericalPanel.add(numericalEntry);
        numericalPanel.add(goButton);

        askForNumerical.add(numericalPanel);
        askForNumerical.setSize(200, 120);

        askForNumerical.setLocationRelativeTo(null);
        askForNumerical.setVisible(true);

        goButton.addActionListener(e -> {
            numericalInput = Integer.parseInt(numericalEntry.getText());
            proceedNumerical = true;
            askForNumerical
                .dispatchEvent(new WindowEvent(askForNumerical, WindowEvent.WINDOW_CLOSING));
        });
        while (!proceedNumerical)
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
        return numericalInput;
    }


    /**
     * Resets the GUI by removing all extraneous windows.
     */
    public void reset()
    {
        proceedDropdown = false;
        proceedNumerical = false;
        numericalInput = 0;
        dropdownInputIndex = 0;
    }
}
