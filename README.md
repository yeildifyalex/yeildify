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
