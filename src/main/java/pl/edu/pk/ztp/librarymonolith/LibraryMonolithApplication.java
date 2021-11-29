package pl.edu.pk.ztp.librarymonolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pk.ztp.librarymonolith.controller.UserController;
import pl.edu.pk.ztp.librarymonolith.dto.UserDTO;
import pl.edu.pk.ztp.librarymonolith.service.UserService;
import pl.edu.pk.ztp.librarymonolith.model.Role;
import pl.edu.pk.ztp.librarymonolith.repository.UserRepository;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@SpringBootApplication(scanBasePackageClasses = {UserRepository.class, UserDTO.class, UserService.class, Role.class, UserController.class})
@EnableTransactionManagement
@EnableSwagger2
public class LibraryMonolithApplication {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build().apiInfo(createApiInfo());
	}

	public static void main(String[] args) {
		SpringApplication.run(LibraryMonolithApplication.class, args);

	}

	private ApiInfo createApiInfo() {
		List<VendorExtension> vendorExtension = Collections.emptyList();
		return new ApiInfo(
				"Book Repository API",
				"This API allows to find users registered in library, find available books and rent or return them",
				"1.0",
				"https://www.epam.com",
				new Contact("Grzegorz Konopka", "https://www.github.com/konopkagrzegorz","konopkagrzeg@gmail.com"),
				"Not described",
				"Not defined",
				vendorExtension);
	}

}
