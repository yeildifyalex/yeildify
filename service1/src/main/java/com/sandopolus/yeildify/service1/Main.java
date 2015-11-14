/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sandopolus.yeildify.service1;

import javax.enterprise.inject.spi.CDI;
import org.jboss.resteasy.cdi.CdiInjectorFactory;
import org.jboss.resteasy.cdi.ResteasyCdiExtension;
import org.jboss.resteasy.plugins.server.netty.cdi.CdiNettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.weld.environment.se.Weld;

/**
 *
 * @author sandygordon
 */
public class Main {
    
    public static void main(String[] args) {
        int port = 8080;
        Weld weld = new Weld();
        weld.initialize();
        ResteasyCdiExtension cdiExtension = CDI.current().select(ResteasyCdiExtension.class).get();
        CdiNettyJaxrsServer netty = new CdiNettyJaxrsServer();
        ResteasyDeployment rd = new ResteasyDeployment();
        rd.setActualResourceClasses(cdiExtension.getResources());
        rd.setInjectorFactoryClass(CdiInjectorFactory.class.getName());
        rd.getActualProviderClasses().addAll(cdiExtension.getProviders());
        netty.setDeployment(rd);
        netty.setPort(port);
        netty.setRootResourcePath("/");
        netty.start();
    }
}
