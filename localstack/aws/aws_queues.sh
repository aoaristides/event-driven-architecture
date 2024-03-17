#!/bin/bash

echo ### Criando Queue(Standard) no SQS do LocalStack...
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name customerServiceQueue
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name productServiceQueue
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name paymentServiceQueue
aws --endpoint http://localhost:4566 --profile localstack sqs create-queue --queue-name orderServiceQueue