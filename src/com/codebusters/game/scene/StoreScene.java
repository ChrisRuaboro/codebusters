package com.codebusters.game.scene;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class StoreScene {
    private final JPanel storePanel;
    private final JPanel buySellBtnPanel;
    private final JLabel backgroundImg;
    private final JSplitPane inventories;
    private JTextArea userInventory;
    private JTextArea storeInventory;
    private final JButton buyBtn = new JButton("Buy");
    private final JButton sellBtn = new JButton("Sell");
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 16);

    public StoreScene() {
        storePanel = new JPanel();

        //create background image
        BufferedImage bgImg = null;
        InputStream is = getClass().getClassLoader().getResourceAsStream("helpBgImage.png");
        try {
            assert is != null;
            bgImg = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert bgImg != null;
        Image helpBgImg = bgImg.getScaledInstance(880, 690, Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(helpBgImg); //create ImageIcon from image to pass to label
        //set image, size, layout
        backgroundImg = new JLabel(image);
        backgroundImg.setSize(880,690);
        //label will act as a panel that will eventually be added to the main help panel
        backgroundImg.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //set title
        JLabel storeTitle = new JLabel("Game Store");
        storeTitle.setPreferredSize(new Dimension(480, 46));
        storeTitle.setForeground(Color.decode("#e76f51")); //title text color
        Font titleFont = new Font("Times New Roman", Font.BOLD, 32);
        storeTitle.setFont(titleFont); //title font
        storeTitle.setHorizontalAlignment(JLabel.CENTER);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(-170,0,0,0);
        //add to the backgroundImg Label
        backgroundImg.add(storeTitle, constraints);

        //set labels for inventory
        JLabel userInventoryLabel = new JLabel("PLAYER INVENTORY");
        userInventoryLabel.setForeground(Color.decode("#635147"));
        userInventoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
        userInventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel storeInventoryLabel = new JLabel("STORE INVENTORY");
        storeInventoryLabel.setForeground(Color.decode("#635147"));
        storeInventoryLabel.setFont(new Font("Arial", Font.BOLD, 13));
        storeInventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel inventoryLabelsPanel = new JPanel();
        inventoryLabelsPanel.setPreferredSize(new Dimension(600, 20));
        inventoryLabelsPanel.setBackground(Color.decode("#EDE5D0"));
        inventoryLabelsPanel.setLayout(new GridLayout(1,2));
        inventoryLabelsPanel.add(userInventoryLabel);
        inventoryLabelsPanel.add(storeInventoryLabel);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(0,0,0,0);
        backgroundImg.add(inventoryLabelsPanel, constraints);

        //set up user inventory text
        userInventory = new JTextArea();
        userInventory.setBackground(Color.decode("#EDE5D0"));
        userInventory.setForeground(Color.black);
        userInventory.setFont(normalFont);
        userInventory.setLineWrap(true);
        userInventory.setWrapStyleWord(true);
        userInventory.setEditable(false);

        //set up store inventory text
        storeInventory = new JTextArea();
        storeInventory.setBackground(Color.decode("#EDE5D0"));
        storeInventory.setForeground(Color.black);
        storeInventory.setFont(normalFont);
        storeInventory.setLineWrap(true);
        storeInventory.setWrapStyleWord(true);
        storeInventory.setEditable(false);

        //set up inventories
        inventories = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, userInventory, storeInventory);
        inventories.setPreferredSize(new Dimension(600, 300));
        inventories.setBackground(Color.decode("#EDE5D0"));
        inventories.setForeground(Color.black);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0,0,20,0);
        inventories.setResizeWeight(0.5);
        //add to backgroundImg label
        backgroundImg.add(inventories, constraints);


        //Initialize buy sell buttons
        ArrayList<JButton> buttons = new ArrayList<>(Arrays.asList(buyBtn, sellBtn));
        for (JButton button : buttons) {
            button.setForeground(Color.white);
            button.setBackground(Color.darkGray);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            button.setPreferredSize(new Dimension(100,35));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setName("button");
        }

        //Setup button panel, add buttons to panel
        buySellBtnPanel = new JPanel();
        buySellBtnPanel.setPreferredSize(new Dimension(300,90));
        buySellBtnPanel.setBackground(Color.decode("#EDE5D0"));
        buySellBtnPanel.setLayout(new GridLayout(2,3, 4,4));
        for (JButton button : buttons) {
            button.setOpaque(true);
            button.setBorderPainted(false);
            buySellBtnPanel.add(button);
        }
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0,0,-50,0);
        //add to backgroundImg label
        backgroundImg.add(buySellBtnPanel, constraints);

        //Setup exit screen text to inform user how to quit the helper window
        JTextArea howToResumeGame = new JTextArea("-- Press [ESC] to exit store screen --");
        Font helperFont = new Font("Times New Roman", Font.ITALIC, 28);
        howToResumeGame.setFont(helperFont);
        howToResumeGame.setBackground(Color.decode("#EDE5D0"));
        howToResumeGame.setForeground(Color.BLUE);
        howToResumeGame.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(100,0,-100,0);
        //add to backgroundImg label
        backgroundImg.add(howToResumeGame, constraints);
        //add backgroundImg to the main help panel
        storePanel.add(backgroundImg);
    }

    //**** GETTERS ****//

    public JPanel getStorePanel() {
        return storePanel;
    }

    public JPanel getBuySellBtnPanel() {
        return buySellBtnPanel;
    }

    public JLabel getBackgroundImg() {
        return backgroundImg;
    }

    public JSplitPane getInventories() {
        return inventories;
    }

    public JButton getBuyBtn() {
        return buyBtn;
    }

    public JButton getSellBtn() {
        return sellBtn;
    }

    public JTextArea getUserInventory() {
        return userInventory;
    }

    public JTextArea getStoreInventory() {
        return storeInventory;
    }
}