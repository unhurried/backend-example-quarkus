package io.github.unhurried.example.backend.quarkus.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import io.quarkus.logging.Log;

@Logged 
@Interceptor 
public class LoggingInterceptor {

   @AroundInvoke 
   Object logInvocation(InvocationContext context) throws Exception {
      String ctx = context.getTarget().getClass().getName() + "#" + context.getMethod().getName();
      Log.info(ctx + ": start");
      Object ret = context.proceed(); 
      Log.info(ctx + ": end");
      return ret;
   }

}
