package com.jlu.jtee.service;

import com.jlu.jtee.domain.Exam;

import java.util.Date;
import java.util.List;

public interface ExamService {
    void newExam(Exam exam);

    void updateExam(String status, String score, Date postTime, int sId);

    List<Exam> findAllMyExamBySId(Integer sId);

    List<Exam> getExam(int sid);
}
