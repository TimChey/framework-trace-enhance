package com.fmsh.framework.plugins.trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.util.SpanNameUtil;
import org.springframework.stereotype.Component;

/**
 * dapper trace 追踪切面类, 用于追踪业务代码service和Dao类中方法执行时间
 * 
 * @author chenwei
 *
 */
@Aspect
@Component
@ConditionalOnProperty(value = "fmshiot.dapper.trace.switch", havingValue = "true", matchIfMissing = false)
public class DapperTraceAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final Tracer tracer;

    @Autowired
    private final TraceKeys traceKeys;

    @Value("${eureka.instance.instance-id:na}")
    private String instanceId;

    public DapperTraceAspect(Tracer tracer, TraceKeys traceKeys) {
        this.tracer = tracer;
        this.traceKeys = traceKeys;
    }

    @Pointcut("execution(* *..fmsh..service.*Service.*(..))")
    private void dapperTraceServiceFlag() {
        // no method body
    }

    @Pointcut("execution(* *..fmsh..dao.*Dao.*(..))")
    private void dapperTraceDaoFlag() {
        // no method body
    }

    @Around(value = "dapperTraceServiceFlag() || dapperTraceDaoFlag()")
    public Object traceBackgroundThread(final ProceedingJoinPoint pjp) throws Throwable {
        Span span = null;
        try {
            String spanName = SpanNameUtil.toLowerHyphen(pjp.getSignature().getName());
            span = this.tracer.createSpan(spanName);
            this.tracer.addTag(Span.INSTANCEID, instanceId);// add instanceId
            // add class name
            this.tracer.addTag(this.traceKeys.getAsync().getClassNameKey(), pjp.getTarget().getClass().getSimpleName());
            //add method name
            this.tracer.addTag(this.traceKeys.getAsync().getMethodNameKey(), pjp.getSignature().getName());
        } catch (Exception e) {
            logger.info("DapperTraceAspect trace start failer");
        }

        try {
            return pjp.proceed();
        } finally {
            if (null != span) {
                this.tracer.close(span);
            }
        }
    }

}
