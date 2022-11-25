package com.jpa.exercise.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ReviewReadResponse {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private String hospitalName;
}
