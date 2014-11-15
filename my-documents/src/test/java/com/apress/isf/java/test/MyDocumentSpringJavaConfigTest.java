package com.apress.isf.java.test;

import com.apress.isf.spring.config.MyDocumentsContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyDocumentsContext.class)
public class MyDocumentSpringJavaConfigTest extends MyDocumentsTestBase {
}
