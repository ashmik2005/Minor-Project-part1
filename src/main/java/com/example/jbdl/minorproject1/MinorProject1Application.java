package com.example.jbdl.minorproject1;

import com.example.jbdl.minorproject1.security.User;
import com.example.jbdl.minorproject1.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MinorProject1Application implements CommandLineRunner {

	@Value("${user.authority.admin}")
	private String ADMIN_AUTHORITY;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
//		User user = User.builder()
//				.username("admin@gmail.com")
//				.password(passwordEncoder.encode("admin1234"))
//				.authority(ADMIN_AUTHORITY)
//				.build();
//
//		userService.createUser(user);
	}

	public static void main(String[] args) {
		SpringApplication.run(MinorProject1Application.class, args);
	}

}
