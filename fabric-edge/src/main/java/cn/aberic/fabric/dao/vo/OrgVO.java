package cn.aberic.fabric.dao.vo;

import cn.aberic.fabric.dao.entity.Org;
import cn.aberic.fabric.dao.entity.Peer;
import lombok.Data;

import java.util.List;

@Data
public class OrgVO extends Org {
    private List<Peer> peerList;
}
