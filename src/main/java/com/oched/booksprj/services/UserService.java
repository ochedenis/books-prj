package com.oched.booksprj.services;

import com.oched.booksprj.entities.UserEntity;
import com.oched.booksprj.enumerations.UserRole;
import com.oched.booksprj.repositories.UserRepository;
import com.oched.booksprj.requests.NewUserRequest;
import com.oched.booksprj.responses.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserInfoResponse addNewUser(NewUserRequest request) {
        List<UserRole> roleList = new ArrayList<>();
        roleList.add(UserRole.USER);

        if(request.getRole().equals("ADMIN")) {
            roleList.add(UserRole.ADMIN);
        }

        this.userRepository.save(new UserEntity(
                request.getLogin(),
                request.getEmail(),
                request.getPassword(),
                roleList
        ));

        return new UserInfoResponse(
                request.getLogin(),
                request.getEmail()
        );
    }

    public List<UserInfoResponse> getUsersList() {
        List<UserEntity> list = this.userRepository.findAll();

        return list.stream().map(
                user -> new UserInfoResponse(
                        user.getLogin(),
                        user.getEmail()
                )
        ).collect(Collectors.toList());
    }
}
