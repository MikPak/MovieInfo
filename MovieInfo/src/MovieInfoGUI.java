/**
MovieInfoGUI-class
------------------------

MovieInfoGUI-Class is for creating a graphical user interface for our app.

Authors: Mikko Pakkanen, Mikko Tella 
Date: 04.03.2015
**/

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MovieInfoGUI implements ActionListener, ItemListener {

    private JFrame frame = new JFrame("MovieInfo");
    private GridBagLayout layout = new GridBagLayout();
    private JList lista = new JList();
    private JLabel c = new JLabel("Movie Name");
    private JTextArea textArea = new JTextArea(10, 30); // Rows, Columns
    private ExamplePane pane = new ExamplePane();
    
    public MovieInfoGUI() {
        //setLayout(layout); // Set layout as GridBagLayout
        frame.setLayout(new BorderLayout()); // Use BorderLayout
        frame.add(new JScrollPane(lista), BorderLayout.WEST);
        frame.add(pane, BorderLayout.CENTER);
        frame.add(createMenuBar(), BorderLayout.NORTH);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(700,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public class ExamplePane extends JPanel {
        public ExamplePane() {
            //setLayout(layout);
            JPanel infoDisplayPane = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            
            //gbc.gridx = 0;
            //gbc.gridy = 0;
            //gbc.fill = GridBagConstraints.RELATIVE;
            // makeButton (display text, Layout type, cell span, height, width, row number, column number)
            //buttonPane.add(makeButton(new JButton("Open"), layout, 1, 1, 1, 0, 1), gbc);
            
            //gbc.gridx = 0;
            //gbc.gridy = 0;
            //gbc.insets = new Insets(10, 10, 10, 80);
            add(infoDisplayPane, gbc);
            
            //gbc.insets = new Insets(150, 100, 150, 100);
            //gbc.gridx++;
            //gbc.gridy = 1;
            //gbc.fill = GridBagConstraints.BOTH;
            addLabel(c, infoDisplayPane, 0);
            addLabel("Plot", infoDisplayPane, 1);
            add(new JScrollPane(textArea), gbc);
        }
        
        public void setTextMovieName(String text) {
            c.setText(text);
        }
        
        public void setTextMoviePlot(String text) {
            textArea.setText(text);
        }
    }
    
    private JMenuBar createMenuBar() {
            JMenuBar menuBar;
            JMenu menu, menu2, menu3;
            JMenuItem menuItem;
            JCheckBoxMenuItem cbMenuItem;

            // Create the menu bar
            menuBar = new JMenuBar();

            // File-menu
            menu = new JMenu("File");
            menuBar.add(menu);
            
            // View-menu
            menu2 = new JMenu("View");
            menuBar.add(menu2);
            
            // Info-menu
            menu3 = new JMenu("Info");
            menuBar.add(menu3);
            
            // Info->About
            menuItem = new JMenuItem("About");
            menuItem.addActionListener(this);
            menu3.add(menuItem);

            // File->Open
            menuItem = new JMenuItem("Open");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            
            // File->Quit
            menuItem = new JMenuItem("Quit");
            menuItem.addActionListener(this);
            menu.add(menuItem);
            
            // View->Plot
            cbMenuItem = new JCheckBoxMenuItem("Plot");
            cbMenuItem.addActionListener(this);
            menu2.add(cbMenuItem);
            
            return menuBar;
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
    private JButton makeButton(JButton button, GridBagLayout gd, int gridwidth, int weightx, int weighty, int gridy, int gridx){
        GridBagConstraints c = new GridBagConstraints();
        
        c.gridwidth = gridwidth;
        c.weightx = weightx;
        c.weighty = weighty;
        c.gridx = gridx;
        c.gridy = gridy;
        //c.fill = GridBagConstraints.BOTH;
        
        button.setFont(new Font("monospaced", Font.PLAIN, 14));
        button.addActionListener(this);
        
        gd.setConstraints(button, c);
        frame.add(button);
        
        return button;
    }
    
    private void addLabel(Component c, Container parent,int gridy) {
        GridBagConstraints labelConstraints = null;
        labelConstraints = new GridBagConstraints();
        
        // Määrittelyt label-komponentille
        labelConstraints.weightx = 0.0; // Label käyttää mahdollisimman vähän tilaa
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        //labelConstraints.gridwidth = 1;
        labelConstraints.anchor = GridBagConstraints.WEST;
        labelConstraints.insets = new Insets(0, 0, 5, 185);
        labelConstraints.gridy = gridy;
        
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, labelConstraints);
        parent.add(c);
    }
    
    private JLabel addLabel(String s, Container parent, int gridy) {
        JLabel c = new JLabel(s);
        addLabel(c, parent, gridy);
        return c;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            // If button called "Open" is pressed.
            if("Open".equals(command)) {
                List<File> sub_folders = new ArrayList(); // We want to store complete subfolder paths in ArrayList-container.
                List<String> movie_names = new ArrayList(); // We want to store parsed folder names in ArrayList-container.

                File folderPath = FileChooser(new JFileChooser()); // FileChooser for movie-folder
                if(folderPath != null) {
                    FileNameParser parser = new FileNameParser();

                    sub_folders = parser.getFolders(folderPath); // Get subfolders of a given folder
                    movie_names = parser.parseMovieNames(sub_folders); // Parsed movie-folders

                    List<MoviesIMDB> parsed_responses = new ParseUrl().Query(movie_names); // Make a query to OMDb
                    JList lista = new JList();
                    // For each result
                    for(MoviesIMDB movie : parsed_responses) {
                        System.out.print(movie.getMovieName() + " - ");
                        System.out.println(movie.getMovieYear());
                        System.out.println("------");
                        System.out.println(movie.getMoviePlot());
                        System.out.println();
                        pane.setTextMovieName(movie.getMovieName());
                        pane.setTextMoviePlot(movie.getMoviePlot());
                    }
                }
            }
    }    
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println(e);
    }
}
