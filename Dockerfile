FROM java:8 
VOLUME /tmp
ADD ./target/ebuy-cart-service-1.0.1.jar  ebuy-cart-service.jar
RUN sh -c 'touch /ebuy-cart-service.jar'
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java  -jar  /ebuy-cart-service.jar" ]
