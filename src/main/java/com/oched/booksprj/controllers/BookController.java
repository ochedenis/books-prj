package com.oched.booksprj.controllers;

import com.oched.booksprj.requests.BookRequest;
import com.oched.booksprj.requests.DeleteBookRequest;
import com.oched.booksprj.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/add")
    private String getAddBookPage() {
        return "/books/addBook";
    }

    @PostMapping(value = "/add")
    public String addNewBook(final @ModelAttribute("request") BookRequest request) {
        this.bookService.addBook(request);

        return "redirect:/books/all";
    }

    @GetMapping(value = "/edit")
    public ModelAndView getEditBookPage(ModelAndView modelAndView, @PathVariable Long id) {
        modelAndView.addObject("book", this.bookService.getById(id));
        modelAndView.setViewName("/books/editBook");
        return modelAndView;
    }

    @PutMapping(value = "/edit")
    public String updateBook(final @ModelAttribute("request") BookRequest request) {
        this.bookService.editBook(request);
        return "redirect:/books/all";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        System.out.println("============================");
        System.out.println(id);
        System.out.println("============================");

        this.bookService.deleteBook(id);
        return "redirect:/books/all";
    }


    @GetMapping("/all")
    public ModelAndView getAllBooks(final ModelAndView modelAndView) {
        modelAndView.addObject("list", this.bookService.getAll());
        modelAndView.setViewName("/books/allBooks");

        return modelAndView;
    }
}
