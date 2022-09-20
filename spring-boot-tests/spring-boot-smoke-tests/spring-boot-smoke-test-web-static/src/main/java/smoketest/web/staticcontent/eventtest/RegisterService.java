package smoketest.web.staticcontent.eventtest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author ZhaoJinLiang
 * @Date 2022/3/29 2:40 下午
 */
@Service
public class RegisterService implements ApplicationEventPublisherAware {

	ApplicationEventPublisher publisher;

	public void register(String username){
		System.out.println(username + "注册成功");
		publisher.publishEvent(new RegisterSuccessEvent(username));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}
}
