# first-project
Gym Membership Management

Imagined Project Requirement:
    An application for a gym that offers contact sports memberships, which keeps track of its Classes, Instructors and Registered Members 

Technologies used: Java, Spring Boot, Maven, Hibernate, MySQL, Thymeleaf
    
    
Functionalities Implemented (so far)
    
    •Members Operations
    
      -  creating(registering) a member (checking uniqueness of email and phone)
      -  retrieving(finding) members (by specifc characters in their name and/or email address)
      -  listing of all members (by their last name)
      -  updating member information (checking uniqueness of email and phone)
      -  registering for new classes (checking for uniqueness of class registration)
      -  removing of class registrations
      -  renewing expired class registrations
      -  writing of reviews for classes registered for (even if membership expired)
      -  show active/inactive membership flag
      -  deleting a member
      
  •Classes Operations
  
      -  adding a new class
      -  listing of all classes
      -  access the class reviews for each class
      -  deleting the class (with subsequent deletion of all class reviews and class memberships)
      
  •Instructors Operations
  
      -  listing of all instructors
      -  deleting an instructor (with subsequent deletion of all classes and reviews of classes that the instructor teaches, and of all registered class memberships)
      -  assigning an instructor to a new class
