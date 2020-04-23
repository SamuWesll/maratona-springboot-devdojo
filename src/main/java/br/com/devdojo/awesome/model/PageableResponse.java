package br.com.devdojo.awesome.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PageableResponse<t> extends PageImpl<t> {

//    public PageableResponse(@JsonProperty("content") List<t> content,
//                            @JsonProperty("number") int page,
//                            @JsonProperty("size") int size,
//                            @JsonProperty("totalElements") long totalElements,
//                            @JsonProperty("sort") Sort sort) {
//        super(content, new PageRequest(page, size, sort,), totalElements);
//    }

    public PageableResponse() {
        super(new ArrayList<>());
    }
}
