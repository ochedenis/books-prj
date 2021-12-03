package com.oched.booksprj.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookContentEntity {
    private long id;
    private BookDescriptionEntity description;
    public Map<String, String> content;
}
