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
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    }
    
    /* 
        FileChooser() creates JFileChooser and returns folder path selected by user
    */
    private File FileChooser(JFileChooser j) {
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = j.showOpenDialog(j);
        
        // If user chooses folder
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File folderPath = j.getSelectedFile();
            return folderPath;
        } else {
            return null;
        }
    }
    
    /* 
        makeButton() creates buttons with specific attributes, also adds ActionListener
        to every button. 
    */
    void makeButton(JButton button, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx){
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        //c.fill = GridBagConstraints.BOTH;
        
        button.setFont(new Font("monospaced", Font.BOLD, 16));
        button.setBackground(Color.white);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton pressed = (JButton)e.getSource();
                
                // If button called "Open" is pressed.
                if(pressed.getText().equals("Open")) {
                    List<String> movie_names = new ArrayList<String>(); // We want to store parsed folder names in ArrayList-container, too.
                    List<File> sub_folders = new ArrayList<File>(); // We want to store complete subfolder paths in ArrayList-container.
                    
                    File folderPath = FileChooser(new JFileChooser()); // FileChooser for movie-folder
                    if(folderPath != null) {
                        FileNameParser parser = new FileNameParser();
                    
                        sub_folders = parser.getFolders(folderPath); // Get subfolders of given folder
                        movie_names = parser.parseMovieNames(sub_folders); // Parsed movie-folders
                    
                        List<MoviesIMDB> parsed_responses = new ParseUrl().Query(movie_names);
                    
                        for(MoviesIMDB movie : parsed_responses) {
                            System.out.print(movie.getMovieName() + " - ");
                            System.out.println(movie.getMovieYear());
                            System.out.println("------");
                            System.out.println(movie.getMoviePlot());
                            System.out.println();
                        }
                    }
                }
            }
        });
        
        gd.setConstraints(button, c);
        add(button);
    }
}
