package com.oched.booksprj.services;

import com.oched.booksprj.entities.UserEntity;
import com.oched.booksprj.enumerations.UserRole;
import com.oched.booksprj.exceptions.BadRequestException;
import com.oched.booksprj.repositories.UserRepository;
import com.oched.booksprj.requests.NewUserRequest;
import com.oched.booksprj.requests.UpdateUserRequest;
import com.oched.booksprj.responses.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserInfoResponse addNewUser(NewUserRequest request) {
        Set<UserRole> roleList = new HashSet<>();
        roleList.add(UserRole.ROLE_USER);

        if(request.getRole().equals("ADMIN")) {
            roleList.add(UserRole.ROLE_ADMIN);
        }

        this.userRepository.save(new UserEntity(
                request.getLogin(),
                request.getEmail(),
                this.passwordEncoder.encode(request.getPassword()),
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

    public UserInfoResponse updateUser(UpdateUserRequest request) {
        Optional<UserEntity> optional = this.userRepository.findById(request.getId());

        UserEntity user = optional.orElseThrow(
                () -> new BadRequestException("No user with such id!", HttpStatus.BAD_REQUEST)
        );

        user.setLogin(request.getLogin());
        user.setEmail(request.getEmail());
        user.setPassword(
                this.passwordEncoder.encode(request.getPassword())
        );
        user.getRoles().add(request.getRole());

        this.userRepository.save(user);

        return new UserInfoResponse(
                user.getLogin(),
                user.getEmail()
        );
    }


    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }
}
