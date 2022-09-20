package smoketest.web.staticcontent.beanfactorytest.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author ZhaoJinLiang
 * @Date 2022/4/8 5:43 下午
 */
//@Component
public class FactoryBeanPostProccessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("拦截到Bean的初始化之前：" + bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("拦截到Bean的初始化之后：" + bean);
		return bean;
	}
}