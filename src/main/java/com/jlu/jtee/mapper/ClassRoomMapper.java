package com.jlu.jtee.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassRoomMapper {
    List SelectClassRoomName();

    void setClassByName(@Param("username")String userName, String className);
}
