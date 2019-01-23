# jersey restful server examples 
Build war and deploy it as a REST-service onto tomcat :)

Steps:

1. Download a fresh [Tomcat 8 distribution](https://tomcat.apache.org/download-80.cgi)
2. Clone the repository to your local machine
3. Start Tomcat ( default at localhost,  port 8080 )
4. Run mvn -X help:active-profiles tomcat:redeploy
5. Tomcat will automatically deploy the war
6. Open [http://localhost:8080/jersey-restful-client-examples/](http://localhost:8080/jersey-restful-client-examples/) in your browser
7) look into https://github.com/edcompass/jersey-restful-client-examples for "How to using it"
