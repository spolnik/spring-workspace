package com.apress.isf.java.test;

import com.apress.isf.spring.config.MyDocumentsContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyDocumentsContext.class, loader = AnnotationConfigContextLoader.class)
public class MyDocumentSpringJavaConfigTest extends MyDocumentsTestBase {
}
