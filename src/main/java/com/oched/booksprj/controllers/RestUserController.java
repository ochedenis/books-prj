package com.oched.booksprj.controllers;

import com.oched.booksprj.requests.NewUserRequest;
import com.oched.booksprj.requests.UpdateUserRequest;
import com.oched.booksprj.responses.UserInfoResponse;
import com.oched.booksprj.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-rest")
public class RestUserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<UserInfoResponse[]> getUsersList() {
        return new ResponseEntity<>(
                this.userService.getUsersList().toArray(new UserInfoResponse[0]),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addNewUser(
            final @Valid @RequestBody NewUserRequest request
    ) {
        this.userService.addNewUser(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<UserInfoResponse> updateUser(
            final @Valid @RequestBody UpdateUserRequest request
    ) {
        return new ResponseEntity<>(this.userService.updateUser(request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserInfoResponse[]> deleteUser(final @PathVariable Long id) {
        this.userService.deleteUser(id);

        return new ResponseEntity<>(
                this.userService.getUsersList().toArray(new UserInfoResponse[0]),
                HttpStatus.OK
        );
    }
}
