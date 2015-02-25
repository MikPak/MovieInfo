/**
ParseUrl-Class
------------------------

ParseUrl-Class is made for establishing connection to a given url and for making 
queries to website and fetch the information. Also parsing it for later usage.


Authors: Mikko Pakkanen, Mikko Tella 
Date: 19.02.2015
**/

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseUrl {
    public ParseUrl() {

    }
    
    /*  Query(String url, List<String> queries) is for making queries to a specified
        URL and fetching query info. OMDb API gives you two choices for return data, XML
        and JSON. We are going for the XML. We want to parse this response data from the
        server and place it to ArrayList containing each query result as an object, in this
        case, a movie.
    */
    public List<MoviesIMDB> Query(String url, List<String> queries) {
        ArrayList<MoviesIMDB> responses = new <MoviesIMDB>ArrayList(); // We are gonna store successful queries here.
        
        DocumentBuilderFactory builderFactory =
        DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
   
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  
        }
        
        // For-each query.
        for(String query : queries) {
            try{
                query = URLEncoder.encode(query, "UTF-8"); // Encoding query for white-spaces.
                URL omdb = new URL("http://www.omdbapi.com/?t=" + query + "&r=xml"); // Execute query, parameter r for XML-response.
                BufferedReader in = new BufferedReader(new InputStreamReader(omdb.openStream())); // Reader for the stream.
                
                while(in.readLine() != null) {
                    try {
                        Document document = builder.parse(omdb.openStream()); // Build DOM-document.
                        Element rootElement = document.getDocumentElement(); // Root element of DOM.
                        NodeList nodes = rootElement.getChildNodes(); // Get nodes.

                        for(int i = 0; i < nodes.getLength(); i++) {
                            Node node = nodes.item(i);
                            MoviesIMDB movie = new MoviesIMDB(); // Creating object for each movie.

                            if(node instanceof Element) {
                                // A child element to process.
                                Element child = (Element) node;
                                
                                // Set parsed nodes as object's attributes.
                                movie.setMovieName(child.getAttribute("title")); // Movie title.
                                movie.setMovieYear(child.getAttribute("year")); // Movie year.
                                movie.setMovieRated(child.getAttribute("rated")); // Movie rated.
                                movie.setMovieReleased(child.getAttribute("released")); // Movie released.
                                movie.setMovieRuntime(child.getAttribute("runtime")); // Movie runtime.
                                movie.setMovieGenre(child.getAttribute("genre")); // Movie genre.
                                movie.setMovieDirector(child.getAttribute("director")); // Movie director.
                                movie.setMovieWriter(child.getAttribute("writer")); // Movie writer.
                                movie.setMovieActors(child.getAttribute("actors")); // Movie actors.
                                movie.setMoviePlot(child.getAttribute("plot")); // Movie plot.
                                movie.setMovieLanguage(child.getAttribute("language")); // Movie language.
                                movie.setMovieCountry(child.getAttribute("country")); // Movie country.
                                movie.setMovieAwards(child.getAttribute("awards")); // Movie awards.
                                movie.setMoviePoster(child.getAttribute("poster")); // Movie poster.
                                movie.setMovieMetascore(child.getAttribute("metascore")); // Movie metascore.
                                movie.setMovieIMDBRating(child.getAttribute("imdbRating")); // Movie IMDB-Rating.
                                movie.setMovieIMDBVotes(child.getAttribute("imdbvotes")); // Movie IMDB-Votes.
                                movie.setMovieIMDBid(child.getAttribute("imdbID")); // Movie IMDB-ID.
                                
                                // Add object to ArrayList.
                                responses.add(movie);
                            }
                        }   
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                System.out.println(e);
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
        return responses;
    }
}
