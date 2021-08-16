package service;

import org.json.JSONException;
import org.json.JSONObject;

public interface HttpService {

    public JSONObject getResponse(HttpMethod method) throws JSONException, HttpServiceException;

}
