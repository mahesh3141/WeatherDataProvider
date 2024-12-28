Weather spring boot application for accessing the weather information on basis of postal code  
<br> Swagger Api :- http://localhost:8081/swagger-ui/index.html  
<br>Here I am using H2 database to stored the data. Datase file name :- weatherdb.mv.db  
<br>To access the table visit http://localhost:8081/h2-ui/  
<br>For login to database username:- admin and password:- admin@123  
<br>There are two tables on the database  
<br>First table is for Users (It will stored the user information such as username along with active or deactive status)  
<br>Second table is for WEATHER_REPORT (It will stored the weather information along with username)
<br>
<br> For User  
<br> To create a user use the api:-  http://localhost:8081/users?name=John Doe (its a POST api)    
<br> To get all user as per the status user the api :- http://localhost:8081/users/checkUser/active  (its a GET api)  
<br> To activate the user use the api:- http://localhost:8081/users/activate/8 (It a PUT api pass user id over it)  
<br> To deactivate the user use the api:- http://localhost:8081/users/deactivate/8 (It a PUT api pass user id over it)  
<br>
<br>For Weather  
<br>To fetch the weather data use api:- http://localhost:8081/weather/fetch?postalCode=20042&userName=John Doe (First it the for user is active or not then it will stored the data on database)  
<br>To get the history for weather from database via postal code use the api :- http://localhost:8081/weather/historyByPostalCode/41702   
<br>To get the history for weather from database via username code use the api :- http://localhost:8081/weather/historyByUser/John Doe  
<br>
<br>Also added the postman collection search the file WeatherReport.postman_collection.json
