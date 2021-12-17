package com.oched.booksprj.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActionRequest {
    @NotBlank
    private Long id;
}
