package com.apress.isf.java.test;

import com.apress.isf.spring.views.ResourceLoaderMenu;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDocumentsMenuTest {

    private static final Logger log =
            LoggerFactory.getLogger(MyDocumentsMenuTest.class);

    @Test
    public void testMenu() throws Exception {
        log.debug("About to read the Resource file using ResourceLoader: menu.txt");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");

        final ResourceLoaderMenu menu = context.getBean(ResourceLoaderMenu.class);
        assertThat(menu).isNotNull();
        menu.printMenu("classpath:META-INF/data/menu.txt");
    }
}
