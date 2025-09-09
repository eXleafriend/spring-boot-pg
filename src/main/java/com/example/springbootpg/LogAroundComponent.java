package com.example.springbootpg;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAroundComponent {

	@Around("execution(* com.example.springbootpg..*.*(..))")
	public Object printCurrentTime(ProceedingJoinPoint jp) throws Throwable {

		if (jp.getSignature() instanceof MethodSignature method) {

			final var logger = LoggerFactory.getLogger(jp.getTarget().getClass());
			final var methodName = method.getName();
			final var paramNames = method.getParameterNames();
			final var args = jp.getArgs();
			try {
				if (paramNames.length == 0) {
					logger.debug("{}() <<< (no-args)", methodName);
				} else {
					for (int i = 0; i < paramNames.length; i++) {
						logger.debug("{}() <<< {} = {}", methodName, paramNames[i], args[i]);
					}
					// logger.debug(")");
				}
				final var result = jp.proceed();
				if (method.getReturnType() == void.class) {
					logger.debug("{}() >>> (void)", methodName);
				} else {
					logger.debug("{}() >>> {}", methodName, result);
				}
				return result;
			} catch (final Throwable e) {
				final var sb = new StringBuilder();
				for (int i = 0; i < e.getStackTrace().length; i++) {
					final var stackTraceElement = e.getStackTrace()[i];
					sb.append("\n\tat ").append(stackTraceElement);
					if (stackTraceElement.getClassName().equals(method.getMethod().getDeclaringClass().getName())
							&& stackTraceElement.getMethodName().equals(methodName)) {
						if (i < e.getStackTrace().length - 1) {
							sb.append("\n\t...");
						}
						break;
					}
				}

				logger.warn("{}() !!! {}: {}{}", methodName, e.getClass().getName(), e.getMessage(), sb.toString());
				throw e;
			}

		} else {
			return jp.proceed();
		}
	}

}
