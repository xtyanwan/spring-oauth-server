package com.monkeyk.sos;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 * @author Shengzhao Li
 */
@ContextConfiguration(locations = {""}, initializers = {TestApplicationContextInitializer.class})
public abstract class ContextTest extends AbstractTransactionalJUnit4SpringContextTests {

    @BeforeTransaction
    public void beforeTest() {

    }
}