package com.tss.auth.config;

import com.tss.auth.entity.User;
import com.tss.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check if users already exist
        if (userRepository.count() > 0) {
            log.info("Users already exist in database. Skipping initialization.");
            return;
        }

        log.info("Initializing database with test users...");

        // Create user: harshad
        User harshad = new User();
        harshad.setUsername("harshad");
        harshad.setPassword(passwordEncoder.encode("harshad123"));
        harshad.setEmail("harshad@example.com");
        userRepository.save(harshad);
        log.info("Created user: harshad");

        // Create user: ashish
        User ashish = new User();
        ashish.setUsername("ashish");
        ashish.setPassword(passwordEncoder.encode("ashish123"));
        ashish.setEmail("ashish@example.com");
        userRepository.save(ashish);
        log.info("Created user: ashish");

        // Create user: mahek_panda
        User mahekPanda = new User();
        mahekPanda.setUsername("mahek_panda");
        mahekPanda.setPassword(passwordEncoder.encode("mahek123"));
        mahekPanda.setEmail("mahek.panda@example.com");
        userRepository.save(mahekPanda);
        log.info("Created user: mahek_panda");

        log.info("Database initialization completed successfully!");
        log.info("Test users created with default passwords:");
        log.info("  - harshad / harshad123");
        log.info("  - ashish / ashish123");
        log.info("  - mahek_panda / mahek123");
    }
}
