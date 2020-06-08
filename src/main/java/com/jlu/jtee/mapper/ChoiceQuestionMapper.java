package com.jlu.jtee.mapper;

import com.jlu.jtee.domain.ChoiceQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChoiceQuestionMapper {
    void importChoice(@Param("choiceQuestion") ChoiceQuestion choiceQuestion);

    List<String> getTypeList();

    List<ChoiceQuestion> getListByType(@Param("type") String type);

    String getAnswerById(@Param("TiHao") Integer tiHao);
}
