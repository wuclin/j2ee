package com.jlu.jtee.service.serviceImpl;

import com.jlu.jtee.domain.Student;
import com.jlu.jtee.mapper.StudentMapper;
import com.jlu.jtee.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    public Student sel(int id) {
        return studentMapper.sel(id);
    }

    @Override
    public Student checkoutUsername(String username) {
        return studentMapper.checkoutUsername(username);
    }

    @Override
    public Student checkoutPassword(String password) {
        return studentMapper.checkoutPassword(password);
    }

    @Override
    public Student checkoutAdmin(String username, String password) {
        return studentMapper.checkoutAdmin(username, password);
    }

    @Override
    public Integer findIdByUserName(String userName) {
        return studentMapper.findIdByUserName(userName);
    }

    @Override
    public void insertStudent(String username, String password) {
        studentMapper.insertStudent(username, password);
    }

    @Override
    public Student checkoutUser(String username, String password) {
        return studentMapper.checkoutUser(username, password);
    }

    @Override
    public void updateFaceId(String purl, Integer id) {
        studentMapper.updateFaceId(purl,id);
    }
}
