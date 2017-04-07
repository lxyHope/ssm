package cn.ssm.mapper;

import cn.ssm.model.Map;

import java.util.List;

public interface MapMaper {
    public Map findMapById(int id) ;
    public List<Map> findMapList() ;
}
