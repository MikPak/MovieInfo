/**
FileNameParser-Class
------------------------

FileNameParser-Class is made for parsing and returning subdirectory names from 
a given folder. One could then use this list to retrieve information from
different kind of queries to retrieve data from a website, for example.


Authors: Mikko Pakkanen, Mikko Tella 
Date: 19.02.2015
**/

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileNameParser {
    public FileNameParser() {

    }

    /* List subfolders of a given folder, return ArrayList containing absolute 
    path for each folder.*/
    public static List<File> getFolders(String directoryName) {
        File directory = new File(directoryName); 
        List<File> resultList = new ArrayList<File>(); // Using ArrayList to store subfolders
        
        // List all files from folder
        File[] files = directory.listFiles();
        
        /* FOR LATER USE! Filter results by the file extension (ex. .avi, .mp4 etc).
        File[] files = directory.listFiles(new FilenameFilter() {
            public boolean accept(File directory, String name) {
                return name.toLowerCase().endsWith(".avi");
            }
        }); */
        
        // We only want folder names, so we store them to resultList
        for(File f : files) {
            if (!f.isFile()) {
                resultList.add(f);
            }
        }
        
        return resultList;
    }
    
    public static List<String> parseMovieNames(List <File>file) {
        /*
         for(int i = 0; file.iterator().hasNext(); i++) {
             System.out.println(file.get(i));
         } */
         
         for(File s : file) {
             System.out.println(s.getName());
         }
         
         return null;
    }
    
}
