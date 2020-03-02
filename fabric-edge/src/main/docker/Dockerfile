FROM frolvlad/alpine-oraclejre8:cleaned
ADD *.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]