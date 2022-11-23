package paulohenrique.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("Restful API with Java 17 and Spring boot 3")
						.version("v1")
						.description("Base API to Register and manager person")
						.termsOfService("https://github.com/paulohenriqueb")
						.license( 
								new License()
									.name("Apache 2.0")
									.url("https://github.com/paulohenriqueb")
								)
						);
	}
}
