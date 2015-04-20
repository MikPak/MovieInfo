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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameParser {
    public FileNameParser() {

    }

    /*  getFolders(String directoryName) lists subfolders of a given folder, 
        returns ArrayList containing absolute path for each subfolder.*/
    public List<File> getFolders(File directoryPath) {
        List<File> resultList = new ArrayList<File>(); // Using ArrayList to store subfolders
        
        // List all files from folder
        File[] files = directoryPath.listFiles();
       
        /*  FOR LATER USE! Filter results by the file extension 
            (ex. .avi, .mp4 etc).
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
    
    // Returns file from data-file
    public File getFolderPathFromMemory() throws IOException, ClassNotFoundException {
        File path2 = new File("default");

        try {
           FileInputStream fis = new FileInputStream("path.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            path2 = (File)ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileNameParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path2;
    }
    
    /*  parseMovieNames(List <File>folders) parses given ArrayList containing 
        absolute folder paths. Movie folder names can sometimes be complicated, 
        so we want to parse them before making any queries with them. In many cases
        movie-folder names contain special characters we want to get rid of. Folder
        names usually contain 4 number digit as release year of the movie, so we 
        are gonna use that in our advance.    */
    public List<String> parseMovieNames(List <File>folders) {
        List<String> movieNames = new <String>ArrayList();
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE); // Regex for special characters check
        
        /* Iterating through folders-list, checking if folder name
           contains special characters or release year and parses them to most 
           simple output.  */
        for(File folder : folders) {
            String name = folder.getName();
            Matcher m = p.matcher(name);
            boolean b = m.find();
            
            // Parsing string if name contains special characters
            if(b) {
                //System.out.println("Folder: \" " + name + "\" contains special characters.");
                String parsed_name = name.replaceFirst( "\\d{4}.*", "" ).
                        replaceAll( "[\\p{P}\\p{S}&&[^&']]+", " " );
                //System.out.println("Parsed: " + result + " > " + parsed_name);
                
                // Add name to ArrayList
                movieNames.add(parsed_name);
            } else { // No special characters, check for year.
                String parsed_name = name.replaceFirst( "\\d{4}.*", "" );
                movieNames.add(parsed_name);
            }
        }
        return movieNames;
    }
}
