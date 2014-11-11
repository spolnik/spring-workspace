package com.apress.isf.java.test;

import com.apress.isf.java.service.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-login-context.xml")
public class MyDocumentsWithLoginTest {

    private static final Logger log =
            LoggerFactory.getLogger(MyDocumentsWithLoginTest.class);

    private static final String EMAIL = "test@mydocuments.com";
    private static final String PASSWORD = "test123";

    private static final String SUCCESS = "This user is authorized";
    private static final String FAILURE = "WARNING! This user is not authorized";

    @Autowired
    private Login login;

    @Test
    public void testLogin() throws Exception {
        log.debug("Login test.");

        assertThat(login).isNotNull();

        if (login.isAuthorized(EMAIL, PASSWORD))
            System.out.println(SUCCESS);
        else
            System.out.println(FAILURE);
    }
}
