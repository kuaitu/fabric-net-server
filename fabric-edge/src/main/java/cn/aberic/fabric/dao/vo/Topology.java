package cn.aberic.fabric.dao.vo;

import cn.aberic.fabric.dao.entity.Orderer;
import lombok.Data;

import java.util.List;

@Data
public class Topology {
    private List<Orderer> ordererList;
    private List<OrgVO> orgVOList;
}
