Weather spring boot application for accessing the weather information on basis of postal code.
Here I am using H2 database to stored the data.
To access the table visit http://localhost:8081/h2-ui/
For login to database username:- admin and password:- admin@123
There are two tables on the database 
First table is for Users (It will stored the user information such as username along with active or deactive status)
Second table is for WEATHER_REPORT (It will stored the weather information along with username)

For User
To create a user use the api:-  http://localhost:8081/users?name=John Doe (its a POST api)
To get all user as per the status user the api :- http://localhost:8081/users/checkUser/active  (its a GET api)
To activate the user use the api:- http://localhost:8081/users/activate/8 (It a PUT api pass user id over it)
To deactivate the user use the api:- http://localhost:8081/users/deactivate/8 (It a PUT api pass user id over it)

For Weather
To fetch the weather data use api:- http://localhost:8081/weather/fetch?postalCode=20042&userName=John Doe (First it the for user is active or not then it will stored the data on database)
To get the history for weather from database via postal code use the api :- http://localhost:8081/weather/historyByPostalCode/41702 
To get the history for weather from database via username code use the api :- http://localhost:8081/weather/historyByUser/John Doe
