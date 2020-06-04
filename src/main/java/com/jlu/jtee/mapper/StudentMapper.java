package com.jlu.jtee.mapper;

import com.jlu.jtee.domain.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
    Student sel(@Param("id") int id);

    Student checkoutUsername(@Param("username") String username);

    Student checkoutPassword(@Param("password") String password);

    Student checkoutAdmin(@Param("username") String username, @Param("password") String password);
}
