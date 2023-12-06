package com.operations.booking.util;


import com.operations.booking.dto.PageableQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//sinifinin asas məqsədi, Spring Data JPA repozitoriyaları üçün səhifələmə və sıralama məlumatlarını emal etmək və uyğun bir PageRequest obyektini yaratmaqdır.
public class PageableRequestBuilder {
    private PageableRequestBuilder() {
    }

    public static PageRequest build(PageableQuery query) {
        if (query.getSortDirection() == null || query.getSortBy() == null) {
            return PageRequest.of(
                    Math.max(0, query.getPage() - 1),
                    Math.max(0, query.getPageSize())
            );
        }

        return PageRequest.of(
                Math.max(0, query.getPage() - 1),
                Math.max(0, query.getPageSize()),
                Sort.Direction.valueOf(query.getSortDirection().toUpperCase()),
                query.getSortBy()
        );
    }
}
