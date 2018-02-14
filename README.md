# rlstats
A simple little project in my spare time.

We are Rocket League enthusiasts, and we wanted to track our Seasonal Progress and matches with their corresponding data. Currently we do that
in a not-so-nice Google Spreadsheet.
So I thought up this idea of a little GUI with a database that checks the API for that every minute after I run it.

The core idea is : The API provides a whole lot of data, but not very specific ones. For example, there is basically no way to track 
goals / season , since goals is a player stat. 

Our idea to solve this would be running this programm before a match in the concrete queue, it gathers the specific players data, caches it,
and periodically (every 2-3 minutes would probably suffice to not stress the API too much) for new matches, compare data , and put the difference
into the data base as "a new match". And ultimately make it available on my own little website.

I thought this would be a nice project to actually do something useful.

Features :

- seperate threads for UI and API Request
- check input for SteamID or Name
- new Thread for multiple requests (up to 3 players concurrent via the BATCH API )
- periodically check for new matches
- implement queue selection (see Playlists.ENUM)
- view Stats of the player in a seperate window
- put your own API key in via a file or GUI
- save/load configs
- display data on website
- Initalize a new catabase / connect to an existing one (SQLite or mySQL is still open for debate)
- put data into the database (user selection??)
