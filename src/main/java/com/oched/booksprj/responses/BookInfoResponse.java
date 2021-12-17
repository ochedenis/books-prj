package com.oched.booksprj.responses;

import lombok.ToString;
import lombok.Value;

@Value
@ToString
public class BookInfoResponse {
    long id;
    String title;
    int year;
    String authorFirstName;
    String authorLastName;
}
