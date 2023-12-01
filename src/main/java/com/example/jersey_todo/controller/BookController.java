package com.example.jersey_todo.controller;

import com.example.jersey_todo.mapper.BookMapper;
import com.example.jersey_todo.payload.BookDto;
import com.example.jersey_todo.payload.PaginationDto;
import com.example.jersey_todo.tables.Book;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static com.example.jersey_todo.config.MyBatisConfig.getSqlSessionFactory;
import static com.example.jersey_todo.utills.StaticVariables.*;

@Path("/books")
public class BookController {

    @GET
    public String hello() {
        return "salom";
    }

    @GET
    @Path("/get/{id}")
    public Response getById(@PathParam("id") Long id) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            Book result = bookMapper.getById(id);
            if (result != null)
                return Response.status(Response.Status.OK).entity(result).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/getAll")
    public Response getAll() {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            List<Book> results = bookMapper.getAll();
            if (results.size() != 0)
                return Response.status(Response.Status.OK).entity(results).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/addBook")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newBook(BookDto book) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            bookMapper.saveBook(book);
            session.commit();
            return Response.status(Response.Status.OK).entity("Success saved").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/editBook")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editBook(BookDto book) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            bookMapper.updateBook(book);
            session.commit();
            return Response.status(Response.Status.OK).entity("Success update").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteBook(@PathParam("id") Long id) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            bookMapper.deleteBook(id);
            session.commit();
            return Response.status(Response.Status.OK).entity("Success delete").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/page/{number}")
    public Response pagination(@PathParam("number") int pageNumber) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            List<Book> books = bookMapper.pagination(pageNumber);
            PaginationDto paginationDto = new PaginationDto();
            paginationDto.setCurrent_page(pageNumber);
            paginationDto.setPages_count(bookMapper.allPagesCount());
            paginationDto.setObj(books);
            if (books.size() != 0)
                return Response.status(Response.Status.OK).entity(paginationDto).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/search")
    public Response search(@QueryParam("q") String query, @QueryParam("pageNumber") int pageNumber) {
        try (SqlSession session = getSqlSessionFactory().openSession()) {
            BookMapper bookMapper = session.getMapper(BookMapper.class);
            List<Book> books = bookMapper.search(query, pageNumber);
            PaginationDto paginationDto = new PaginationDto();
            paginationDto.setCurrent_page(pageNumber);
            paginationDto.setPages_count(bookMapper.searchPagesCount(query));
            paginationDto.setObj(books);
            if (books.size() != 0)
                return Response.status(Response.Status.OK).entity(paginationDto).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
