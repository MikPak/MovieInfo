#MovieInfo - Simple movie-library tool
------------------------
MovieInfo is a very simple movie-library tool for retrieving movie information from
Internet Movie Database (IMDB.com) and showing it in neat and simple GUI.

MovieInfo uses OMDB-API ( http://www.omdbapi.com/ ) for querying IMDB.

Complete features:
- Parse folder names and make queries to OMDb
- Method for connecting to OMDb and passing folder name as movie name
- Parsing OMDb response & showing it in the client

TODO:
- Make GUI better, its very poor at the moment.
- Search movies by extension .avi, .mp4 etc. At the moment MovieInfo only parses subfolders from user defined path.
- Store fetched movie information in .xml or database and load info from there,
  instead of making query to OMDb on every startup.

Authors: Mikko Pakkanen, Mikko Tella 
Project started: 19.02.2015
