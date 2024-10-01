package com.demo.api_gateway.Monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Controller
public class ProductServiceHealthCheck implements HealthIndicator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceHealthCheck.class);

   @Value("${productService.address}")
   private String address;
   @Value("${productService.port}")
   private String port;

    @Override
    public Health health() {
        try {
            if(isServiceUp()){
                return Health.up().withDetail("Product-service","Is Working good").build();
            }else{
                return Health.down().withDetail("product-service","is Down").build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isServiceUp() throws IOException {
        Socket isSocket = new Socket();
        try {
            isSocket.connect(new InetSocketAddress(address, Integer.parseInt(port)), 3000);
            return true;
        }catch (IOException ex){
            LOGGER.debug("Exception occured while connecting to the server : {} ",ex.getMessage());
            return false;
        }finally {
            isSocket.close();
        }

    }
}
