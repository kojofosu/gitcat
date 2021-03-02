package com.mcdev.gitcat.controlllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstanceController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstanceByApplicationName(@PathVariable String applicationName){
        return this.discoveryClient.getInstances(applicationName);
    }
}