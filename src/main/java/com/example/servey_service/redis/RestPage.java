package com.example.servey_service.redis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Spring Data PageImpl을 JSON 직렬화→역직렬화할 때,
 * pageable·sort 필드는 무시하고 content, page, size, totalElements만 사용하기 위한 래퍼
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable", "sort"})
public class RestPage<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPage(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int page,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") long totalElements
    ) {
        super(content, PageRequest.of(page, size), totalElements);
    }

    /** 기존 Page<T> → RestPage<T> 변환용 생성자 */
    public RestPage(Page<T> page) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
    }
}
