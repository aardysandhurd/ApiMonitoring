package org.raman.api.monitoring;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class CheckApiStat {
    private static final Logger logger = LogManager.getLogger(CheckApiStat.class);
    
    private static final int TIMEOUT_SECONDS = 30;
    private static final int SUCCESS_STATUS_CODE_MIN = 200;
    private static final int SUCCESS_STATUS_CODE_MAX = 299;

    public List<String> getListOfFailedApis(List<String> endPoints) {
        List<String> failedApis = new ArrayList<>();
        
        if (endPoints == null || endPoints.isEmpty()) {
            logger.warn("No endpoints provided for checking");
            return failedApis;
        }
        
        logger.info("Checking {} endpoints for status", endPoints.size());
        
        for (String endPoint : endPoints) {
            if (endPoint == null || endPoint.trim().isEmpty()) {
                logger.warn("Skipping empty or null endpoint");
                continue;
            }
            
            try {
                int statusCode = getApiStatus(endPoint.trim());
                if (statusCode < SUCCESS_STATUS_CODE_MIN || statusCode > SUCCESS_STATUS_CODE_MAX) {
                    failedApis.add(endPoint);
                    logger.error("API failed: {} - Status: {}", endPoint, statusCode);
                } else {
                    logger.info("API passed: {} - Status: {}", endPoint, statusCode);
                }
            } catch (Exception e) {
                failedApis.add(endPoint);
                logger.error("Error checking API {}: {}", endPoint, e.getMessage());
            }
        }
        
        logger.info("API check completed. Failed endpoints: {}/{}", failedApis.size(), endPoints.size());
        return failedApis;
    }

    private int getApiStatus(String endPoint) {
        try {
            // Handle both relative and absolute URLs
            String baseUrl = endPoint.startsWith("http") ? "" : "https://formatjsononline.com";
            String fullUrl = baseUrl + endPoint;
            
            RequestSpecification req = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .setContentType(ContentType.JSON)
                    .build();
            
            Response response = given()
                    .spec(req)
                    .relaxedHTTPSValidation()
                    .timeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
                    .when()
                    .get(endPoint)
                    .then()
                    .extract()
                    .response();
            
            return response.getStatusCode();
            
        } catch (Exception e) {
            logger.error("Failed to get status for endpoint {}: {}", endPoint, e.getMessage());
            throw e;
        }
    }
}
