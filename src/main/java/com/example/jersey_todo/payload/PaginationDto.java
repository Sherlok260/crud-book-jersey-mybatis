package com.example.jersey_todo.payload;

import com.example.jersey_todo.utills.StaticVariables;

import static com.example.jersey_todo.utills.StaticVariables.*;

public class PaginationDto {
    private int current_page = 1;
    private int pages_count;
    private int default_page_size = pageSize;
    private Object obj;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getPages_count() {
        return pages_count;
    }

    public void setPages_count(int pages_count) {
        this.pages_count = pages_count;
    }

    public int getPage_size() {
        return default_page_size;
    }

    public void setPage_size(int page_size) {
        this.default_page_size = page_size;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
