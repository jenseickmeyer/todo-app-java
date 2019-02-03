package com.carpinuslabs.todo;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Entrypoints for API Gateway
 */
public class TaskRequestHandler {
  public APIGatewayProxyResponseEvent listTasks(APIGatewayProxyRequestEvent request, Context context) {
    Map<String, String> headers = new HashMap<String, String>();
    headers.put("Content-Type", "text/plain");
    return new APIGatewayProxyResponseEvent().withStatusCode(200).withHeaders(headers).withBody("Hello, world!");
  }
}
