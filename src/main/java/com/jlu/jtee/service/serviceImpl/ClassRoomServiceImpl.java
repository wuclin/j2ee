package com.jlu.jtee.service.serviceImpl;

import com.jlu.jtee.mapper.ClassRoomMapper;
import com.jlu.jtee.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {
    @Autowired
    ClassRoomMapper classRoomMapper;


    @Override
    public List<String> SelectClassRoomName() {
        List ClassName = classRoomMapper.SelectClassRoomName();
        return ClassName;
    }

    @Override
    public void setClassByName(String userName, String className) {
        classRoomMapper.setClassByName(userName, className);
    }
}
