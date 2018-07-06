### Run junit tests

`mvn install -DskipITs=true`

### Run with integration tests

`mvn install`

### Dynamodb (Pre requisite to running IT Tests)

###### Download from https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.html

###### Create tables by running SetUpForIntegration under test folder

Remove @Ignore 

3 Tables are created 

1.SurveyInfo 
2.Answer
3.Question

### Run application locally

`mvn spring-boot:run`

### Curl commands 

Create question

`curl -X POST \
   http://localhost:8081/question \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: bc32c0ee-1b7a-adfd-c7e9-0360e96d9c18' \
   -d '{
 "id" : "1",
 "name" : "Its fun"
 }'`
 
 Submit SurveyResponse 
 
 `curl -X POST \
    http://localhost:8081/survey \
    -H 'cache-control: no-cache' \
    -H 'content-type: application/json' \
    -H 'postman-token: 509958b0-afaa-7f3d-92e1-64c35942f327' \
    -d '{
  	"sprintNumber" :1,
  	"questions" :
  	[
  		{
  			"question" : "1",
  			"status" : "Yellow",
  			"response" : "I'\''m not learning anything new"
  		}
  	]
  	
  }'`
  
  Get Survey Results for a Sprint 
  
  curl -X GET "http://localhost:8081/results?sprint=1"