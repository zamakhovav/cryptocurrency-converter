package service;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HttpServiceImpl implements HttpService {
    private CloseableHttpClient httpClient;
    @Getter @Setter
    private String url;
    private HttpRequestBase httpRequest;

    public HttpServiceImpl(String url) {
        this.url = url;
    }

    @Override
    public JSONObject getResponse(HttpMethod method) throws JSONException, HttpServiceException {
        try {
            setHttpMethod(method);
            httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            JSONObject responseRates = new JSONObject(EntityUtils.toString(entity));
            httpClient.close();
            return responseRates;
        } catch (HttpServiceException | IOException e) {
            throw new HttpServiceException("Cannot get response, the error message is " + e.getMessage());
        }
    }

    private void setHttpMethod(HttpMethod method) throws HttpServiceException {
        if (method.equals(HttpMethod.GET)) {
            httpRequest = new HttpGet(url);
        } else if (method.equals(HttpMethod.POST)) {
            httpRequest = new HttpPost(url);
        } else {
            throw new HttpServiceException(String.format("Unsupported http method %s", method));
        }
    }
}
