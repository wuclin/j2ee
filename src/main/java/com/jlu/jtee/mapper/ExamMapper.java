package com.jlu.jtee.mapper;

import com.jlu.jtee.domain.Exam;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ExamMapper {
    void newExam(@Param("exam") Exam exam);

    void updateExam(@Param("status") String status,@Param("score") String score,@Param("postTime") Date postTime,@Param("sId")int sId);

    List<Exam> findAllMyExamBySId(@Param("sId") Integer sId);

    List<Exam> getExam(@Param("sId") int sid);
}
