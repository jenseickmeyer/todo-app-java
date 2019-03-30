#!/bin/bash

S3_BUCKET=
INPUT_FILE=sam-template.yaml
OUTPUT_FILE=sam-template-output.yaml
STAGE_NAME=dev
STACK_NAME=todo-app-java-$STAGE_NAME

docker run --rm \
           -v $PWD:/usr/src/app \
           -v maven-repository:/root/.m2/repository \
           -w /usr/src/app maven:3.6-jdk-8 mvn clean package -DskipTests

aws cloudformation package --template-file $INPUT_FILE --output-template-file $OUTPUT_FILE --s3-bucket $S3_BUCKET
aws cloudformation deploy --template-file $OUTPUT_FILE --stack-name $STACK_NAME --parameter-overrides StageName=$STAGE_NAME --capabilities CAPABILITY_IAM
