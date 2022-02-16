# Book Diary

## 코드 회고

- RecyclerView를 이용해 calendar를 직접 커스텀하여 사용할 수 있도록 제작
- Naver Book API를 사용해 Book 검색 기능 제공
- Library : RxJava, picasso, retrofit, circleimageview

### [Naver Book API](https://developers.naver.com/docs/search/book/)

- retrofit 라이브러리를 사용해 API 추출 코드 구축
- query를 이용한 기본 검색으로 json 데이터 추출

> 검색 결과 개수 제한이 있어 10개로 설정하여 스크롤 시 추가로 10개씩 검색 결과 업데이트

> html 마크업언어가 함께 추출되어 utf8로 변경 수행
