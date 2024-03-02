package com.shoot.myshootbg.service;

import com.shoot.myshootbg.entity.dto.Props;

import java.util.List;

public interface PropsService {
    Props findByNameOrId(String text);


    List<Props> getAllData();

    boolean save(Props props);

    boolean updateById(Props props);

    boolean removeById(Props props);
}
