package com.example.mycinema;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;

@Component
public class BrowserLauncher {
    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser() {
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();
        try{
            desktop.browse(new URI("http://localhost:8080"));
        } catch(Exception e){
            System.err.println("It was not possible to launch your browser. Try access it manually at localhost:8080");
            System.err.println(e);
        }
    }
}
