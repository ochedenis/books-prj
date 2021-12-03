package com.oched.booksprj.responses;

import lombok.Value;

@Value
public class BookResponse {
    String title;
    int year;
    String authorFirstName;
    String authorLastName;
}
