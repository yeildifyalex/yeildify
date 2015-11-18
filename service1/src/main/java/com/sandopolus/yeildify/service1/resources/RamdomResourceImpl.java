/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sandopolus.yeildify.service1.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandopolus.yeildify.service1.model.Message;
import com.sandopolus.yeildify.service1.model.RandomMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author sandygordon
 */
@Path("/api")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RamdomResourceImpl {

    private static SecureRandom RAND;
    private static ObjectMapper JSON_MAPPER;
    private static HttpClient HTTP_CLIENT;

    @PostConstruct
    public void init() {
        RAND = new SecureRandom();
        JSON_MAPPER = new ObjectMapper();
        HTTP_CLIENT = HttpClientBuilder.create().build();
    }

    @POST
    public RandomMessage generateRandomValue(Message msg) {
        try {
            HttpPost req = new HttpPost("http://service2:8080/reverse");
            req.addHeader("accept", "application/json");
            req.addHeader("content-type", "application/json");
            req.setEntity(new StringEntity(JSON_MAPPER.writeValueAsString(msg)));

            HttpResponse resp = HTTP_CLIENT.execute(req);
            if (resp.getStatusLine().getStatusCode() != 200) {
                throw new InternalServerErrorException("Service2 returned status code " + resp.getStatusLine().getStatusCode());
            }
            Message reversedMessage = JSON_MAPPER
                    .readValue(resp.getEntity().getContent(), Message.class);

            RandomMessage result = new RandomMessage();
            result.setMessage(reversedMessage.getMessage());
            result.setRand(RAND.nextDouble());
            return result;
        } catch (IOException ex) {
            throw new InternalServerErrorException("Error with comms to service2");
        }
    }
}
