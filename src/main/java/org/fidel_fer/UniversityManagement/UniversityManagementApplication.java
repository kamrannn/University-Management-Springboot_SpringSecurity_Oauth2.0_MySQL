package org.fidel_fer.UniversityManagement;

import org.fidel_fer.UniversityManagement.model.User;
import org.fidel_fer.UniversityManagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class UniversityManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityManagementApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        return args -> {
            User user = new User();
            user.setUsername("admin");
            user.setFullName("Admin Name");
            user.setPassword(bCryptPasswordEncoder.encode("admin"));
            userRepository.save(user);
        };
    }
}
