package com.jlu.jtee.service.serviceImpl;

import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.mapper.ChoiceQuestionMapper;
import com.jlu.jtee.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {
    @Autowired
    ChoiceQuestionMapper choiceQuestionMapper;

    @Override
    public void importChoice(ChoiceQuestion choiceQuestion) {
        choiceQuestionMapper.importChoice(choiceQuestion);
    }
}
