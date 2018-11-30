package ca.concordia.soen6841.gateway.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity 	// Enable ca.concordia.soen6841.gateway.security config. This annotation denotes config for spring ca.concordia.soen6841.gateway.security.
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // make sure we use stateless session; session won't be used to store user's state.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // handle an authorized attempts
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                // Add a filter to validate the tokens with every request
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class)
                // authorization requests config
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/**").permitAll()
                .antMatchers(HttpMethod.POST, "/position/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/position/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/position/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/invoice/**").hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/job/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/job/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/job/**").hasRole("ADMIN")
                .antMatchers("/employee/**").hasRole("ADMIN")
                // Any other request is allowed
                .anyRequest().permitAll();
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}