package com.apress.isf.spring.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Component("menu")
public class ResourceLoaderMenu {

    @Autowired
    private ResourceLoader resourceLoader;

    public void printMenu(String menuFile) {
        try {
            try (final InputStream stream = resourceLoader.getResource(menuFile).getInputStream()) {
                try (final Scanner scanner = new Scanner(stream)) {
                    while (scanner.hasNext()) {
                        System.out.println(scanner.nextLine());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
