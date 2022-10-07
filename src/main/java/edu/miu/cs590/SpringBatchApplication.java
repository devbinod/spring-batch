package edu.miu.cs590;

import edu.miu.cs590.entity.User;
import edu.miu.cs590.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class SpringBatchApplication implements CommandLineRunner{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin"))
                .build();

        createUser(user);
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
