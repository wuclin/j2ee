package com.jlu.jtee.service;

import java.util.Collection;
import java.util.List;

public interface ClassRoomService {
    List<String> SelectClassRoomName();

    void setClassByName(String userName, String className);
}
