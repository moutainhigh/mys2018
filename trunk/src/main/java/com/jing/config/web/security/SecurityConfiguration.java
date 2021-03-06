package com.jing.config.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jing.system.login.LoginFailureHandler;
import com.jing.system.login.LoginSuccessHandler;
import com.jing.utils.SpringContextHolder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	public SpringContextHolder springContextHolder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 拦截请求
				.antMatchers("/home.html").permitAll() // 访问白名单
				.anyRequest().authenticated().and()// 其他请求都要过滤
				.formLogin().loginPage("/pc/login.html").successHandler(loginSuccessHandler()).permitAll().and() // 登录
				.logout().invalidateHttpSession(true).logoutSuccessUrl("/logoutSuccess").permitAll().and()// 注销
				.headers().frameOptions().sameOrigin().and()
				.csrf().disable(); // 默认开启,我们关闭
		http.addFilterBefore(requestHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(springSecurityFilterPermission(), FilterSecurityInterceptor.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截规则
		web.ignoring().antMatchers("/pc/**","**/syswhite/**","/swagger-resources/**","/swagger/**","/v2/**", "/**/images/**", "/**/css/**", "**/js/**", "/**/checkcode","/loginSuccess","/logoutSuccess");
	}

	public PermissionSecurityFilter springSecurityFilterPermission() {
		PermissionSecurityFilter filter = new PermissionSecurityFilter();
		filter.setAuthenticationManager(authenticationManager());
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		decisionVoters.add(new RoleVoter());
		AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);
		filter.setAccessDecisionManager(accessDecisionManager);
		filter.setSecurityMetadataSource(new PermissionSecurityMetadataSource());
		filter.setDefaultURL("/pc/home.html");
		filter.setErrorPage("/error");
		filter.setLogoutURL("/logout");
		return filter;
	}

	public RequestHeaderProcessingFilter requestHeaderFilter() {
		RequestHeaderProcessingFilter requestHeaderFilter = new RequestHeaderProcessingFilter(
				"/j_spring_security_check");
		requestHeaderFilter.setLoginFailureHandler(loginFailureHandler());
		requestHeaderFilter.setLoginSuccessHandler(loginSuccessHandler());
		requestHeaderFilter.setAuthenticationManager(authenticationManager());
		requestHeaderFilter.setLogoutURL("/logout");
		requestHeaderFilter.setLoginFailPageURL("/logout");
		requestHeaderFilter.setLoginPageURL("/pc/login.html");
		requestHeaderFilter.afterPropertiesSet();
		return requestHeaderFilter;
	}

	public AuthenticationManager authenticationManager() {
		DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher = new DefaultAuthenticationEventPublisher();
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(permissionUserDetailsAuthenticationProvider());
		ProviderManager manager = new ProviderManager(providers);
		manager.setAuthenticationEventPublisher(defaultAuthenticationEventPublisher);
		return manager;
	}

	public AuthenticationProvider permissionUserDetailsAuthenticationProvider() {
		return new PermissionUserDetailsAuthenticationProvider();
	}

	public LoginSuccessHandler loginSuccessHandler() {
		LoginSuccessHandler s= new LoginSuccessHandler();
		s.setSuccessUrl("/loginSuccess");
		return s;
	}

	public LoginFailureHandler loginFailureHandler() {
		LoginFailureHandler f = new LoginFailureHandler();
		f.setDefaultFailureUrl("/login");
		return f;
	}

}