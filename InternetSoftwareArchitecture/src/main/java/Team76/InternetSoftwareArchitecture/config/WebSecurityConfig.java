package Team76.InternetSoftwareArchitecture.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import Team76.InternetSoftwareArchitecture.security.TokenUtils;
import Team76.InternetSoftwareArchitecture.security.auth.RestAuthenticationEntryPoint;
import Team76.InternetSoftwareArchitecture.security.auth.TokenAuthenticationFilter;
import Team76.InternetSoftwareArchitecture.service.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).and().authorizeRequests()
				.antMatchers("/auth/*", "/admin/registrationRequests", "/admin", "/admin/declineRegistrationRequest", "/admin/acceptRegistrationRequest", "/client/*", "/deleteAccount/*", "/cottage/*", "/cottage", "/reservationCottage/*", "/ship", "/ship/*", "/reservationShip/*", "/adventure", "/reservationAdventure/*", "/adventure/*" ,"/image/upload/*/*, /reservationAdditionalService/*").permitAll()
				.anyRequest().authenticated().and().httpBasic().and().cors().and()
				.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService),
						BasicAuthenticationFilter.class);
		http.cors();
		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.PUT, "/auth/confirm_account/*");
		web.ignoring().antMatchers(HttpMethod.POST, "/auth/login", "/auth/logout", "/auth/signupClient", "/auth/signupCottageOwner", "/auth/signupShipOwner", "/auth/signupFishingInstructor");
		web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
				"/**/*.css", "/**/*.js");
	}

}
