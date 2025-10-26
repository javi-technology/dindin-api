package com.javitech.dindinapi.service.utils.http;

import java.util.HashMap;

public interface HttpService {
    HashMap<String, Object> createResponse(String message, Object data);
}
