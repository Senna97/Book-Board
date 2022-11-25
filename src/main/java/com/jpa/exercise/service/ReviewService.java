package com.jpa.exercise.service;

import com.jpa.exercise.domain.Hospital;
import com.jpa.exercise.domain.Review;
import com.jpa.exercise.domain.dto.ReviewCreateRequest;
import com.jpa.exercise.domain.dto.ReviewCreateResponse;
import com.jpa.exercise.domain.dto.ReviewReadResponse;
import com.jpa.exercise.repository.HospitalRepository;
import com.jpa.exercise.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public ReviewCreateResponse createReview(ReviewCreateRequest reviewCreateRequest) {

        // Hospital 불러오기
        Optional<Hospital> optionalHospital = hospitalRepository.findById(reviewCreateRequest.getHospitalId());

        // ReviewEntity 만들기
        Review review = Review.builder()
                .title(reviewCreateRequest.getTitle())
                .content(reviewCreateRequest.getContent())
                .userName(reviewCreateRequest.getUserName())
                .hospital(optionalHospital.get())
                .build();

        // 저장
        Review savedReview = reviewRepository.save(review);

        return ReviewCreateResponse.builder()
                .userName(savedReview.getUserName())
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .message("리뷰 등록 성공")
                .build();
    }

    public Review viewReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("입력하신 id에 해당하는 리뷰가 없습니다."));
        return review;
    }

    public List<ReviewReadResponse> viewReviewList(Long hospitalId) {
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new IllegalArgumentException("입력하신 id에 해당하는 병원이 없습니다."));

        List<ReviewReadResponse> reviewList = reviewRepository.findByHospital(hospital)
                .stream()
                .map(review -> ReviewReadResponse.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .content(review.getContent())
                        .userName(review.getUserName())
                        .hospitalName(review.getHospital().getHospitalName())
                        .build())
                .collect(Collectors.toList());

        return reviewList;
    }
}