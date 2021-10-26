package com.unistar.app3;

import com.unistar.app3.model.UserMessage;
import com.unistar.app3.utils.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URL; 
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
class BaseControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port); 
        System.out.println("IntegrationTest base = " + base.toString());
    }

    @Test
    void greetingDefaultTest() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString() + "/greeting", String.class);
        assertThat(response.getBody()).contains("my kid");
    }

    @Test
    void greetinglTest() throws Exception {
        ResponseEntity<UserMessage> response = restTemplate.exchange(
                RequestEntity.get(uri("/greetingl/joe")).build(), UserMessage.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        UserMessage message = response.getBody();
        assertTrue(message.getName().equals("joe"));
    }

    @Test
    void sendMessageTest() throws Exception {
        UserMessage message = new UserMessage();
        message.setName("testName");
        message.setEpochInUTC(DateUtils.getNowString());
        message.setEpochSecond(0);

        HttpEntity<String> request = getJsonRequest(message);
        String response = this.restTemplate.postForObject(uri("/sendmessage"), request, String.class);

        String expected = message.toString();
        assertEquals(expected, response);
    }

    private HttpEntity<String> getJsonRequest(UserMessage message) throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject obj = new JSONObject();
        obj.put("name", message.getName());
        obj.put("epochSecond", message.getEpochSecond()); 
        obj.put("epochInUTC", message.getEpochInUTC());

        return new HttpEntity<String>(obj.toString(), headers);
    }

    private URI uri(String path) {
        return restTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
    }
}
