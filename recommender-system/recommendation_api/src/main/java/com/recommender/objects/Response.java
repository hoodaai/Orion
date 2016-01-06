package com.recommender.objects;

import java.util.HashMap;
import java.util.Map;

/**
 *  Contains response status.
 */
public class Response {

    private String success;
    private String status;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Static Factory method to create response map.
     * @param success
     * @param status
     * @return map containing Response
     */
    public static Map<String, Response> createResponseMap(String success, String status){
        Response response = new Response();
        response.setStatus(status);
        response.setSuccess(success);
        Map map = new HashMap<String, Response>();
        map.put("Response", response);
        return map;
    }
}
