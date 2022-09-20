/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package smoketest.web.staticcontent;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import smoketest.web.staticcontent.beanfactorytest.pojo.*;
import smoketest.web.staticcontent.eventtest.RegisterService;

import java.beans.beancontext.BeanContextChild;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Basic integration tests for demo application.
 *
 * @author Dave Syer
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SampleWebStaticApplication.class)
class SampleWebStaticApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private RegisterService registerService;

	@Test
	public void testListableBeanFactory(){
		ClassPathResource resource = new ClassPathResource("test.xml");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
		beanDefinitionReader.loadBeanDefinitions(resource);
		// 直接打印容器中的所有Bean
		System.out.println("加载xml文件后容器中的Bean：");
		beanFactory.registerSingleton("dog", new Dog());
//		beanFactory.registerBeanDefinition("dog2", BeanDefinitionBuilder.genericBeanDefinition(Dog.class).getBeanDefinition());
		Stream.of(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
		BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
		System.out.println();
	}

	@Test
	public void testIgnore(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(SampleWebStaticApplication.class);
		//忽略该类型注入
		ctx.getBeanFactory().ignoreDependencyType(Dog.class);
//		忽略该接口所有setter方法注入
		ctx.getBeanFactory().ignoreDependencyInterface(Dog.class);
		ctx.refresh();
		Dog bean = ctx.getBean(Dog.class);
		BeanA bean1 = ctx.getBean(BeanA.class);
		BeanB bean2 = ctx.getBean(BeanB.class);
		BeanC bean3 = ctx.getBean(BeanC.class);
		Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
	}

	@Test
	public void testRegister(){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SampleWebStaticApplication.class);
		registerService.register("test");
	}

	@Test
	public void test(){
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SampleWebStaticApplication.class);
		String[] forType = ctx.getBeanNamesForType(Dog.class);
		Dog dog = ctx.getBean(Dog.class);
		System.out.println(dog);
		dog = ctx.getBean("dogFactory", Dog.class);
//		dog.test();

	}

	@Test
	void testHome() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("<title>Static");
	}

	@Test
	void testCss() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/webjars/bootstrap/3.0.3/css/bootstrap.min.css",
				String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("body");
		assertThat(entity.getHeaders().getContentType()).isEqualTo(MediaType.valueOf("text/css"));
	}

}
