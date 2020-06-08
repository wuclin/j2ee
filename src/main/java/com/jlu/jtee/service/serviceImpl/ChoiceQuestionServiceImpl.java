package com.jlu.jtee.service.serviceImpl;

import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.mapper.ChoiceQuestionMapper;
import com.jlu.jtee.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {
    @Autowired
    ChoiceQuestionMapper choiceQuestionMapper;

    @Override
    public void importChoice(ChoiceQuestion choiceQuestion) {
        choiceQuestionMapper.importChoice(choiceQuestion);
    }

    @Override
    public List<String> getTypeList() {
        return choiceQuestionMapper.getTypeList();
    }

    @Override
    public List<ChoiceQuestion> getListByType(String type) {
        return choiceQuestionMapper.getListByType(type);
    }

    @Override
    public String getAnswerById(Integer TiHao) {
        return choiceQuestionMapper.getAnswerById(TiHao);
    }
}
