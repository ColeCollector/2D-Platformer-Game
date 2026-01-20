package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Represents the panel in which the scoreboard is displayed.
 */

// Credit to B02-SpaceInvadersBase
public class ScorePanel extends JPanel {
    private static final int LBL_WIDTH = 110;
    private static final int LBL_HEIGHT = 30;
    private Game game;
    private JLabel healthLbl;
    private JLabel enemyLbl;


    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Game g) {
        game = g;

        setBackground(new Color(180, 180, 180));
        healthLbl = new JLabel("Health: " + game.getHealth());
        healthLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(healthLbl);
        
        add(Box.createHorizontalStrut(10));
        enemyLbl = new JLabel("Enemy Count: " + game.getEnemies().size());
        enemyLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(enemyLbl);
        
        add(Box.createHorizontalStrut(10));
        addButtons();
    }

    // effects: adds a button to the score panel
    public void addButtons() {
        JButton loadBtn = new JButton("Load");

        loadBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.load();
            }
        });

        add(loadBtn);

        JButton saveBtn = new JButton("Save");

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.save();
            }
        });
        
        add(saveBtn);
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates health of the player
    public void update() {
        healthLbl.setText("Health: " + game.getHealth());
        enemyLbl.setText("Enemy Count: " + game.getEnemies().size());
        repaint();
    }
}
