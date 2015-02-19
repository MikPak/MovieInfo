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
- Store fetched movie information in .xml or database and load info there.


Authors: Mikko Pakkanen, Mikko Tella 
Project started: 19.02.2015
**/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String args[]) {
        long startTime = System.currentTimeMillis(); // For measuring program runtime
        String folderpath = "E:/W/testi/"; // Set path for a folder containing movies.
        List<File> sub_folders = new ArrayList<File>(); // We want to store complete subfolder paths in ArrayList-container.
        List<String> movie_names = new ArrayList<String>(); // We want to store parsed folder names in ArrayList-container, too.
        
        FileNameParser parser = new FileNameParser();
        ParseUrl omdb = new ParseUrl();
        
        // Get subfolders
        sub_folders = parser.getFolders(folderpath);
        
        // Print absolute subfolder paths from a given path
        // System.out.println(sub_folders);
        
        // Print movie folder name only
        movie_names = parser.parseMovieNames(sub_folders);
        
        /*// Iterating through ArrayList
        int i = 1;
        for(String movie : movie_names) {
            System.out.println("#"+ i + " " + movie);
            i++;}*/
        
        // Make query to OMDb

            omdb.Query(folderpath, movie_names);

        
        // Program runtime measure end.
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Runtime: " + elapsedTime + "ms");
    }
}
