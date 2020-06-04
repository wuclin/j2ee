package com.jlu.jtee.mapper;

import com.jlu.jtee.domain.ChoiceQuestion;
import org.apache.ibatis.annotations.Param;

public interface ChoiceQuestionMapper {
    void importChoice(@Param("choiceQuestion") ChoiceQuestion choiceQuestion);
}
