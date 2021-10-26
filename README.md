# hellospring: Demo of junit , with spring REST microservice
   . use org.springframework.boot, so the mainClass is SpringBootApplication and not need to be specified in pom.xml
   . the junit 4 MyStackTest class needs be changed to @SpringBootTest and using org.junit.jupiter.api.*. 
     otherwise, the tests will not be detected by maven.

   . if want to execute mainClass other than the SpringBootApplication, need to specify mainClass in pom.xml 
         <plugin>
             <groupId>org.springframework.boot</groupId>
             <artifactld>spring-boot-maven-plugin</artifactld>
             <version>2.2.1.RELEASE</version>
             <configuration>
                <mainClass>com.unistar.app3.mainapp</mainClass>
             </configuration>
         </plugin>

PS C:\helloSpring> mvn clean package -DskipTests

PS C:\helloSpring> mvn test
   Tests run: 14, Failures: 0, Errors: 0, Skipped: 0

PS C:\helloSpring> mvn -Dtest=BaseControllerTest test
   [INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.065 s - in com.unistar.app3.BaseControllerTest

PS C:\helloSpring> mvn -Dtest=BaseControllerTest#sendMessageTest test
   18:14:18.794 [main] INFO c.u.app3.controller.BaseController - sendMessageJson: UserMessage [name=testName, epochSecond=0, 
   epochInUTC=2021-05-08T22:14:18Z]
   [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.651 s - in com.unistar.app3.BaseControllerTest

PS C:\helloSpring> mvn -Dtest=MyStackTest test
   Tests run: 7, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 4.45 s - in com.unistar.app3.MyStackTest

PS C:\helloSpring> mvn clean package
   ; Running com.unistar.app3.BaseControllerIntegrationTest 
   ; Running com.unistar.app3.BaseControllerTest
   ; Running com.unistar.app3.MySpringAppTests
   ; Running com.unistar.app3.MyStackTest
   
   Tests run: 14, Failures: 0, Errors: 0, Skipped: 0

; execute mainClass other than the mainClass in pom.xml from command
PS C:\helloSpring> java -cp target/hellospring-0.0.1-SNAPSHOT.jar "-Dloader.main=com.unistar.app3.mainapp" 
org.springframework.boot.loader.PropertiesLauncher
   Hello World!
   pushed : [1, 2, 3]
   stack.size() : 3
   3

PS C:\helloSpring> java -cp target/hellospring-0.0.1-SNAPSHOT.jar "-Dloader.main=com.unistar.app3.MySpringApp" 
org.springframework.boot.loader.PropertiesLauncher

; execute SpringBootApplication class
PS C:\helloSpring> java -jar target/hellospring-0.0.1-SNAPSHOT.jar --server.port=8080 
  ;Ctrl + C to stop the Tomcat webserver

## REST API
C:\>curl "http://localhost:8080/hello"
welcome to helloSpring api...
C:\>curl "http://localhost: 8080/ping"
welcome to helloSpring api...

C:\>curl "http://localhost: 8080/greeting"
("name":"my kid","epochSecond":0,"epochInUTC":"2021-04-17T16:52:02Z") 
C:\>curl "http://localhost:8080/greeting?name=Joe"
("name":"Joe","epochSecond":0,"epochInUTC":"2021-04-17T16:52:34Z")
C:\>curl "http://localhost: 8080/greetingl"
("name":"my kid","epochSecond":0,"epochInUTC":"2021-04-17T16:54:07Z") 
C:\>curl "http://localhost: 8080/greetingl/Joe" -v
("name":"Joe","epochSecond":0,"epochInUTC":"2021-04-17T16:53:02Z")

C:\>curl -X POST -H "Content-Type:application/json" -d "{\"name\":\"Joe\",\"epochSecond\":0,\"epochInUTC\":\"2021-04-17T16:00Z\"}" "http://localhost: 8080/sendmessage"
UserMessage (name=doe, epochSecond=0, epochInUTC=2021-04-17T16:00Z]

C:\>curl "http://localhost:8080/greetingl/Joe" -v

C:\>curl -X POST -H "Content-Type:application/json" -d "{\"name\":\"Joe\",\"epochSecond\":0,\"epochInUTC\":\"2021-04-17T16:00Z\"}" 
"http://localhost:8080/sendmessage" -v

## Docker command
docker builder -t zmg/hellospring:tag-1.0.0 -f Dockerfile .
docker run -p 9000:8080 --name hellospring -d zmg/hellospring:tag-1.0.0

docker-compose -f docker-compose.xml build 
docker-compose -f docker-compose.xml up -d 
docker-compose -f docker-compose.xml down
