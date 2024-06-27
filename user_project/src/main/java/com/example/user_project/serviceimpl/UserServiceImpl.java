package com.example.user_project.serviceimpl;

import com.example.user_project.model.User;
import com.example.user_project.model.request.UserRequest;
import com.example.user_project.repository.UserRepository;
import com.example.user_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Object saveOrUpdate(UserRequest userRequest) {
   //updating user
   if (userRepository.existsById(userRequest.getUserId()))
   {

       User user  = userRepository.findById(userRequest.getUserId()).get();


       user.setUserId(userRequest.getUserId());
       if (isUserNameValid(userRequest.getUserName())) {
           user.setUserName(userRequest.getUserName());
       }
       else
       {
           return "username must contain only alphabets and '_' ";
       }
       user.setFirstName(userRequest.getFirstName());
       user.setLastName(userRequest.getLastName());
       if (userRepository.existsByEmail(userRequest.getEmail()))
       {
          return "email already exists";
       }
       else {
           user.setEmail(userRequest.getEmail());
       }
       if (isPasswordValid(userRequest.getPassword())) {
           user.setPassword(userRequest.getPassword());
       }
       else
       {

        return "password must be 5 to 15 units long and contain a digit,an alphabet and a special character";
       }
       user.setCity(userRequest.getCity());
       if (isContactValid(userRequest.getContact())) {
           user.setContact(userRequest.getContact());
       }
       else
       {

        return "contact must be of 10 digits";
       }
       user.setIsActive(true);
       user.setIsDeleted(false);
       userRepository.save(user);
       return "user updated";
   }
   //saving user starts here
   else
   {
       User user = new User();

       user.setUserId(userRequest.getUserId());
       if (isUserNameValid(userRequest.getUserName())) {
           user.setUserName(userRequest.getUserName());
       }
       else
       {
           return "username must contain only alphabets and '_' ";
       }
       user.setFirstName(userRequest.getFirstName());
       user.setLastName(userRequest.getLastName());
       if (userRepository.existsByEmail(userRequest.getEmail()))
       {
           return "email already exists";
       }
       else {
           user.setEmail(userRequest.getEmail());
       }
       if (isPasswordValid(userRequest.getPassword())) {
           user.setPassword(userRequest.getPassword());
       }
       else
       {

           return "password must be 5 to 15 units long and contain a digit,an alphabet and a special character";
       }
       user.setCity(userRequest.getCity());
       if (isContactValid(userRequest.getContact())) {
           user.setContact(userRequest.getContact());
       }
       else
       {

           return "contact must be of 10 digits";
       }

       user.setIsActive(true);
       user.setIsDeleted(false);
       userRepository.save(user);
       return "user saved";

   }


   //method ends here
    }

   // condition for userName => it must contain 'a-zA-Z_' only this characters

    public boolean isUserNameValid(String username)
    {
        String regex = "^[a-zA-Z_ ]+$";
        return username.matches(regex);

    //method ends here
    }

    //conditions for password => at least 10 units must contain a digit ,an alphabet upper or lower,
    //a special character.min 5 to max 10 chars
    public boolean isPasswordValid(String password)
    {
//       String regex = "^[a-zA-Z0-9$#@!^%*=+]{5,15}$";
//
//        return password.matches(regex);

        if (password == null || password.length()<5 ||password.length()>15)
        {
          return  false;
        }



        Boolean hasAlphabets = false;

        for (char ch='a';ch<='z';ch++)
        {
            for (int i=0;i<password.toCharArray().length;i++)
            {

                if (ch==password.toCharArray()[i])
                {

                  hasAlphabets = true;
                  break;

                }

            }


        }
        if(!hasAlphabets)
        {
            return false;
        }


        Boolean hasDigit = false;


            for (char c: password.toCharArray())
            {
                if (Character.isDigit(c)) {


                        hasDigit=true;
                        break;


                }


            }
           /* for (int j=0;j<password.toCharArray().length;j++)
            {


                if (i==password.toCharArray()[j])
                {

                    hasDigit=true;
                    break;

                }


            }*/




        if (!hasDigit)
        {

            return false;

        }


      /*  String regex = "^[a-zA-Z0-9!@#$%^&*+=?]+$";

       boolean value= password.matches(regex);


        if (!value)
        {

            return false;
        }*/

        Boolean hasSpecialCharacter = false;
        String special = "!@#$%^&*()_+?";
        for (char c: special.toCharArray())
        {

            boolean s = password.contains(String.valueOf(c));
            if (s)
            {

                hasSpecialCharacter = true;
                break;

            }
            System.out.println(s);
        }

        if (!hasSpecialCharacter)
        {
            return false;
        }

        return true;
      //isPasswordValid() method ends here
    }


    //condition for contact => it must be of 10 digits

    public boolean isContactValid(String contact)
    {
      String regex = "^[0-9]{10}$";

      return contact.matches(regex);
    }

  //  class ends here
}
