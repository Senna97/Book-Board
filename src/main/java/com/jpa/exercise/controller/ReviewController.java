package com.jpa.exercise.controller;

import com.jpa.exercise.domain.Review;
import com.jpa.exercise.domain.dto.ReviewReadResponse;
import com.jpa.exercise.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@Slf4j
@RequiredArgsConstructor // 필요한 argument 를 넣어서 생성자를 만들어준다.
public class ReviewController {

    private final ReviewService reviewService;

//    public ReviewController(ReviewService reviewService) {
//        this.reviewService = reviewService;
//    }

    // 리뷰 1개를 조회하는 기능
    @GetMapping("/{id}")
    public ResponseEntity<ReviewReadResponse> viewOneReview(@PathVariable Long id) {
        Review review = reviewService.viewReview(id);
        ReviewReadResponse reviewReadResponse = ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .userName(review.getUserName())
                .hospitalName(review.getHospital().getHospitalName())
                .build();
        return ResponseEntity.ok().body(reviewReadResponse);
    }
}
