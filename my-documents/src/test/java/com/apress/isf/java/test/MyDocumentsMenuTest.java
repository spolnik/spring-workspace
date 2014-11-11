package com.apress.isf.java.test;

import com.apress.isf.spring.views.ResourceLoaderMenu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-context.xml")
public class MyDocumentsMenuTest {

    private static final Logger log =
            LoggerFactory.getLogger(MyDocumentsMenuTest.class);

    @Autowired
    private ResourceLoaderMenu menu;

    @Test
    public void testMenu() throws Exception {
        log.debug("About to read the Resource file using ResourceLoader: menu.txt");

        assertThat(menu).isNotNull();
        menu.printMenu("classpath:META-INF/data/menu.txt");
    }
}
