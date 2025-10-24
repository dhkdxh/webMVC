package com.ssg.webmvc.todo.domain;

import lombok.*;

import java.time.LocalDate;

//읽기 전용
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoVO {
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
