package org.generation.itemsApi.service;

import org.generation.itemsApi.Controller.dto.ResponseDto;
import org.generation.itemsApi.Controller.dto.SignInDto;
import org.generation.itemsApi.Controller.dto.user.SignInResponseDto;
import org.generation.itemsApi.Controller.dto.user.SignUpDto;
import org.generation.itemsApi.entity.AuthenticationToken;
import org.generation.itemsApi.entity.User;
import org.generation.itemsApi.exceptions.AuthenticationFailException;
import org.generation.itemsApi.exceptions.CustomException;
import org.generation.itemsApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Transactional
    public ResponseDto signup(SignUpDto signUpDto) {
        //check if the user is present
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            try {
                throw new CustomException("user already registered");
            } catch (CustomException e) {
                throw new RuntimeException(e);
            }
        }
        //hash password
        String encryptedPassword = signUpDto.getPassword();
         try{
             encryptedPassword = hashPassword(signUpDto.getPassword());
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
        //save the user
        User user = new User(signUpDto.getFirstName(),signUpDto.getLastName(),signUpDto.getEmail(),encryptedPassword);
         userRepository.save(user);
        //create token
               final AuthenticationToken authenticationToken= new AuthenticationToken(user);
               authenticationService.saveConfirmationToken(authenticationToken);


        ResponseDto responseDto = new ResponseDto("success","response");
        return responseDto;
    }
   //hash the password
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        //find user by email
          User user = userRepository.findByEmail(signInDto.getEmail());
          if(Objects.isNull(user)){
              throw new AuthenticationFailException("user is not found");
          }
          //hash the password
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("password is not correct");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //if the password match
        AuthenticationToken  token = authenticationService.getToken(user);
        //retrieve token
        if(Objects.isNull(token)){
            try {
                throw new CustomException("Token not found");
            } catch (CustomException e) {
                e.printStackTrace();
            }
        }
        return new SignInResponseDto("sucess",token.getToken());
    }

}
