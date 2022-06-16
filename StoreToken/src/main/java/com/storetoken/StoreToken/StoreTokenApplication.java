package com.storetoken.StoreToken;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class StoreTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreTokenApplication.class, args);
	}
	
	@EnableWebFluxSecurity
	public class HelloWebfluxSecurityConfig {

		
		 @Bean
		    public MapReactiveUserDetailsService userDetailsService() {
			 PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				UserDetails user = User.withUsername("user1")
						.password(encoder.encode("password"))
						.roles("USER")
						.build();
		        return new MapReactiveUserDetailsService(user);
		    }
	}
	
	@Bean
	public RouterFunction<ServerResponse> routes() {
		return route(GET("/storeToken"),r -> ServerResponse.ok().bodyValue(String.format("Hello from instance %s", "Almacenando Token")));
	}

}
