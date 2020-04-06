# jersey restful server examples 
## Build war and deploy it as a REST-service onto tomcat :)

### Steps:

##### 1. Download a fresh [Tomcat 8 distribution](https://tomcat.apache.org/download-80.cgi)
##### 2. Clone the repository to your local machine
##### 3. Start Tomcat ( default at localhost,  port 8080 )
##### 3a. check Maven + JVM-version ( Java8 is a MUST!)
```
mvn -version
Apache Maven 3.6.0
Maven home: /usr/share/maven
Java version: 1.8.0_242, vendor: Private Build, runtime: /usr/lib/jvm/java-8-openjdk-amd64/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.15.0-91-generic", arch: "amd64", family: "unix"
```
##### and redefine/revert/rollback/downgrade it if neccessary , for ex.

```
sudo update-alternatives --config java

```

##### 4. Run mvn -X help:active-profiles tomcat:redeploy
##### 5. Tomcat will automatically deploy the war
##### 6. Open [SERVER-API][] in your browser
##### 7. look into [REST-CLIENT][] for "How to using it"


[SERVER-API]: http://localhost:8080/jersey-restful-server-example/
[REST-CLIENT]: https://github.com/edcompass/jersey-restful-client-examples
