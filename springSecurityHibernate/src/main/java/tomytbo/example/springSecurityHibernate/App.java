package tomytbo.example.springSecurityHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import tomytbo.example.springSecurityHibernate.user.User;
import tomytbo.example.springSecurityHibernate.user.UserRepository;

@SpringBootApplication
public class App implements CommandLineRunner {
    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //when run programing, insert to csdl 1 user
        User user = new User();
        user.setUsername("tabo");
        user.setPassword(passwordEncoder.encode("tabo"));
        userRepository.save(user);
        System.out.println(user);
    }
}