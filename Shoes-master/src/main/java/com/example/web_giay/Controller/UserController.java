package com.example.web_giay.Controller;

import com.example.web_giay.dto.BaseResponse;
import com.example.web_giay.dto.ChangePasswordRequest;
import com.example.web_giay.dto.UserDTO;
import com.example.web_giay.entity.Users;
import com.example.web_giay.repository.UserRepository;
import com.example.web_giay.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse> signUp(@RequestBody @Validated UserDTO user){
        if(userRepository.findUsersByUsernameAndActive(user.getUsername(),true) != null ){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Username is already taken or Email is already taken!"));
        }else {
            Users savedUser = userService.signUp(user);
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), null, "Please check email register"));
        }
    }
    @GetMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestParam(value = "username") String username,@RequestParam(value = "pass") String password){
        Users users =userRepository.findUsersByUsernameAndActive(username,true);
        if(users!=null && users.getPassword().contains(password)){
            return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), null, "Sign in successful"));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null,"Not Found Account"));
        }
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<BaseResponse> verifyToken(@PathVariable(value = "token") String token){
        String activeMsg= userService.verifyToken(token);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),null,activeMsg));
    }
    @PutMapping("/update/{name}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "name") String name, @RequestBody ChangePasswordRequest passwordRequest){
        Users userSaved = userRepository.findUsersByUsernameAndActive(name,true);
        if(passwordRequest.getPassword().equals(userSaved.getPassword()) ){
            if(passwordRequest.getNewPassword().equals(passwordRequest.getConfirmNewPassword())){
                userSaved.setPassword(passwordRequest.getNewPassword());
                userRepository.save(userSaved);
            }
        }
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(), null, "Upadate complete "));
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody Long[] id){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),null,userService.deleteUser(id)));
    }

}
