package com.xdclass.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;

public interface IRouteService {
	
	void refresh();
	
    Collection<RouteDefinition> getRouteDefinitions();

    boolean save(RouteDefinition routeDefinition);

    boolean delete(String routeId);
}
