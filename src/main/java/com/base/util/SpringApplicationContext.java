package com.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Spring application context에 대한 참조를 반환하며, 해당 클래스는 Spring context 시작시 초기화되어야 한다.
 *
 * @author 조용상
 * @version 1.0
 * 
 * <pre>
 * 수정일 | 수정자 | 수정내용
 * ---------------------------------------------------------------------
 * 2014.01.27 조용상 최초 작성
 * </pre>
 */
@Component
public class SpringApplicationContext implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * Spring ApplicationContext가 시작 완료가될 때 호출되며, spring bean에 대한 참조를 갖는다.
     * @param context ApplicationContext에 대한 참조
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        setContext(applicationContext);
    }

    private static void setContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        //System.out.println(context.toString());
    }

    /**
     * Spring Application Context에 대한 엑세스를 제공한다.
     * 해당 메소드를 이용하여 특정 객체를 호출할 경우에는 적절한 대상 클래스로 캐스팅해야 한다.
     * @param beanName bean 이름(아이디)
     * @return bean 이름에 해당하는 객체 참조
     * @throws RuntimeException bean이 존재하지 않을경우
     */
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    /**
     * requiredType type의 bean을 반환한다.
     * @param requiredType type the bean must match
     * @return an instance of the single bean matching the required type
     * @throws RuntimeException bean이 존재하지 않을경우
     */
    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }
}