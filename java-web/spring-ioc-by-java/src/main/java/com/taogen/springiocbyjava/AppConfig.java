package com.taogen.springiocbyjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The AppConfig class above would be equivalent to the following Spring <beans/> XML:
 * <beans>
 *   <bean id="myBean" class="com.taogen.springiocbyjava.MyBean"/>
 * </beans>
 */
@Configuration
public class AppConfig
{
    @Bean
    public MyBean myBean()
    {
        return new MyBean();
    }
}
