package com.userregistration.service.updation;

import com.userregistration.exception.GeneralValidationException;
import com.userregistration.exception.InvalidEmailException;
import com.userregistration.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserUpdation {
    private final MongoTemplate mongoTemplate;

    public UserUpdation(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }


    //User update.
    public User updateUser(String email,User userUpdate){
        // Find the user by email
        Query query = new Query(Criteria.where("email").is(email));
        User existingUser = mongoTemplate.findOne(query, User.class);
        if (existingUser == null) {
            throw new InvalidEmailException("User with the provided email does not exist");
        }

        // Update fields selectively
        Update update = new Update();
        if (userUpdate.getFirstName() != null) update.set("firstName", userUpdate.getFirstName());
        if (userUpdate.getLastName() != null) update.set("lastName", userUpdate.getLastName());
        if (userUpdate.getCountryCode() != null) update.set("countryCode", userUpdate.getCountryCode());
        if (userUpdate.getMobileNumber() != null) update.set("mobileNumber", userUpdate.getMobileNumber());
        if (userUpdate.getDateOfBirth() != null) update.set("dateOfBirth", userUpdate.getDateOfBirth());
        if (userUpdate.getAddress() != null) update.set("address", userUpdate.getAddress());
        if (userUpdate.getCity() != null) update.set("city", userUpdate.getCity());
        if (userUpdate.getState() != null) update.set("state", userUpdate.getState());
        if (userUpdate.getCountry() != null) update.set("country", userUpdate.getCountry());
        if (userUpdate.getActive() != null) update.set("active", userUpdate.getActive());
        if (userUpdate.getPassword() != null) update.set("password", userUpdate.getPassword());

        mongoTemplate.updateFirst(query, update, User.class);

        // Return the updated user
        return mongoTemplate.findOne(query, User.class);
    }
}
