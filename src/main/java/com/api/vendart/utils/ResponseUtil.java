package com.api.vendart.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ResponseUtil {
    public Map<String, String> getResponse(String[][] params, int length) {
        Map<String, String> response = new HashMap<>();
        for (int i = 0; i < length; i++) {
            response.put(params[i][i], params[i+1][i]);
        }
        return response;
    }
}
