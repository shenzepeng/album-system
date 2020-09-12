package kxg.album.system.provider.aspect;

import kxg.album.system.provider.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 要写注释呀
 */
@Aspect
@Component
@Slf4j
public class AlbumAspect {

    //统一切点,对kxg.album.system.provider.controller及其子包中所有的类的所有方法切面
    @Pointcut("execution(public * kxg.album.system.provider.controller..*.*(..))")
    public void Pointcut() {
    }

    //前置通知
    @Before("Pointcut()")
    public void beforeMethod(JoinPoint joinPoint){
        log.info("调用了前置通知");

    }

    //@After: 后置通知
    @After("Pointcut()")
    public void afterMethod(JoinPoint joinPoint){
        log.info("调用了后置通知");

    }
    //@AfterRunning: 返回通知 rsult为返回内容
    @AfterReturning(value="Pointcut()",returning="result")
    public void afterReturningMethod(JoinPoint joinPoint,Object result){
        log.info("调用了返回通知 {}", JsonUtils.objectToJson(result));
    }
    //@AfterThrowing: 异常通知
    @AfterThrowing(value="Pointcut()",throwing="e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e){
        log.info("调用了异常通知");
    }

    //@Around：环绕通知
    @Around("Pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("around执行方法之前");
        Object object = pjp.proceed();
        log.info("around执行方法之后--返回值：" +object);
        return object;
    }
}
