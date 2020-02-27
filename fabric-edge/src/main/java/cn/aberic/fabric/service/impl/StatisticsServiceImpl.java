package cn.aberic.fabric.service.impl;

import cn.aberic.fabric.dao.mapper.StatisticsMapper;
import cn.aberic.fabric.dao.vo.Topology;
import cn.aberic.fabric.service.OrdererService;
import cn.aberic.fabric.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Resource
    private OrdererService ordererService;

    @Override
    public Topology getTopology() {
        Topology topology = new Topology();
        topology.setOrdererList(ordererService.listAll());
        topology.setOrgVOList(statisticsMapper.listOrgVOAll());
        return topology;
    }
}
