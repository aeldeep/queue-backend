package com.eldeep.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eldeep.annotations.Auth;
import com.eldeep.exceptions.UnauthorizedException;
import com.eldeep.users.UsersModel;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

@Aspect
@Component
public class AuthAspect {
	@Around("execution(* com.eldeep.controllers..*(..)) && @annotation(auth)")
	public Object authenticateUser(ProceedingJoinPoint pjp, Auth auth) throws Throwable
	{
		System.out.println("Is Advice Live?");
		UsersModel u = getLoggedInUser();
		if (u== null)
		{
			throw new UnauthorizedException();
		}else {
			for (String role : auth.roles())
			{
				if (u.getRole().equals(role))
				{
					return pjp.proceed();
				}
			}
			throw new UnauthorizedException();
		}
		
	}
	
	public static UsersModel getLoggedInUser()
	{
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return (UsersModel) req.getSession().getAttribute("user");
	}
}
