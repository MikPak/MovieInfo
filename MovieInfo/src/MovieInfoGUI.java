/**
MovieInfoGUI-class
------------------------

MovieInfoGUI-Class is for creating a graphical user interface for our app.

Authors: Mikko Pakkanen, Mikko Tella 
Date: 04.03.2015
**/

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MovieInfoGUI extends JFrame {
    private GridBagLayout layout = new GridBagLayout();
    
    public MovieInfoGUI(String title) {
        super(title);
        setLayout(layout); // Set layout as GridBagLayout
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,600);
        
        // makeButton (display text, Layout type, cell span, height, width, row number, column number)
        makeButton(new JButton("Open"), layout, 1, 1, 1, 0, 1);
        makeButton(new JButton("Save"), layout, 1, 1, 1, 0, 2);
    }
    
    /* 
        makeButton() creates JFileChooser.
    */
    private void makeFileChooser(JFileChooser j) {
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        j.showOpenDialog(j);
    }
    
    /* 
        makeButton() creates buttons with specific attributes, also adds ActionListener
        to every button. 
    */
    void makeButton(JButton button, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx){
        GridBagConstraints c = new GridBagConstraints();
        
        button.setFont(new Font("monospaced", Font.BOLD, 16));
        button.setBackground(Color.white);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton pressed = (JButton)e.getSource();
                
                // If button called "Open" is pressed.
                if(pressed.getText().equals("Open")) {
                    makeFileChooser(new JFileChooser()); // FileChooser for movie-folder
                }
            }
        });

        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        //c.fill = GridBagConstraints.BOTH;
        gd.setConstraints(button, c);
        add(button);
    }
}
