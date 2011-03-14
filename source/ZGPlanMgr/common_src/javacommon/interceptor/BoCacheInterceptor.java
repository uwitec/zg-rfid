package javacommon.interceptor;

import javacommon.base.service.IVmModelBo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import com.boco.frame.cache.ICacheService;

public class BoCacheInterceptor {
	
	@Autowired
	private ICacheService cacheService;
	
	public void before(JoinPoint joinPoint) throws Throwable {
		String methodName = joinPoint.getSignature().getName();
		Object args[] = joinPoint.getArgs();
		String key=methodName;
		for(Object obj:args){
			key+=obj.toString();
		}
		System.out.println("执行前"+key);
	}
	
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object args[] = joinPoint.getArgs();
		String key=joinPoint.getSignature().getDeclaringType().getName()+"."+joinPoint.getSignature().getName()+":";
		for(Object obj:args){
			if(obj != null)
				key+=obj.toString();
		}
		if(cacheService.getValue(key)!=null){
			return cacheService.getValue(key);
		}else{
			Object obj = joinPoint.proceed();
			cacheService.putElement(key, obj);
			return obj;
		}
	}
	
	public void after(JoinPoint joinPoint)throws Throwable {
		//数据变化,清除缓存数据
		Object[] args = joinPoint.getArgs();
		if(args.length>1){
			Object obj = args[0];
			if(obj!=null){
				if(obj instanceof IVmModelBo ){
					
				}
			}
		}
		String methodName = joinPoint.getSignature().getName();
		
	}
}
