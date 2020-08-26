# github-trending-languges
The application is a REST microservice that lists languages used in the 100 trending github repositories for the last 30 days.<br />
It fetches Github API: https://api.github.com/search/repositories?q=created:>{date}&sort=stars&order=desc <br />
The result is a JSON listing trending languages as follow:
  - Language name
    - The list of repositories using this language
 
## Getting Started
### Prerequisites
- Docker v2, or:
- Java 8
- Spring 2.3.2

### Running the application with Maven
- Clone repository, compile and run:
```
git clone https://github.com/douniahm/github-trending-languages
cd github-trending-languages
mvn package
java -jar target/github-trending-languages-0.0.1-SNAPSHOT.jar
```
- Open browser, go to: 
```
http://localhost:8080/api/languages
```
### Running the application using Docker
- Pull Docker image from dockerhub:
```
docker pull douniahm/github-trending-languages
```
- Run image:
```
 docker run -p 8080:8080 douniahm/github-trending-languages
 ```
  
## Built with
- Java 8
- Spring 2.3.2
- Eclipse IDE
- Docker

## Author
- Dounia AIT HAMMI
