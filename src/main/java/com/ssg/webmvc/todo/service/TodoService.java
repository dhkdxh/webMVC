package com.ssg.webmvc.todo.service;

import com.ssg.webmvc.todo.dao.TodoDAO;
import com.ssg.webmvc.todo.domain.TodoVO;
import com.ssg.webmvc.todo.dto.TodoDTO;
import com.ssg.webmvc.todo.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public enum TodoService {
    INSTANCE; //객체의 수를 정할 떄 사용 + 현재 INSTANCE가 1개 이므로 서비스 객체는 1개만 운영 + singleton pattern
    //여러 컨트롤러들이 TodoService 객체를 통해서 원하는 데이터를 주고받는 구조로 구성

    private TodoDAO dao;
    private ModelMapper modelMapper;

    TodoService() {
        this.dao = new TodoDAO();
        this.modelMapper = MapperUtil.INSTANCE.get();
    }

    public void register(TodoDTO todoDTO) throws Exception {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        //원형으로 copy하겠다는 의미
        log.info("todoVO : " + todoVO);

        log.info(todoVO);

        dao.insert(todoVO);
    }

    //10개의 TodoDTO 객체를 만들어 반환
//    public List<TodoDTO> getList() {
//        List<TodoDTO> todoDTOS = IntStream.range(0,10).mapToObj(
//                i -> {
//                    TodoDTO dto = new TodoDTO();
//                    dto.setTno((long) i);
//                    dto.setTitle("Todo..title" + i);
//                    dto.setDueDate(LocalDate.now());
//                    return dto;
//                }
//        ).collect(Collectors.toList());
//        return todoDTOS;
//    }
//
//    public TodoDTO get(Long tno){
//        TodoDTO dto = new TodoDTO();
//
//        dto.setTno(tno);
//        dto.setTitle("Sample Todo");
//        dto.setDueDate(LocalDate.now());
//        dto.setFinished(true);
//        return dto;
//    }

    public List<TodoDTO> listALl() throws Exception {
        List<TodoVO> voList = dao.selectAll();
        log.info("voList: "+ voList);

        List<TodoDTO> dtoList = voList.stream().map(
                vo -> modelMapper.map(vo, TodoDTO.class)
        ).collect(Collectors.toList());

        return dtoList;
    }

    public TodoDTO selectOne(Long tno) throws Exception {
        TodoVO vo = dao.selectOne(tno);
        if(vo == null) return null;

        log.info("vo: " + vo);

        TodoDTO dto = modelMapper.map(vo, TodoDTO.class);
        return dto;
    }

    public void updateOne(TodoDTO newDTO) throws Exception{
        TodoVO newVO = modelMapper.map(newDTO,TodoVO.class);

        dao.updateOne(newVO);
    }

    public void deleteOne(Long tno) throws Exception {
        dao.deleteOne(tno);
    }
}

//장점: 정해진 수만큼 객체를 생성할 수 있음
