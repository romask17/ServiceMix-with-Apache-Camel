package ru.camel.mail;

import org.apache.camel.Exchange;

public class LoggerClass implements org.apache.camel.Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("\nSubject: " + exchange.getIn().getHeaders());
        String body = exchange.getIn().getBody(String.class);
        System.out.println("\nBody: " + body);
    }
}
