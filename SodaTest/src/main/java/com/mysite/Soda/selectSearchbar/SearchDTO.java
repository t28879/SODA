package com.mysite.Soda.selectSearchbar;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import groovy.transform.builder.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonAutoDetect
public class SearchDTO {
    private String mySearchType;
    private String searchInput;

    public SearchDTO() {
    }

    @Builder
    public SearchDTO(String mySearchType, String searchInput) {
        this.mySearchType = mySearchType;
        this.searchInput = searchInput;
    }
}
