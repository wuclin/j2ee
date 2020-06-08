package com.jlu.jtee.service;

import com.jlu.jtee.domain.ChoiceQuestion;

import java.util.List;

public interface ChoiceQuestionService {
    void importChoice(ChoiceQuestion choiceQuestion);

    List<String> getTypeList();

    List<ChoiceQuestion> getListByType(String type);

    String getAnswerById(Integer TiHao);
}
