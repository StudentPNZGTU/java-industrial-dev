package org.penzgtu.Application;

import org.penzgtu.Application.menu.auth.AuthenticationMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final AuthenticationMenu authenticationMenu;

    @Autowired
    public Application(AuthenticationMenu authenticationMenu) {
        this.authenticationMenu = authenticationMenu;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        authenticationMenu.display();
    }
}