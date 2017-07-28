package com.dish.ofm.titanAdapter.wsClient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.stream.Collectors;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.AbstractHandler;
import org.mortbay.jetty.webapp.WebAppContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JettyTest extends AbstractHandler {

    private Server server;

    @Before
    public void startServer() throws Exception {
        server = new Server(8080);
        server.setHandler(this);
        server.setStopAtShutdown(true);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/app");
        webAppContext.setResourceBase("src/main/webapp");
        webAppContext.setClassLoader(getClass().getClassLoader());
        server.addHandler(webAppContext);
        server.start();
    }
    @Test
    public void shouldBePreAuthenticated() throws Exception {
        String userId = "invalid";
        HttpClient client = new DefaultHttpClient();
        HttpGet mockRequest = new HttpGet("http://localhost:8080/app");
        mockRequest.setHeader("http-user",userId);
        Thread.sleep(15*60*1000);
        HttpResponse mockResponse = client.execute(mockRequest);
        BufferedReader rd = new BufferedReader
            (new InputStreamReader(mockResponse.getEntity().getContent()));
        // DO YOUR ASSERTIONS
    }

    @After
    public void shutdownServer() throws Exception {
        server.stop();
    }

    @Override
    public void handle(String target, HttpServletRequest request, HttpServletResponse response, int dispatch) throws IOException, ServletException {
        System.out.println("Hello Corina");
        printHeaders(request);
        System.out.println(read(request.getInputStream()));
    }

    public static String read(InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

    private void printHeaders(HttpServletRequest request) {
        System.out.println("headers");
        Collections.list(request.getHeaderNames()).forEach(headerKey -> System.out.println(headerKey + " " + request.getHeader((String)headerKey)));
    }
}