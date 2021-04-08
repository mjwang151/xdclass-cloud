package com.xdclass.config;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdclass.pojo.GatewayRoute;
import com.xdclass.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class RouteService implements IRouteService,ApplicationEventPublisherAware {

   /**
    * 事件发布
    */
   private ApplicationEventPublisher publisher;

    /**
     * 路由定义
     */
    private List<RouteDefinition> routeDefinitions;
    
    
    
    @PostConstruct
    private void loadRouteDefinition() {
    	  log.info("loadRouteDefinition, 开始初使化路由");
    	  routeDefinitions = load();
          log.info("共初使化路由信息：{}", routeDefinitions.size());
    }

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitions;
    }

    @Override
    public boolean save(RouteDefinition routeDefinition) {
        return true;
    }

    @Override
    public boolean delete(String routeId) {
        return true;
    }

    @Override
	public void refresh() {
    	log.info("刷新路由配置...");
		routeDefinitions = load();
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
	}
	
    
	private List<RouteDefinition> load() {
		List<GatewayRoute> gatewayRoutes = new ArrayList<>();
        GatewayRoute a1 = new GatewayRoute();
        a1.setFilters("[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]");
        a1.setRouteId("a1");
        a1.setOrders(100);
        a1.setUri("lb://xdclass-order-service");
        a1.setPredicates("[{\"name\":\"Path\",\"args\":{\"pattern\":\"/neworder/**\"}}]");
        gatewayRoutes.add(a1);
		if (gatewayRoutes == null || gatewayRoutes.isEmpty()) {
			return new ArrayList<>(0);
		}
		List<RouteDefinition> routeDefinitions = new ArrayList<>(gatewayRoutes.size());
		gatewayRoutes.forEach(gatewayRoute -> {
			RouteDefinition routeDefinition = convertGateway2RouteDefinition(gatewayRoute);
			routeDefinitions.add(routeDefinition);
		});
		
		return routeDefinitions;
	}
	
	private RouteDefinition convertGateway2RouteDefinition(GatewayRoute gatewayRoute) {
		RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(gatewayRoute.getRouteId());
        routeDefinition.setOrder(gatewayRoute.getOrders());
        routeDefinition.setUri(URI.create(gatewayRoute.getUri()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            routeDefinition.setFilters(objectMapper.readValue(gatewayRoute.getFilters(), new TypeReference<List<FilterDefinition>>() {}));
            routeDefinition.setPredicates(objectMapper.readValue(gatewayRoute.getPredicates(), new TypeReference<List<PredicateDefinition>>() {
            }));
            
        } catch (IOException e) {
            log.error("网关路由对象转换失败", e);
        }
        log.info("路由id【"+gatewayRoute.getRouteId()+"】,路径为："+gatewayRoute.getUri()+"加载成功！");
        return routeDefinition;
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}


}
