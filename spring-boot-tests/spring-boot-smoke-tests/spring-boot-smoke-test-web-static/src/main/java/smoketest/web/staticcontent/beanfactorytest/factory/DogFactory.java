package smoketest.web.staticcontent.beanfactorytest.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import smoketest.web.staticcontent.beanfactorytest.pojo.Dog;

/**
 * @author ZhaoJinLiang
 * @Date 2022/3/28 4:37 下午
 */
//@Component
public class DogFactory implements FactoryBean<Dog> {
	@Override
	public Dog getObject() throws Exception {
		return new Dog();
	}

	@Override
	public Class<?> getObjectType() {
		return Dog.class;
	}
}
