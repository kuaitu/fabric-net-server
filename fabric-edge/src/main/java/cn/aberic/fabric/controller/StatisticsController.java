package cn.aberic.fabric.controller;

import cn.aberic.fabric.bean.Home;
import cn.aberic.fabric.dao.entity.Orderer;
import cn.aberic.fabric.dao.entity.Peer;
import cn.aberic.fabric.dao.vo.Topology;
import cn.aberic.fabric.service.*;
import cn.aberic.fabric.utils.CacheUtil;
import cn.aberic.fabric.utils.DataUtil;
import cn.aberic.fabric.utils.Ret;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/fabric-edge/statistics")
public class StatisticsController {

    @Resource
    private OrdererService ordererService;

    @Resource
    private PeerService peerService;

    @Resource
    private StatisticsService statisticsService;

    @Resource
    private LeagueService leagueService;

    @Resource
    private OrgService orgService;

    @Resource
    private CAService caService;

    @Resource
    private ChannelService channelService;

    @Resource
    private ChaincodeService chaincodeService;

    @Resource
    private AppService appService;

    @Resource
    private BlockService blockService;


    /**
     * 服务器服务器拓扑
     * @return Topology
     */
    @PostMapping(value = "getTopology")
    public Ret<Topology> getTopology() {
        Topology topology = statisticsService.getTopology();
        return Ret.ok(topology);
    }

    /**
     * 排序服务器状态信息
     * @return List<Orderer>
     */
    @PostMapping(value = "listOrders")
    public Ret<List<Orderer>> listOrders() {
        List<Orderer> orderers = ordererService.listAll();
        return Ret.ok(orderers);
    }

    /**
     * 网络节点状态信息
     * @return List<Peer>
     */
    @PostMapping(value = "listPeers")
    public Ret<List<Peer>> listPeers() {
        List<Peer> peers = peerService.listAll();
        return Ret.ok(peers);
    }

    @PostMapping(value = "index")
    public Ret<Home> index() {
        // Map<String, Object> map = new HashMap<>();
        Home home = CacheUtil.getHome();
        if (null == home) {
            home = DataUtil.obtain().home(leagueService, orgService, ordererService,
                    peerService, caService, channelService, chaincodeService,
                    appService, blockService);
            CacheUtil.putHome(home);
        }

        /*// 首页底部区块记录 blocks
        map.put("blocks", home.getBlocks());

        // 右一 通道区块比 通道交易比 channelPercents
        map.put("channelPercents", new JSONArray(home.getChannelPercents()).toString());

        // 左一 通道区块交易曲线 channelBlockLists
        map.put("channelBlockLists", new JSONArray(home.getChannelBlockLists()).toString());

        // 左二 区块高度	通道名称	交易数量	日期 blockDaos
        map.put("blockDaos", home.getBlockDaos());

        // 右二 平台今日区块数 平台今日交易量 dayStatistics
        map.put("dayStatistics", home.getDayStatistics() != null ? home.getDayStatistics() : 0);

        // 平台区块总数 平台总交易量 平台区块读写集总数 platform
        map.put("platform", home.getPlatform());

        // 平台日总区块曲线 dayBlocks dayBlocksJson
        map.put("dayBlocks", home.getDayBlocks());
        map.put("dayBlocksJson", new JSONObject(home.getDayBlocks()));

        // 平台日总交易量曲线 dayTxs dayTxsJson
        map.put("dayTxs", home.getDayTxs());
        map.put("dayTxsJson", new JSONObject(home.getDayTxs()));

        // 平台日总读写集曲线 dayRWs dayRWsJson
        map.put("dayRWs", home.getDayRWs());
        map.put("dayRWsJson", new JSONObject(home.getDayRWs()));*/

        return Ret.ok(home);
    }
}
