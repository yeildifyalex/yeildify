/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sandopolus.yeildify.service1.resources;

import com.sandopolus.yeildify.service1.model.Message;
import com.sandopolus.yeildify.service1.model.RandomMessage;
import java.security.SecureRandom;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sandygordon
 */
@Path("/api")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RamdomResourceImpl {
    
    private static final SecureRandom RAND = new SecureRandom();
    
    @POST
    public RandomMessage generateRandomValue(Message msg) {
        RandomMessage result = new RandomMessage();
        result.setMessage(new StringBuilder(msg.getMessage()).reverse().toString());
        result.setRand(RAND.nextDouble());
        return result;
    }
}
