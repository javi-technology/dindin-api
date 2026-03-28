package com.javitech.dindinapi.service.utils.http;

import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class HttpServiceImpl implements HttpService {

    public HttpServiceImpl() {}

    @Override
    public HashMap<String, Object> createResponse(String message, Object data) {
        HashMap<String, Object> resp = new HashMap<>();
        resp.put("message", message);
        resp.put("data", data);
        return resp;
    }
}
