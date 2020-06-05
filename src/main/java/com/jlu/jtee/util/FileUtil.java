package com.jlu.jtee.util;

import com.jlu.jtee.domain.ChoiceQuestion;
import com.jlu.jtee.service.ChoiceQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
@Component
public class FileUtil {
    @Autowired
    ChoiceQuestionService choiceQuestionService;

    public static FileUtil fileUtil;
    @PostConstruct
    public void init(){
        fileUtil = this;
        fileUtil.choiceQuestionService = this.choiceQuestionService;
    }
    public String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    /**
     * 每道题的开始有六个#
     * 以***开始的是题目的类型
     * 以**开始的是题干
     * 以*开始的是选项
     * 以>>>开始是答案
     * 该题结束时答案的下一行为#
     * @return
     */
    public int txt222String(){
        int cnt = 0;//题目数
        String content = "";//题目
        String answer = "";//答案
        String type = "";//题目类型
        String[] choices = new String[6];//选项
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("D:\\project\\jtee\\src\\main\\resources\\resources\\exam\\9cd1e836-b40c-4313-98f1-a9d99c0a504da53b58a6-a3f9-4bc7-9af7-eb4c7ad49dd5选择题import2.txt")));
            String line = "";
            ChoiceQuestion q = new ChoiceQuestion();
            while((line = br.readLine()) != null){
                line = line.replace(" ", "");//将UTF-8的特殊空格替换掉，编码c2a0
                line = line.replace((char) 65279, ' ');//对有BOM头的第一行做处理
                line = line.trim();//去掉每一行的两端空格
                if (line.equals(""))
                    continue;
                else if (line.startsWith("######")){
                    continue;
                }else if (line.startsWith("***")){
                    type = line.substring(3).trim();
                    q.setType(type);
                }else if(line.startsWith("@@")){
                    int index = line.lastIndexOf(",");
                    content = line.substring(index+1,line.length()).trim();
                    q.setContent(content);
                }else if(line.startsWith("-")){
                    char firstChar = line.charAt(1);
                    if (Character.isUpperCase(firstChar)){
                        switch (firstChar){
                            case 'A':
                                choices[0] = line.substring(1,line.length()).trim();
                                q.setChoiceA(choices[0]);
                                break;
                            case 'B':
                                choices[1] = line.substring(1,line.length()).trim();
                                q.setChoiceB(choices[1]);
                                break;
                            case 'C':
                                choices[2] = line.substring(1,line.length()).trim();
                                q.setChoiceC(choices[2]);
                                break;
                            case 'D':
                                choices[3] = line.substring(1,line.length()).trim();
                                q.setChoiceD(choices[3]);
                                break;
                            case 'E':
                                choices[4] = line.substring(1,line.length()).trim();
                                q.setChoiceE(choices[4]);
                                break;
                            case 'F':
                                choices[5] = line.substring(1,line.length()).trim();
                                q.setChoiceF(choices[5]);
                                break;
                        }
                    }

                }else if (line.startsWith(">>>")){
                    answer = line.substring(3,line.length());
                    q.setAnswer(answer);
                }else if (line.startsWith("%")){
                    cnt++;
                    choiceQuestionService.importChoice(q);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cnt;
    }

}
