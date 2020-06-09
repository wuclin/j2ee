package com.jlu.jtee.service;

import com.jlu.jtee.domain.Exam;

import java.util.Date;

public interface ExamService {
    void newExam(Exam exam);

    void updateExam(int status, String score, Date postTime, int sId);
}
