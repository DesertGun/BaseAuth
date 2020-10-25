package de.joayahiatene.baseauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class BaseauthApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BaseauthApplication.class, args);
    }

}
