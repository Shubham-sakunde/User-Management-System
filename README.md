# User Management System

A comprehensive user management system built with Spring Boot and MongoDB, featuring functionalities for user registration, login, update, delete, and retrieval. The system is designed with RESTful APIs and includes robust validation and exception handling mechanisms.

## Features
 
- **User Registration**: Allows new users to register with validated input fields.
- **User Login**: Secure user login with validation and authentication. 
- **Update User**: Update user details without changing the unique email identifier.
- **Delete User**: Deactivate user accounts without removing the data from the database. 
- **Get Users List**: Retrieve a list of all registered users.
- **Get User by Email**: Fetch user details based on the unique email identifier.
- **Validation**: All API requests are validated to ensure data integrity.
- **Exception Handling**: Centralized and consistent exception handling using Spring Boot. 

## Technologies Used

- **Spring Boot**: For building the RESTful APIs.
- **MongoDB**: As the NoSQL database for storing user data.
- **MongoTemplates**: For database operations and queries.
- **Spring Validation**: For validating user inputs during registration and login.
- **Spring Boot Exception Handling**: For handling exceptions across the application.

## Project Structure

- `src/main/java/com/yourcompany/usermanagement`: Contains the Java source code.
  - `controller`: REST controllers for handling API requests.
  - `service`: Service classes containing business logic.
  - `repository`: Repository interfaces for MongoDB operations.
  - `model`: POJOs representing the user data.
  - `exception`: Custom exception classes and global exception handler.
  - `validation`: Custom validators for user input validation.
- `src/main/resources`: Configuration files and resources.
  - `application.properties`: Application configuration including MongoDB connection details.
- `pom.xml`: Maven configuration file for dependencies.

## API Endpoints
- `Register User`: POST /api/users/register.
- `Login User`: POST /api/users/login.
- `Update User`: PUT /api/users/update.
- `Delete User`: DELETE /api/users/delete/{email}.
- `Get Users List`: GET /api/users/list.
- `Get User by Email`: GET /api/users/{email}.


