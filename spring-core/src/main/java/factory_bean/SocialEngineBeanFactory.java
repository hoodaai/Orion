package factory_bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by varun on 20/09/15.
 */
public class SocialEngineBeanFactory implements FactoryBean<SocialEngine> {
    @Override
    public SocialEngine getObject() throws Exception {
        return new SocialEngine();
    }

    @Override
    public Class getObjectType() {
        return SocialEngine.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
