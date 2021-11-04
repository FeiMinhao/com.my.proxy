# Google Search Proxy
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

This project is only for use to fetch the first 10 google search results. 
The project is based on SpringBoot Web Application Frame.

### How to start
To start the appliation you can use following ways. 
#### 1. Start App with mvn
- Clone the repo to your local host.
- Get into repo folder.
- Run command in terminal to build the project.
```sh
mvn package
```
- Run command line to start the app
```sh
java -jar target/searchproxy-0.0.1-SNAPSHOT.jar -Dspring.config.location=./application.properties
```

#### 2. Start App with Docker

Ensure you have already installed [docker](https://www.docker.com/products/docker-desktop).
You can build the docker on your local host with following command in the repo folder:
```sh
docker build ./ -t <your-image-name>
```
Or pull direct image from [dockerhub](https://hub.docker.com/repository/docker/thefay/searchproxy) with using
```sh
docker pull thefay/searchproxy
```
To start the docker container use
```sh
docker run -itd --name <your-container-name> thefay/searchproxy -p <your-host-port>:4041 
```

### 3. Start Test
Using ``` mvn test ```

### How to use
To test with the app is recommended to use postman. In the repo you can find a postman collection for direct import into postman. The search proxy API is secured under springboot oauth2 To call the API use first the Get Token request to fetch a token. Token will be automatically saved in postman env. Then Call the API Get Search Results

If everything success, then you should see as in the following screenshot.

![image](https://user-images.githubusercontent.com/11611036/140194675-8abfd99c-b748-45b4-85e6-d7799fc85fb9.png)

### Implementation

#### Call Google Search API
To use the Google Search API. Need to have an api Key and set an engine. Here is the [site](https://console.cloud.google.com/apis/credentials) how to get an API Key from google
and set an [engine](https://cse.google.com/cse/all)








