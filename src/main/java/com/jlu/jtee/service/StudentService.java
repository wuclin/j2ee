package com.jlu.jtee.service;

import com.jlu.jtee.domain.Student;

public interface StudentService {

    Student sel(int id);

    Student checkoutUsername(String username);

    Student checkoutPassword(String password);

    Student checkoutAdmin(String username, String password);
}
