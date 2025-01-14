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

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import smoketest.web.staticcontent.beanfactorytest.pojo.BeanB;
import smoketest.web.staticcontent.beanfactorytest.pojo.Dog;

@SpringBootApplication
public class SampleWebStaticApplication extends SpringBootServletInitializer {


	@Bean(autowire = Autowire.BY_TYPE)
	public BeanB beanB(){
		return new BeanB();
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public Dog dog(){
		return new Dog();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebStaticApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleWebStaticApplication.class, args);
	}

}
