package com.jlu.jtee.mapper;

import com.jlu.jtee.domain.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    Student sel(@Param("id") int id);

    Student checkoutUsername(@Param("username") String username);

    Student checkoutPassword(@Param("password") String password);

    Student checkoutAdmin(@Param("username") String username, @Param("password") String password);

    Integer findIdByUserName(@Param("username") String userName);

    void insertStudent(@Param("username") String username, @Param("password") String password);

    Student checkoutUser(@Param("username") String username,@Param("password") String password);

    void updateFaceId(@Param("purl") String purl,@Param("id")Integer id);
}
