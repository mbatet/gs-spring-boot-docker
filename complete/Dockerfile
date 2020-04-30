#FROM openjdk:8-jdk-alpine
# ALPINE
# Alpine Linux. This is the distribution that Docker uses by default to build its packages. Alpine wasn’t designed specifically for hosting containers, but its small footprint and focus on security make it a good fit.
FROM azul/zulu-openjdk-alpine:13 as jdk
# UBUNTU
#FROM azul/zulu-openjdk:13 as jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
#Copiar el hello.jar i totes les dependencies, i a veure si així no necessitem fer el dir de dependencies
#COPY build/libs/library-example-0.1-all.jar app.jar
ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]
