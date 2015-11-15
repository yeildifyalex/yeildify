# Yeildify Coding Test
As defined at https://gist.github.com/willfrew/5c86559600fa39fa5f8a

## Service 1
A simple Java REST service which takes a JSON message object and returns the same JSON with a random number between 0 & 1 included in the response.

The REST Server is embedded within the Jar & libraries so it can be run directly from Java.

The Dockerfile is contained within the Maven src structure so that simple substitution can be performed.

## Service 2
A simple Java REST service which takes a JSON message object and returns the message in reversed order.

The REST Server is embedded within the Jar & libraries so it can be run directly from Java.

The Dockerfile is contained within the Maven src structure so that simple substitution can be performed.

## Service 1 Integration Test
A simple Maven project which uses the surefire plugin and JUnit to control the running of the tests.
RestAssured is used to provide a clean way of creating and validating the HTTP request/response.

## Travis CI Setup

The travis.yml file is setup so that it performs the following actions in order:

1. Build Service1 (Maven Clean & Install)
2. Build Service2 (Maven Clean & Install)
3. Docker Build Service1 (Docker Build with Tag yeildifyalex/service1)
4. Docker Build Service2 (Docker Build with Tag yeildifyalex/service2)
5. Docker Run Service1 (Docker Run Detached with Port Mapping 8080 to 8080 for Tag yeildifyalex/service1)
6. Docker Run Service2 (Docker Run Detached for Tag yeildifyalex/service2)
7. Run Integration Tests (Maven Clean Verify)
