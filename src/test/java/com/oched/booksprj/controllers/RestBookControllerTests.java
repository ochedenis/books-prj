package com.oched.booksprj.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oched.booksprj.requests.NewBookRequest;
import com.oched.booksprj.responses.BookInfoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@Sql({"/some_test_data.sql", "/some_test_data2.sql"})
@Transactional
public class RestBookControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("/some_test_data.sql")
    void getAllBooksAndAddNewBookTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                get("/book-rest/list")
        ).andExpect(status().isOk()).andReturn();

        BookInfoResponse[] response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                BookInfoResponse[].class
        );

        assertEquals(0, response.length);

        NewBookRequest request = new NewBookRequest(
                "Some title",
                1992,
                "Some first name",
                "Some second name",
                "12"
        );

        mockMvc.perform(post("/book-rest/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest());

        request.setContent("New content!");

        mockMvc.perform(post("/book-rest/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());

        mvcResult = mockMvc.perform(
                get("/book-rest/list")
        ).andExpect(status().isOk()).andReturn();
        response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                BookInfoResponse[].class
        );

        assertEquals(1, response.length);
        assertEquals(request.getTitle(), response[0].getTitle());
        assertEquals(request.getYear(), response[0].getYear());
    }
}
