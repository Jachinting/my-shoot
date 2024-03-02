package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoot.myshootbg.entity.dto.Props;
import com.shoot.myshootbg.mapper.PropsMapper;
import com.shoot.myshootbg.service.PropsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropsServiceImpl extends ServiceImpl<PropsMapper, Props> implements PropsService {
    @Override
    public Props findByNameOrId(String text) {
        return this.query()
                .eq("name", text).or()
                .eq("id", text)
                .one();
    }

    @Override
    public List<Props> getAllData() {
        return this.list();
    }

    @Override
    public boolean save(Props entity) {
        return super.save(entity);
    }

    @Override
    public boolean updateById(Props entity) {
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Props props) {
        return super.removeById(props);
    }
}
