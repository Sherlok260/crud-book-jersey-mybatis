package com.example.jersey_todo.mapper;

import com.example.jersey_todo.payload.BookDto;
import com.example.jersey_todo.tables.Book;
import com.example.jersey_todo.utills.StaticVariables;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    Book getById(Long id);
    List<Book> getAll();
    void saveBook(BookDto book);
    void updateBook(BookDto book);
    void deleteBook(Long id);
    List<Book> pagination(int pageNumber);
    List<Book> search(@Param("query") String query, @Param("pageNumber") int pageNumber);
    int allPagesCount();
    int searchPagesCount(String query);

}
