package com.demo.api_gateway.Filter;

import com.demo.api_gateway.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchnage,chain)-> {
            if(validator.isSecured.test(exchnage.getRequest())){
                //header contains token or not
                if(!exchnage.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchnage.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")){
                    authHeader = authHeader.substring(7).trim();
                }
                try{
                    jwtUtil.validateToken(authHeader);
                    List<String> roles = jwtUtil.extractRoles(authHeader);
                    // Check roles for specific endpoints
                    if (isRestrictedEndpoint(exchnage.getRequest().getURI().getPath(), roles)) {
                        return handleUnauthorized(exchnage, "Access Denied: Insufficient permissions");
                    }
                }catch (Exception e){
                    return handleUnauthorized(exchnage, "Unauthorized Access to application");
                }
            }
            return chain.filter(exchnage);
        });
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(
                String.format("{\"error\": \"%s\"}", message).getBytes(StandardCharsets.UTF_8)
        )));
    }

    private boolean isRestrictedEndpoint(String path, List<String> roles) {
       List<String> adminApis = Arrays.asList("/product/add","/product/delete/","/product/update/");
        // Define the endpoints that require specific roles
        for(String adminApi:adminApis){
            if(path.startsWith(adminApi)){
                return !roles.contains("ROLE_ADMIN");
            }
        }
        // Add other role checks as needed
        return false;
    }

    public static class Config{

    }
}
