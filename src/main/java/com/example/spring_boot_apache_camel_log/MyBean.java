package com.example.spring_boot_apache_camel_log;

import org.springframework.context.annotation.Configuration;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;


@Configuration
public class MyBean {

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext camelContext) {
                try {
                    camelContext.addRoutes(new RouteBuilder() {
                        @Override
                        public void configure() throws Exception {
                            from("timer:foo?period=10000")
                                    .routeId("myRoute")
                                    .setBody(simple("Hello World from Bean"))
                                    .log("Processing message: ${body}")
                                    .to("log:result");
                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
                 // Additional configuration after Camel context starts
            }
        };
    }
}
