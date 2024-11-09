# ContactsApp
server side backend and local desktop app for managing contacts

---

### Backend: 
1) Import **schema_database.sql** in your MySQL server



2) **application.properties**

Database name ***turing_rubrica***

if MySQL server is not running locally change datasource url

    spring.datasource.url=

Change with valid MySQL server credentials

    spring.datasource.username=
    spring.datasource.password=

If jwt secret token is no longer valid, generate new one with *JWTSecretGeneratorTest* class and replace in properties

    jwt.secret=



### App default user for login:
Username = Turing

Password = rubrica123 