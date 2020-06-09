package com.jlu.jtee.mapper;

import com.jlu.jtee.domain.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ExamMapper {
    void newExam(@Param("exam") Exam exam);

    void updateExam(@Param("status") int status,@Param("score") String score,@Param("postTime") Date postTime,@Param("sId")int sId);
}
