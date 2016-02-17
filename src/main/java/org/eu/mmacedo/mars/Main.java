package org.eu.mmacedo.mars;

import org.eu.mmacedo.mars.resource.MarsResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
  //  	SpringApplication.run(Main.class, args);    	
    	
        Container container = new Container();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addClass(MarsResource.class);
        deployment.addAllDependencies();
        container.start().deploy(deployment);
    }
}