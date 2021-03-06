/**
MovieInfo - Simple movie-library tool
------------------------
MovieInfo is a simple movie-library tool for retrieving movie information from
Internet Movie Database (IMDB.com) and showing this info in neat and simple GUI.

Complete features:
- Retrieve movies from a given folder (MPa)
- Parse folder names for making a query to OMDb. (MPa)
- Method for connecting to OMDb and passing folder name as movie name. (MPa)
- Parsing OMDb response & showing it in the client. (MPa)

Partially complete features:
- Make a first GUI.

TODO:
- Make interactive GUI so it works with OMDb
- Store fetched movie information in .xml or database and load info from there,
  instead of making query to OMDb every time.


Authors: Mikko Pakkanen, Mikko Tella 
Project started: 19.02.2015
**/

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class main {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        FileNameParser parser = new FileNameParser();
        File FolderPathMemory = parser.getFolderPathFromMemory();
        
        /* 
            Check if path exists and initialize JList with movie-data. 
            If not, create plain GUI.
        */
        if(FolderPathMemory != null) {
            List<File> sub_folders;
            List<String> movie_names;
            
            sub_folders = parser.getFolders(parser.getFolderPathFromMemory());
            movie_names = parser.parseMovieNames(sub_folders);
            List<MoviesIMDB> parsed_responses = new ParseUrl().Query(movie_names); // Make a query to OMDb
            
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                MovieInfoGUI window = new MovieInfoGUI();
                window.initializeJList(parsed_responses);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            }
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    }

                    new MovieInfoGUI();
                }
            });
        }
    }
}
