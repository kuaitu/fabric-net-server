package cn.aberic.fabric.dao.vo;

import cn.aberic.fabric.dao.entity.Org;
import cn.aberic.fabric.dao.entity.Peer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrgVO extends Org {
    private List<Peer> peerList;
}
