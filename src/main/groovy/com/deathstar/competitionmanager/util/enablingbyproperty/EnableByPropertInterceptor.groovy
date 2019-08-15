package com.deathstar.competitionmanager.util.enablingbyproperty

import groovy.util.logging.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Log
@Component
@Aspect
class EnableByPropertInterceptor {

    @Autowired
    Environment env

    @Pointcut('execution(public * *(..))')
    void anyMethod() {}

    @Around('anyMethod() && @annotation(enableByProperty)')
    Object invoke(final ProceedingJoinPoint pjp, EnableByProperty enableByProperty) {
        String propertyEnable = env.getProperty(enableByProperty.value())
        if (propertyEnable != null && propertyEnable.toLowerCase() == "enable") {
            log.info("Process method calling: ${pjp.getSignature()}")
            return pjp.proceed()
        } else {
            log.info("Skip method calling: ${pjp.getSignature()}")
            return null
        }
    }
}
