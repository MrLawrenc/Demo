package demo.Spring.annotation.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: Liu Mingyao
 * @create: 2018-08-05 09:33
 **/
public class WindowsCondition implements Condition {
    /**
     * @Description: 判断是否是window系统，是就返回true
     * @Param:conditionContext判断条件能使用的上下文环境 annotatedTypeMetadata 注释信息
     * @return:
     * @Author: Liu Ming
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        conditionContext.getBeanFactory();//获取bean工厂
        conditionContext.getClassLoader();//获取类加载器
        conditionContext.getRegistry();//获取所有的bean的注册类

        String property = conditionContext.getEnvironment().getProperty("os.name");
        if (property.contains("windows")) {
            return true;
        }
        return false;
    }
}
