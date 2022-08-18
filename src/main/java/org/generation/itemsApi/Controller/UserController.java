package org.generation.itemsApi.Controller;


import org.generation.itemsApi.Controller.dto.ResponseDto;
import org.generation.itemsApi.Controller.dto.SignInDto;
import org.generation.itemsApi.Controller.dto.user.SignInResponseDto;
import org.generation.itemsApi.Controller.dto.user.SignUpDto;
import org.generation.itemsApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

      @Autowired
    UserService userService;
    //sign UP API
     @PostMapping("/signup")
    public Object ResponseDto (@RequestBody SignUpDto signUpDto){
       return userService.signup(signUpDto);
    }
    //SIGN IN API
    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
     return userService.signIn(signInDto);
    }

}
