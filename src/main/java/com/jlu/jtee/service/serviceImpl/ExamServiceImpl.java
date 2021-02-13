package com.jlu.jtee.service.serviceImpl;

import com.jlu.jtee.domain.Exam;
import com.jlu.jtee.mapper.ExamMapper;
import com.jlu.jtee.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamMapper examMapper;

    @Override
    public void newExam(Exam exam) {
        examMapper.newExam(exam);
    }

    @Override
    public void updateExam(String status, String score, Date postTime,int sId) {
        examMapper.updateExam(status,score,postTime,sId);
    }

    @Override
    public List<Exam> findAllMyExamBySId(Integer sId) {
        return examMapper.findAllMyExamBySId(sId);
    }

    @Override
    public List<Exam> getExam(int sid) {
        return examMapper.getExam(sid);
    }
}
