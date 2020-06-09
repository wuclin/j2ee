package com.jlu.jtee.service.serviceImpl;

import com.jlu.jtee.domain.Exam;
import com.jlu.jtee.mapper.ExamMapper;
import com.jlu.jtee.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamMapper examMapper;

    @Override
    public void newExam(Exam exam) {
        examMapper.newExam(exam);
    }

    @Override
    public void updateExam(int status, String score, Date postTime,int sId) {
        examMapper.updateExam(status,score,postTime,sId);
    }
}
