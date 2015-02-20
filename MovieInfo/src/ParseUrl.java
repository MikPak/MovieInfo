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
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseUrl {
    public ParseUrl() {

    }
    
    /*  Query(String url, List<String> queries) is for making queries to a specified
        URL and fetching query info. Fetched info will be stored in an ArrayList.
        OMDb gives you two choises for the response, JSON or XML. We are going
        to use XML for now.
    */
    public List<String> Query(String url, List<String> queries) {
        ArrayList<String> parsed_queries = new <String>ArrayList(); // We are gonna store parsed responses here.
        
        for(String query : queries) {
            try{
                query = URLEncoder.encode(query, "UTF-8"); // Encoding query for spaces.
                URL omdb = new URL("http://www.omdbapi.com/?t=" + query + "&r=xml");
                BufferedReader in = new BufferedReader(new InputStreamReader(omdb.openStream()));
                
                String inputLine;
                while((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
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
        return parsed_queries;
    }
    /*
    public void getXml(){
        try {
            // obtain and configure a SAX based parser
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            // obtain object for SAX parser
            SAXParser saxParser = saxParserFactory.newSAXParser();

            // default handler for SAX handler class
            // all three methods are written in handler's body
            DefaultHandler defaultHandler = new DefaultHandler(){

                String firstNameTag="close";
                String lastNameTag="close";
                String emailTag="close";
                String phoneTag="close";

             // this method is called every time the parser gets an open tag '<'
             // identifies which tag is being open at time by assigning an open flag
            public void startElement(String uri, String localName, String qName,
               org.xml.sax.Attributes attributes) throws SAXException {

                if (qName.equalsIgnoreCase("FIRSTNAME")) {
                    firstNameTag = "open";
                }
                if (qName.equalsIgnoreCase("LASTNAME")) {
                    lastNameTag = "open";
                }
                if (qName.equalsIgnoreCase("EMAIL")) {
                    emailTag = "open";
                }
                if (qName.equalsIgnoreCase("PHONE")) {
                    phoneTag = "open";
                }
             }

            // prints data stored in between '<' and '>' tags
            public void characters(char ch[], int start, int length)
                throws SAXException {
                
                if (firstNameTag.equals("open")) {
                    System.out.println("First Name : " + new String(ch, start, length));
                }
                if (lastNameTag.equals("open")) {
                    System.out.println("Last Name : " + new String(ch, start, length));
                }
                if (emailTag.equals("open")) {
                    System.out.println("Email : " + new String(ch, start, length));
                }
                if (phoneTag.equals("open")) {
                    System.out.println("Phone : " + new String(ch, start, length));
                }
            }

            // calls by the parser whenever '>' end tag is found in xml 
            // makes tags flag to 'close'
            public void endElement(String uri, String localName, String qName)
                throws SAXException {

                if (qName.equalsIgnoreCase("firstName")) {
                    firstNameTag = "close";
                }
                if (qName.equalsIgnoreCase("lastName")) {
                    lastNameTag = "close";
                }
                if (qName.equalsIgnoreCase("email")) {
                    emailTag = "close";
                }
                if (qName.equalsIgnoreCase("phone")) {
                    phoneTag = "close";
                }
            }
            };

            // parse the XML specified in the given path and uses supplied
            // handler to parse the document
            // this calls startElement(), endElement() and character() methods
            // accordingly
            saxParser.parse("xml/testi.xml", defaultHandler);
        } catch (Exception e) {
            e.printStackTrace();
            }
    } */
}


