package com.jpa.exercise.controller;

import com.jpa.exercise.domain.dto.ReviewCreateRequest;
import com.jpa.exercise.domain.dto.ReviewCreateResponse;
import com.jpa.exercise.domain.dto.ReviewReadResponse;
import com.jpa.exercise.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@Slf4j
public class HospitalController {

    private final ReviewService reviewService;

    public HospitalController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest) {
        log.info("{}", reviewCreateRequest);
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
        // 에러 처리를 아직 하지 않았기 때문에 ok()로 한다.
        // 컨트롤러는 사용자의 요청을 받아서 받은 요청을 서비스로 보내고 서비스에서 리턴한 것을 다시 사용자에게 응답한다.
    }

    @GetMapping("/{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> viewAllReviews(@PathVariable Long hospitalId) {
        return ResponseEntity.ok().body(reviewService.viewReviewList(hospitalId));
    }
}
