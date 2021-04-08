package com.xdclass;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Version 1.0
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String [] args){

        SpringApplication.run(GatewayApplication.class,args);
    }


}
