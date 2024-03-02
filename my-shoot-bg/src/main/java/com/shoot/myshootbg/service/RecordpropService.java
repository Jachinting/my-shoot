package com.shoot.myshootbg.service;

import com.shoot.myshootbg.entity.dto.Props;
import com.shoot.myshootbg.entity.dto.Recordprop;

import java.util.List;

public interface RecordpropService {
    List<Recordprop> findByNameAndId(String name, Integer propsid);

    boolean updateById(Recordprop entiy);

    boolean save(Recordprop recordprop);

    boolean delByprops(Props props);

    List<Recordprop> findbyId(Integer propid);
}
