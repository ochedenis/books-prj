package com.oched.booksprj.responses;

import lombok.ToString;
import lombok.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Value
@ToString
public class BookInfoResponse {
    long id;
    String title;
    int year;
    String authorFirstName;
    String authorLastName;
}
