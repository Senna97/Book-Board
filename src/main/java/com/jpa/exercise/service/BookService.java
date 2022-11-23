package com.jpa.exercise.service;

import com.jpa.exercise.domain.Author;
import com.jpa.exercise.domain.Book;
import com.jpa.exercise.domain.dto.BookResponse;
import com.jpa.exercise.repository.AuthorRepository;
import com.jpa.exercise.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookResponse> findBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        List<BookResponse> bookResponses = books.stream()
                .map(book -> {
                    Optional<Author> optionalAuthor = authorRepository.findById(book.getAuthorId());
                    return BookResponse.of(book, optionalAuthor.get().getName());
                }).collect(Collectors.toList());
        return bookResponses;
    }
}
