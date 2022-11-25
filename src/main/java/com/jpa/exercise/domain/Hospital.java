package com.jpa.exercise.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    private Long id;
    private String hospitalName;
    private String roadNameAddress;

//    양방향 매핑 이유?
//    나중에 병원 정보와 함께 리뷰를 조회하는 기능을 만들기 위함(양쪽 모두에 FK가 있어야 한다.)
    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY) // get reviews 했을 때, 쿼리가 날아가도록 하는 것이 LAZY(LAZY 가 권장되나 EAGER?라는 다른 전략도 있다. => 알아보기)
    private List<Review> reviews;
}
