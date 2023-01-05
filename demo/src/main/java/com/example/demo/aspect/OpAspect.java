package com.example.demo.aspect;

import com.example.demo.annotation.OpLog;
import com.example.demo.entites.OpType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * OpLog的切面处理类，用于通过注解获取日志信息，记录日志
 */
@Aspect
@Component
@Slf4j
public class OpAspect {

    @Around("@annotation(com.example.demo.annotation.OpLog)")
    public Object log(ProceedingJoinPoint pjp) throws Exception {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        OpLog opLog = method.getAnnotation(OpLog.class);

        Object response = null;

        try{
            //执行目标方法
            response = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        if(StringUtils.isNotEmpty(opLog.opItemIdExpression())){
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression expression = parser.parseExpression(opLog.opItemIdExpression());

            StandardEvaluationContext context = new StandardEvaluationContext();
            //获取参数值
            Object[] args = pjp.getArgs();

            //获取运行时参数的名称
            LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();
            String[] parameterNames = discoverer.getParameterNames(method);

            //将参数绑定到context中
            if(parameterNames != null){
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i],args[i]);
                }
            }

            //将方法的resp当作变量放到context中
            if(response != null){
                context.setVariable(response.getClass().getSimpleName(),response);
            }

            //解析表达式，获取结果
            String itemId = String.valueOf(expression.getValue(context));

            //执行日志记录
            handler(opLog.opType(),opLog.opItem(),itemId);

        }
        return response;
    }
    //  通过日志打印输出
    private void handler(OpType opType, String opItem, String itemId) {
        log.info("opType：[{}],opItem：[{}],itemId：[{}]",opType.name(),opItem,itemId);
    }
}
