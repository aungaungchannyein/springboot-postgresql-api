package com.example.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

    public void addNewUser(String name, String email, String dob) {
        Optional<Users> studentByEmail = usersRepository.findUserByEmail(email);

        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        Users newUser = new Users(
                name,
                email,
                dob
        );
        usersRepository.saveAll(List.of(newUser));
    }


    public void deleteUser(Long userId){
        boolean exits= usersRepository.existsById(userId);
        if (!exits){
            throw new IllegalStateException(
                    "User with id "+ userId +"does not exits"
            );

        }
        usersRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,String name,String email, String dob){
        Users user= usersRepository.findById(userId)
                .orElseThrow(()-> new IllegalStateException(
                "User with id "+ userId+ "does not exit"
        ));

        if(name!= null && name.length()>0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }

        if(email!= null && email.length()>0 && !Objects.equals(user.getEmail(),email)){
            user.setEmail(email);
        }

        if(dob!= null && !Objects.equals(user.getDob(),dob)){
            user.setDob(dob);
        }

    }


}
