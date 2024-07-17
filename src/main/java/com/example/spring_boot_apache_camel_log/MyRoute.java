package com.example.spring_boot_apache_camel_log;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("timer:poo?period=5000")
//                .setBody().constant("Hello , Daneth")
//                .to("log:result");

        from("timer:foo?period=10000")
                .routeId("myRoute")
                .setBody(simple("Hello World from Camel"))
               .log("Processing message: ${body}")
               .to("log:result");
//        from("direct:start").to("log:foo?logMask=true");
    }
}
