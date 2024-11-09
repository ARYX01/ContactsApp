# ContactsApp
server side backend and local desktop app for managing contacts

---

### Backend: 
1) Import **schema_database.sql** in your MySQL server



2) resources/**application.properties**

Database name ***turing_rubrica***

if MySQL server is not running locally change datasource url

    spring.datasource.url=

Change with valid MySQL server credentials

    spring.datasource.username=
    spring.datasource.password=

If jwt secret token is no longer valid, generate new one with *JWTSecretGeneratorTest* class and replace in properties

    jwt.secret=



### Desktop App

resources/**app.config**
If backend server is not running locally change the base endpoint "http://localhost"

    backend.apiPath=http://localhost:8080/api
    backend.loginPath=http://localhost:8080/user/login


App default **user** for login:

Username = Turing

Password = rubrica123 
