package com.apress.isf.java.test;

import com.apress.isf.java.service.Login;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class MyDocumentsWithLoginTest {
    private static final Logger log =
            LoggerFactory.getLogger(MyDocumentsWithLoginTest.class);

    private static final String EMAIL = "test@mydocuments.com";
    private static final String PASSWORD = "test123";

    private static final String SUCCESS = "This user is authorized";
    private static final String FAILURE = "WARNING! This user is not authorized";

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-context.xml");
    }

    @Test
    public void testLogin() throws Exception {
        log.debug("Login test.");
        Login login = context.getBean(Login.class);
        assertThat(login).isNotNull();

        if (login.isAuthorized(EMAIL, PASSWORD))
            System.out.println(SUCCESS);
        else
            System.out.println(FAILURE);
    }
}
