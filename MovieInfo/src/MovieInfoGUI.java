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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.DefaultListModel;
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
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class MovieInfoGUI implements ActionListener, ItemListener {
    // Main frame
    private final JFrame frame = new JFrame("MovieInfo");
    // Labels
    private final JLabel labelMovieName = new JLabel("Title");
    private final JLabel labelMovieActors = new JLabel("Actors");
    private final JLabel labelMovieGenre = new JLabel("Genre");
    private final JLabel labelMovieRuntime = new JLabel("Runtime");
    private final JLabel labelMovieDirector = new JLabel("Director");
    private final JLabel picLabel = new JLabel(new ImageIcon("logo.png"));    
    // Text Areas for plot
    private final JTextArea textArea = new JTextArea(10, 30); // Rows, Columns
    // Text Area for actors
    private final JTextArea textActors = new JTextArea(4, 30);
    // Panels
    private ExamplePane pane = new ExamplePane();
    // List Models
    private final DefaultListModel listModel = new DefaultListModel();
    // Lists
    private JList list = new JList(listModel);
    // Scroll Panes
    private final JScrollPane leftListScollPane = new JScrollPane(list);
    // Dialogs
    private final JDialog dialog = new JDialog();
    
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
            
            // Movie title
            addLabel(labelMovieName, infoDisplayPane, 0);
            addLabel(labelMovieGenre, infoDisplayPane, 1);
            addLabel(labelMovieRuntime, infoDisplayPane, 2);
            addLabel(labelMovieDirector, infoDisplayPane, 3);
            
            // Movie plot            
            Dimension d = textArea.getPreferredSize();
            d.width = 270;
            textArea.setEditable(false);
            textArea.setPreferredSize(d);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);            
            add(textArea, gbc);      
                        
            // Movie actors
            add(labelMovieActors, gbc);
            Dimension o = textActors.getPreferredSize();
            o.width = 270;
            textActors.setEditable(false);
            textActors.setPreferredSize(o);
            textActors.setLineWrap(true);
            textActors.setWrapStyleWord(true);
            add(textActors, gbc); 
            //getFolderPathFromMemory();                       
            add(picLabel);
        }
                
        public void setTextMovieName(String text) {
            labelMovieName.setText(text);
        }
        
        public void setTextMovieRuntime(String text) {
            labelMovieRuntime.setText(text);
        }
        
        public void setTextMovieDirector(String text) {
            labelMovieDirector.setText(text);
        }
        
        public void setTextMovieGenre(String text) {
            labelMovieGenre.setText(text);
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
           // menu2 = new JMenu("View");
            //menuBar.add(menu2);
            
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
           // cbMenuItem = new JCheckBoxMenuItem("Plot");
            //cbMenuItem.addActionListener(this);
            //menu2.add(cbMenuItem);
            
            // View->Actors
           // cbMenuItem = new JCheckBoxMenuItem("Actors");
           // cbMenuItem.addActionListener(this);
            //menu2.add(cbMenuItem);         
                        
            return menuBar;
    }
          
    /*  
        FileChooser() creates JFileChooser and returns folder path selected by 
        user
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
    
    /* addLabel() creates JLabel*/
    private void addLabel(Component c, Container parent,int gridy) {
        GridBagConstraints labelConstraints = null;
        labelConstraints = new GridBagConstraints();

        labelConstraints.gridy = gridy;
        
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, labelConstraints);
        parent.add(c);
    }
    
    /* Initializes JList with objects */
    public void initializeJList(List<MoviesIMDB> movieList) {
        for(MoviesIMDB movie : movieList) {
            if(!movie.getMovieIMDBid().isEmpty()) {
                listModel.addElement(movie);
            }
        }
        list.addMouseListener(mouseListener);
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
                List<File> sub_folders; 
                List<String> movie_names; 

                File folderPath = FileChooser(new JFileChooser()); // FileChooser for movie-folder
                if(folderPath != null) {
                        FileNameParser parser = new FileNameParser();
                        System.out.println(folderPath);
                        
                        // Write file-object to file, to read it from memory later
                        try {
                            FileOutputStream fout = new FileOutputStream("path.dat");
                            ObjectOutputStream oos = new ObjectOutputStream(fout);
                            oos.writeObject(folderPath);
                            oos.close();
                            fout.close();
                        } catch (IOException ex) {
                                Logger.getLogger(MovieInfoGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sub_folders = parser.getFolders(folderPath); // Get subfolders of a given folder
                        movie_names = parser.parseMovieNames(sub_folders); // Parsed movie-folders
                        List<MoviesIMDB> parsed_responses = new ParseUrl().Query(movie_names); // Make a query to OMDb
                        initializeJList(parsed_responses);
                }
            }
            
            if ("About".equals(command)){
                JOptionPane.showMessageDialog(dialog, 
                        "MovieInfo is a simple movie-library tool for retrieving movie information from Internet Movie Database (IMDB.com) "
                                + "and showing this info in a neat and simple GUI.\n\n" +
                                "Mikko Pakkanen & Mikko Tella\n" +
                                "2015");
            }  
    }
    
    MouseListener mouseListener = new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
           MoviesIMDB movie = (MoviesIMDB)list.getSelectedValue();
           pane.setTextMovieName(movie.getMovieName() + " (" + movie.getMovieYear() + ")");
           pane.setTextMovieRuntime(movie.getMovieRuntime());
           pane.setTextMovieDirector(movie.getMovieDirector());
           pane.setTextMovieGenre(movie.getMovieGenre());
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
