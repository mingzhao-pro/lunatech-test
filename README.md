# lunatech-test
This simple application is for technique test only.

Backend is realised with SpringBoot and MongoDB.
Frontend is realised with AngularJS1.x and Bootstreap.
Developement method: TDD

Functionnality: 
/query: return a list of airports according to country name or code and the later ones are case-insensitive and partial matching. runways information could be found in each airport.

/report: return a list of top 10 countries which contain the most airports and a list of countries which contains the least airports. Runways type could be found in each country. This request retrives the top 10 most common runway identifications, too.


