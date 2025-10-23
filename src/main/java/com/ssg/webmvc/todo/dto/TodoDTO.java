package com.ssg.webmvc.todo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoDTO {
    private Long tno; //todo 고유값 pk
    private String title;
    private LocalDate dueDate; //등록시간
    private boolean finished; //할일 체크
}
//DTO 용도: 여러 개의 데이터를 묶어서 하나의 객체로 구성
//서비스 객체의 매소드들의 파라미터나 리턴타입으로 사용됨