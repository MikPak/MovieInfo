/**
ParseUrl-Class
------------------------

ParseUrl-Class is made for establishing connection to given url, make queries to
website and fetch information.


Authors: Mikko Pakkanen, Mikko Tella 
Date: 19.02.2015
**/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ParseUrl {
    public ParseUrl() {

    }
    
    /*  Query(String url, List<String> queries) is for making queries to a specified
        URL and fetching query info. Fetched info will be stored to an ArrayList.
    */
    public String Query(String url, List<String> queries) {
        for(String query : queries) {
            try{
                URL omdb = new URL("http://www.omdbapi.com/?t=" + query);
                BufferedReader in = new BufferedReader(new InputStreamReader(omdb.openStream()));
                
                String inputLine;
                while((inputLine = in.readLine()) != null){
                    System.out.println(inputLine);
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        
        //URL omdb = new URL("http://www.omdbapi.com/?t=");
        
        return "asd";
    }
}
