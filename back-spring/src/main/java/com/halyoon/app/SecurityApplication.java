package com.halyoon.app;

import com.halyoon.app.stay.Stay;
import com.halyoon.app.stay.StayRepository;
import com.halyoon.app.stay.media.StayImages;
import com.halyoon.app.user.Role;
import com.halyoon.app.user.User;
import com.halyoon.app.user.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = "com.halyoon.app")
public class SecurityApplication {

	@Autowired
	private static UserRespository userRepository;
	@Autowired
	private static StayRepository stayRepository;

	public SecurityApplication(UserRespository userRepository ,StayRepository stayRepository) {
		this.userRepository = userRepository;
		this.stayRepository = stayRepository;

	}

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);

		// Create an instance of SecurityApplication with the UserRepository dependency
		SecurityApplication securityApplication = new SecurityApplication(userRepository ,stayRepository);

		// Create and save a user
		User user = User.builder()
				.firstname("John")
				.lastname("Doe")
				.email("john.doe@example.com")
				.password("password123")
				.mfaEnabled(true)
				.role(Role.USER)
				.secret("secret123")
				.imgUrl("profile.jpg")
				.isSuperhost(false)
				.location("City")
				.about("A bit about me.")
				.responseTime("Quick")
				.createdAt(new Date())
				.build();

		var savedUser = securityApplication.userRepository.save(user);

		// Rest of your code for creating a Stay instance
		StayImages image1 = StayImages.builder()
				.imageUrl("https://example.com/image1.jpg")
				.build();

		StayImages image2 = StayImages.builder()
				.imageUrl("https://example.com/image2.jpg")
				.build();

		Stay stayWithoutReviews = Stay.builder()
				.id(2)
				.bedrooms(2)
				.bathrooms(1)
				.name("Cozy Cottage")
				.type("Entire House")
				.price(100.00)
				.summary("A cozy cottage with a beautiful view.")
				.capacity("4 guests")
				.user(savedUser)
				.images(List.of(image1,image2)) // Add the list of images
				.amenities(null)
				.location(null)
				.reviews(null)
				.build();
		securityApplication.stayRepository.save(stayWithoutReviews);

	}
}
