FROM frolvlad/alpine-oraclejre8:cleaned
ADD *.jar app.jar
VOLUME /home/data/config
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar","--spring.profiles.active=test"]