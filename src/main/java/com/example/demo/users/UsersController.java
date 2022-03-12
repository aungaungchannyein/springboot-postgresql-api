package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(path="api/user/view",method = RequestMethod.GET)
    public List<Users> getUsers(){
       return usersService.getUsers();
    }

    @RequestMapping(path="api/user/add",method = RequestMethod.POST)
    public void registerNewUsers(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String dob){
       usersService.addNewUser(name,email,dob);
    }

    @RequestMapping(path="api/user/delete/{userId}",method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("userId") Long id){
        usersService.deleteUser(id);
    }

    @RequestMapping(path="api/user/update/{userId}",method = RequestMethod.PUT)
    public void updateStudent(
            @PathVariable("userId") Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String dob){
        usersService.updateUser(id, name, email, dob);
    }


}
