package com.shoot.myshootbg.service.ipml;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoot.myshootbg.entity.dto.Props;
import com.shoot.myshootbg.entity.dto.Recordprop;
import com.shoot.myshootbg.mapper.RecordpropMapper;
import com.shoot.myshootbg.service.RecordpropService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordpropServiceImpl extends ServiceImpl<RecordpropMapper, Recordprop> implements RecordpropService {
    @Override
    public boolean save(Recordprop entity) {
        return super.save(entity);
    }

    @Override
    public boolean delByprops(Props props) {
        //根据传入的道具实体删除相关的出借记录
        QueryWrapper<Recordprop> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("propsid", props.getId());
        List<Recordprop> recordpropList= this.baseMapper.selectList(queryWrapper);
        System.out.println(recordpropList);
        ArrayList<Integer> idlist = new ArrayList<>();
        for (Recordprop recordprop:recordpropList){
            idlist.add(recordprop.getId());
        }
        return super.removeBatchByIds(idlist);

    }

    @Override
    public List<Recordprop> findbyId(Integer propid) {
        return this.query().eq("propsid",propid).list();
    }

    @Override
    public List<Recordprop> findByNameAndId(String name, Integer propsid) {
        return this.query()
                .eq("username", name)
                .eq("propsid", propsid)
                .list();
    }

    @Override
    public boolean updateById(Recordprop entiy) {
        return super.updateById(entiy);
    }
}
