# NOTE

---

### ✈️ Goals
- move the company kkk

---

### 🕰 My Plan
- Phase #1
  - Event Driven Architecture (strength and weakness 파악)
  - MSA 형태로 진화가 가능한 Domain Driven Design
  - Multiple datasource & JPA 설정
  - QueryDSL 적용 및 동적 쿼리 샘플 작성
  - Bulk Insert 처리
  - BaseEntity 기반의 Entity 구현
  - SNS 로그인
  - JWT 적용
  - Spring Security
  - Spring
  - ...

- Phase #2 (MSA 형태로 진화)
  - Spring Cloud 활용 (feat. Netflix OSS)
  
---

### 📍 Check

- Entity
  - @Column 안붙여도 되는지 언더스코어로 컬럼생성되는지
  - @Table 에 제약조건 달기
  - @Column 에 length 등 다 표기하기
  - @EqualsAndHashCode 테스트. 매핑해놓고 레이즈 패치되는지
  - @NoArgsConstructor Best Practice..
  - @PrePersist, @PostPersist
  - @UpdateTime?

---

### ⚠️ Precautions

- Entity 설계의 기본은 단방향 관계
  - 연관관계의 주인에 대해 항상 생각!
  - 양방향 관계는 필요 시에만
  - 관계 형성 시, ToString() 등으로 인한 순환 참조 조심
- Controller 단에서 Entity -> DTO 변환할 것.

---