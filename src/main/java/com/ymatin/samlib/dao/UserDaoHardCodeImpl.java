package com.ymatin.samlib.dao;

import com.ymatin.samlib.model.Author;
import com.ymatin.samlib.model.UserStatus;
import org.springframework.stereotype.Repository;


import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoHardCodeImpl implements UserDao {

    private List<Author> authors = Arrays.asList(
            new Author("Vasily", "Petrov", "veseliy pet", UserStatus.USER, null),
            new Author("Vasily", "Petrov", "veseliy pet", UserStatus.USER, null),
            new Author("Vasily", "Petrov", "veseliy pet", UserStatus.USER, null)
    );

    @Override
    public List<Author> getAll() {
        return authors;
    }
}
