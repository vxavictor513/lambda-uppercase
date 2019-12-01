# spring-cloud-function-aws
This is a sample code for Spring Cloud Function with AWS Lambda adapter.

## Getting Started

### Prerequisite
1. Create an [execution role](https://docs.aws.amazon.com/lambda/latest/dg/lambda-intro-execution-role.html) for AWS Lambda.

### Create Lambda Function (expose to AWS API Gateway)
Use `org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler` when using AWS API Gateway as Lambda trigger. 
> Replace `[USERID]` and `[ROLE]` with your corresponding AWS details.

```shell script
aws lambda create-function --function-name lambda-uppercase --role arn:aws:iam::[USERID]:role/[ROLE] --zip-file fileb://target/lambda-uppercase-0.0.1-SNAPSHOT-aws.jar --handler org.springframework.cloud.function.adapter.aws.SpringBootApiGatewayRequestHandler --description "Spring Cloud Function AWS Lambda Example" --runtime java11 --region ap-southeast-1 --timeout 30 --memory-size 1024 --publish
```

### Update Lambda Function
```shell script
mvn -U clean package -DskipTests && aws lambda update-function-code --function-name lambda-uppercase --zip-file fileb://target/lambda-uppercase-0.0.1-SNAPSHOT-aws.jar --publish
```

### cURL Command
```shell script
curl -X POST \
  https://{restapi_id}.execute-api.{region}.amazonaws.com/{stage_name}/{path} \
  -H 'Content-Type: application/json' \
  -d '{
	"message": "Singapore"
}'
```

## Reference

1. https://github.com/spring-cloud/spring-cloud-function/tree/master/spring-cloud-function-samples/