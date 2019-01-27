# neueda
Link for Spring boot docker image:
docker pull abdhul/tinytrlspringboot


Steps:
•	Clone from github.
•	There will be 2 folders and some files.
•	Tinyurlspringboot is a spring boot application which consumes rest calls.
•	Angular6-http-client is an angular application.
•	Open the folder in command prompt. Mvn clean install inside the spring boot application.
•	Setup postgresql.
o	Username: postgres
o	Password: 661745
•	Create database with name tinyurlDB. Create a table with name url. Columns urlid(integer), shorturl (character varying 20 characters) , longurl(character varying 2000 characters), logdate (timestamp with time zone).
CREATE TABLE url(
   urlid Integer,
   shorturl char(50),
   logurl char(2000),
   logdate TIMESTAMPZ
);

•	Go to the angular application in command prompt and run ng serve.
•	Go to the url link http://localhost:4200/ .You will see the below page.
•	Start the spring boot application as a Java application. 
•	 
•	Put the long url in the text box and you will see a short url generated.
•	 
•	Copy the short url to another tab in browser. You will see it re-directed to another tab in browser to the long url.



ANALYTICS
For analytics, Elastic stack was used. The data from Postgresql was indexed to ES using Logstash. Visualization and Dashboard was created using Kibana.
DASHBOARD:
 


Top 10 Cities using the application.
 







A choropleth representing the count of request in each country. 
 

A timeline of number of request every week since the start of the application.
 

A pie chart representing the top domains whose long url has been shortened in the site.
 
