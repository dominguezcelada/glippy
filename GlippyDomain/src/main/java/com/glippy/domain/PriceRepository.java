package com.glippy.domain;

import com.glippy.entity.Price;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by oscar on 08/11/2014.
 */
public interface PriceRepository extends CrudRepository<Price,String> {
}
