package cn.aberic.fabric.dao.mapper;

import cn.aberic.fabric.dao.vo.OrgVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    @Select("SELECT id, msp_id FROM fns_org")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "mspId", column = "msp_id"),
            @Result(column="id", property="peerList", javaType=List.class,
                    many=@Many(select="cn.aberic.fabric.dao.mapper.PeerMapper.listByOrgId"))
    })
    List<OrgVO> listOrgVOAll();
}
