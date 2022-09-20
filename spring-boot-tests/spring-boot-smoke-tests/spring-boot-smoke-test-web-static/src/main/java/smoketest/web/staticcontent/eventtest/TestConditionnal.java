package smoketest.web.staticcontent.eventtest;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

/**
 * @author ZhaoJinLiang
 * @Date 2022/3/29 6:00 下午
 */
@ComponentScan
public class TestConditionnal implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return false;
	}
}
