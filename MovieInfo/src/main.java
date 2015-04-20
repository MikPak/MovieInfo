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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class main {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        long startTime = System.currentTimeMillis(); // For measuring program runtime
        FileNameParser parser = new FileNameParser();
        File FolderPathMemory = parser.getFolderPathFromMemory();
        
        // Check if folder exists in memory
        if(FolderPathMemory != null) {
            List<File> sub_folders = new ArrayList();
            List<String> movie_names = new ArrayList();
            
            sub_folders = parser.getFolders(parser.getFolderPathFromMemory());
            movie_names = parser.parseMovieNames(sub_folders);
        } else {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                    }

                    MovieInfoGUI window = new MovieInfoGUI();
                }
            });
        }

        // Program runtime measure end.
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Runtime: " + elapsedTime + "ms");
    }
}
