package com.maddy.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.maddy.user.entities.Hotel;
import com.maddy.user.entities.Rating;
import com.maddy.user.entities.User;
import com.maddy.user.exception.ResourceNotFoundException;
import com.maddy.user.repositories.UserRepository;
import com.maddy.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// String randomUserId = UUID.randomUUID().toString();
		// user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = userRepository.findAll();

//		String ratingURI = "http://localhost:8083/ratings/users";
//		List<Rating> ratingList = restTemplate.getForObject(ratingURI, ArrayList.class, userId);
//		logger.info("ratingList : {}", ratingList);
//		user.setRatingList(ratingList);

		return userList;
	}

	@Override
	public User getUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found with given ID :" + userId));
		//String ratingURI = "http://localhost:8083/ratings/users/" + userId;
		String ratingURI = "http://RATING-SERVICE/ratings/users/" + userId;
		Rating[] ratingArray = restTemplate.getForObject(ratingURI, Rating[].class);
		List<Rating> ratingList = Arrays.asList(ratingArray);
		logger.info("ratingList : {}", ratingList);

		List<Rating> ratingListWithHotel = ratingList.stream().map(rating -> {
			Integer hotelId = rating.getHotelId();
			//String hotelURI = "http://localhost:8082/hotels/" + hotelId;
			String hotelURI = "http://HOTEL-SERVICE/hotels/" + hotelId;
			Hotel hotel = restTemplate.getForObject(hotelURI, Hotel.class);
			rating.setHotel(hotel);
			return rating;
		}).toList();
		logger.info("ratingListWithHotel : {}", ratingListWithHotel);
		user.setRatingList(ratingListWithHotel);

		return user;
	}

}
