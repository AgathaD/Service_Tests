import entities.BaseClass;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ResponseHeaders extends BaseClass {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeMethod
    public void setup(){
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void closeResources() throws IOException {
        client.close();
        response.close();
    }

    @Test
    public void contentTypeIsJson() throws IOException {
        HttpGet get =new HttpGet(BASE_ENDPOINT +"/emojis");
        response = client.execute(get);
        Header contentType = response.getEntity().getContentType();
        assertEquals(contentType.getValue(), "application/json; charset=utf-8");

        ContentType ct = ContentType.getOrDefault(response.getEntity());
        assertEquals(ct.getMimeType(),"application/json");
    }
    @Test
    public void contentTypeIsPresent() throws IOException {
        boolean k;
        HttpGet get =new HttpGet(BASE_ENDPOINT +"/emojis");
        response = client.execute(get);
        Header contentType = response.getEntity().getContentType();
        if ( contentType!=null)
            k=true;
        else
            k=false;

        assertTrue(k);
    }

}