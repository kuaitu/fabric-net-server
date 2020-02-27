/*
 * Copyright (c) 2018. Aberic - All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.aberic.fabric.scheduled;

import cn.aberic.fabric.dao.vo.Topology;
import cn.aberic.fabric.service.*;
import cn.aberic.fabric.utils.BlockUtil;
import cn.aberic.fabric.utils.DataUtil;
import cn.aberic.fabric.utils.PeerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 描述：
 *
 * @author : Aberic 【2018-08-10 11:13】
 */
@Component
@Slf4j
public class ScheduledTasks {

    @Resource
    private LeagueService leagueService;
    @Resource
    private OrgService orgService;
    @Resource
    private OrdererService ordererService;
    @Resource
    private PeerService peerService;
    @Resource
    private CAService caService;
    @Resource
    private ChannelService channelService;
    @Resource
    private ChaincodeService chaincodeService;
    @Resource
    private AppService appService;
    @Resource
    private TraceService traceService;
    @Resource
    private BlockService blockService;
    @Resource
    private StatisticsService statisticsService;

    //fixedDelay = x 表示当前方法执行完毕x ms后，Spring scheduling会再次调用该方法
    @Scheduled(fixedDelay = 15000)
    public void homeUpgrade() {
        log.info("===============>>>>>>>>>> home upgrade start <<<<<<<<<<===============");
        DataUtil.obtain().home(leagueService, orgService, ordererService,
                peerService, caService, channelService, chaincodeService,
                appService, blockService);
        log.info("===============>>>>>>>>>> home upgrade end   <<<<<<<<<<===============");
    }

    @Scheduled(fixedDelay = 60000)
    public void checkChannel() {
        log.info("===============>>>>>>>>>> check channel start <<<<<<<<<<===============");
        BlockUtil.obtain().checkChannel(channelService, caService, blockService, traceService, channelService.listAll());
        log.info("===============>>>>>>>>>> check channel end   <<<<<<<<<<===============");
    }

    //检查节点健康状态
    @Scheduled(fixedDelay = 60000)
    public void checkPeer() {
        log.info("===============>>>>>>>>>> check checkPeer start <<<<<<<<<<===============");
        Topology topology = statisticsService.getTopology();
        PeerUtil.obtain().checkPeer(ordererService, peerService);
        log.info("===============>>>>>>>>>> check checkPeer end   <<<<<<<<<<===============");
    }

}
