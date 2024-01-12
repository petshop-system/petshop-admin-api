# https://spring.io/guides/gs/spring-boot-docker/
FROM openjdk:17-jdk-slim
VOLUME /tmp

RUN echo "America/Sao_Paulo" > /etc/timezone
RUN export TZ=America/Sao_Paulo

ARG DEPENDENCY_CLASS=target/dependency
COPY ${DEPENDENCY_CLASS}/BOOT-INF/lib        /app/lib
COPY ${DEPENDENCY_CLASS}/META-INF            /app/META-INF
COPY ${DEPENDENCY_CLASS}/BOOT-INF/classes    /app

RUN mkdir /tmp/logs

CMD ["java","-Dspring.profiles.active=${SPRING_PROFILE}","-cp","app:app/lib/*","com.petshopadmin.PetshopAdminApiApplication"]