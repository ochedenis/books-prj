package com.oched.booksprj.responses;

import lombok.Value;

@Value
public class BookInfoResponse {
    long id;
    String title;
    int year;
    String authorFirstName;
    String authorLastName;
}
