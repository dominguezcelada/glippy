package com.glippy.domain;

import com.glippy.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/**
 * Created by oscar on 02/11/2014.
 */
public interface UserRepository extends CrudRepository<User, String> {
    ArrayList<User> findByUsername(String username);

    ArrayList<User> deleteByUsername(String username);
}
