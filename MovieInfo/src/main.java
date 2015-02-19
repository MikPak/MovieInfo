/**
MovieInfo - Simple movie-library tool
------------------------
MovieInfo is a simple movie-library tool for retrieving movie information from
Internet Movie Database (IMDB.com) and showing this info in neat and simple GUI.

Complete features:

Partially complete features:
- Retrieve movies from a given folder (MPa)
- Parse folder names for making a query to OMDb. (MPa)

TODO:
- Method for connecting to OMDb and passing folder name as movie name.
- Parsing OMDb response & showing it in the client.
- Make a first GUI.
- Make interactive GUI so it works with OMDb

Authors: Mikko Pakkanen, Mikko Tella 
Project started: 19.02.2015
**/

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String args[]) {
        String folderpath = "E:/W/Elokuvat/"; // Set path for a folder containing movies.
        FileNameParser parser = new FileNameParser();
        List<File> sub_folders = new ArrayList<File>(); // We want to store complete subfolder paths in ArrayList-container.
        List<String> movie_names = new ArrayList<String>(); // We want to store parsed folder names in ArrayList-container, too.
        
        // Get subfolders
        sub_folders = parser.getFolders(folderpath);
        
        // Print absolute subfolder paths from a given path
        // System.out.println(sub_folders);
        
        // Print movie folder name only
        movie_names = parser.parseMovieNames(sub_folders);
        
        // Iterating through ArrayList
        int i = 1;
        for(String movie : movie_names) {
            System.out.println("#"+ i + " " + movie);
            i++;
        }
        
    }
}
