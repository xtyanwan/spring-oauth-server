package cc.wdcy;

import cc.wdcy.domain.shared.BeanProvider;
import cc.wdcy.domain.shared.security.WdcyUserDetails;
import cc.wdcy.domain.shared.security.SecurityUtils;
import cc.wdcy.web.context.SpringSecurityHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 * @author Shengzhao Li
 */
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public abstract class ContextTest extends AbstractTransactionalTestNGSpringContextTests {

    @BeforeTransaction
    public void beforeTest() {
        BeanProvider.initialize(applicationContext);
        SecurityUtils securityUtils = new SecurityUtils();
        securityUtils.setSecurityHolder(new SpringSecurityHolder() {
            @Override
            public WdcyUserDetails userDetails() {
                return null;
            }
        });
    }
}