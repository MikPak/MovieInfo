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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JDialog;

public class MovieInfoGUI implements ActionListener, ItemListener {
    
    // Main frame
    private JFrame frame = new JFrame("MovieInfo");
    // Layouts
    private GridBagLayout layout = new GridBagLayout();
    // Labels
    private JLabel labelMovieName = new JLabel("Title");
    // Text Areas
    private JTextArea textArea = new JTextArea(10, 30); // Rows, Columns
    // Text Area for actors and others
    private JTextArea textActors = new JTextArea(10, 30);
    // Panels
    private ExamplePane pane = new ExamplePane();
    // List Models
    private DefaultListModel listModel = new DefaultListModel();
    // Lists
    private JList list = new JList(listModel);
    // Scroll Panes
    private JScrollPane leftListScollPane = new JScrollPane(list);
    // Dialogs
    private JDialog dialog = new JDialog();
    
    public MovieInfoGUI() {
        frame.setLayout(new BorderLayout()); // Use BorderLayout
        frame.add(pane, BorderLayout.CENTER); // Center panel, contains movie related info.
        frame.add(createMenuBar(), BorderLayout.NORTH); // Menubar on the top of the window
        
        // Add scrollpane to the left
        Dimension d = list.getPreferredSize();
        d.width = 200;
        leftListScollPane.setPreferredSize(d);
        frame.add(leftListScollPane, BorderLayout.WEST);
        list.addMouseListener(mouseListener);
        
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public class ExamplePane extends JPanel {
        public ExamplePane() {
            //setLayout(layout);
            JPanel infoDisplayPane = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            
            add(infoDisplayPane, gbc);
            addLabel(labelMovieName, infoDisplayPane, 0);
            
            Dimension d = textArea.getPreferredSize();
            d.width = 270;
            textArea.setEditable(false);
            textArea.setPreferredSize(d);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            add(textArea, gbc);
            //addLabel(labelMoviePlot, infoDisplayPane, 1);
            
            //text area for actors
            Dimension o = textActors.getPreferredSize();
            o.width = 270;
            textActors.setEditable(false);
            textActors.setPreferredSize(o);
            textActors.setLineWrap(true);
            textActors.setWrapStyleWord(true);
            add(textActors, gbc);        
                      
                        
        }
        
        public void setTextMovieName(String text) {
            labelMovieName.setText(text);
        }
        
        public void setTextMoviePlot(String text) {
            textArea.setText(text);
        }
        public void setTextMovieActors(String text){
            textActors.setText(text);
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
            
            // View->Actors
            cbMenuItem = new JCheckBoxMenuItem("Actors");
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
    
    /* 
        addLabel() creates JLabel
    */
    private void addLabel(Component c, Container parent,int gridy) {
        GridBagConstraints labelConstraints = null;
        labelConstraints = new GridBagConstraints();

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
            
            /* If button called "Open" is pressed. (File->Open)
                - Gets subfolders of folder selected by the user
                - Parses the folder names
                - Makes query to OMDB and retrieve movie data
                - Add MoviesIMDB objects to JList
            */
            if("Open".equals(command)) {
                List<File> sub_folders = new ArrayList(); // We want to store complete subfolder paths in ArrayList-container.
                List<String> movie_names = new ArrayList(); // We want to store parsed folder names in ArrayList-container.

                File folderPath = FileChooser(new JFileChooser()); // FileChooser for movie-folder
                if(folderPath != null) {
                    FileNameParser parser = new FileNameParser();

                    sub_folders = parser.getFolders(folderPath); // Get subfolders of a given folder
                    movie_names = parser.parseMovieNames(sub_folders); // Parsed movie-folders
                    List<MoviesIMDB> parsed_responses = new ParseUrl().Query(movie_names); // Make a query to OMDb
                    
                    // For each result
                    for(MoviesIMDB movie : parsed_responses) {
                        // For debugging
                        System.out.print(movie.getMovieName() + " - ");
                        System.out.println(movie.getMovieYear());
                        System.out.println("------");
                        System.out.println(movie.getMoviePlot());
                        System.out.println();
                        
                        listModel.addElement(movie);
                    }
                    list.addMouseListener(mouseListener);
                }
            }
            
            if ("About".equals(command)){
                JOptionPane.showMessageDialog(dialog, 
                        "MovieInfo is a simple movie-library tool for retrieving movie information from Internet Movie Database (IMDB.com) "
                                + "and showing this info in a neat and simple GUI.");
            }  
    }
    
    MouseListener mouseListener = new MouseAdapter() {
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
           MoviesIMDB movie = (MoviesIMDB)list.getSelectedValue();
           pane.setTextMovieName(movie.getMovieName() + " (" + movie.getMovieYear() + ")");
           pane.setTextMoviePlot(movie.getMoviePlot());
           pane.setTextMovieActors(movie.getMovieActors());
         }
    }
};
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println(e);
    }
}
