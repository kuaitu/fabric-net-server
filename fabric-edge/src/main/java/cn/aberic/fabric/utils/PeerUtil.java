package cn.aberic.fabric.utils;

import cn.aberic.fabric.dao.entity.Orderer;
import cn.aberic.fabric.dao.entity.Peer;
import cn.aberic.fabric.enumerate.EnumMonitorStatus;
import cn.aberic.fabric.service.OrdererService;
import cn.aberic.fabric.service.PeerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class PeerUtil {

    private static PeerUtil instance;

    public static PeerUtil obtain() {
        if (instance == null) {
            synchronized (PeerUtil.class) {
                if (null == instance) {
                    instance = new PeerUtil();
                }
            }
        }
        return instance;
    }

    public void checkPeer(OrdererService ordererService, PeerService peerService) {
        List<Orderer> orderers = ordererService.listAll();

        if (!CollectionUtils.isEmpty(orderers)) {
            for (Orderer orderer : orderers) {
                if (StringUtils.isEmpty(orderer.getLocation())) {
                    continue;
                }
                boolean isOpen = isHostConnectable(orderer.getLocation());
                orderer.setMonitorStatus(EnumMonitorStatus.getStatus(isOpen).getCode());
                orderer.setLastDetectionTime(DateUtil.getCurrent("yyyy-MM-dd HH:mm:ss"));
                ordererService.updateHostState(orderer);
            }
        }

        List<Peer> peers = peerService.listAll();

        if (!CollectionUtils.isEmpty(peers)) {
            for (Peer peer : peers) {
                if (StringUtils.isEmpty(peer.getLocation())) {
                    continue;
                }
                boolean isOpen = isHostConnectable(peer.getLocation());
                peer.setMonitorStatus(EnumMonitorStatus.getStatus(isOpen).getCode());
                peer.setLastDetectionTime(DateUtil.getCurrent("yyyy-MM-dd HH:mm:ss"));
                peerService.updateHostState(peer);
            }
        }
    }

    private boolean isHostConnectable(String location) {
        String[] hostPort = location.split(":");
        return isHostConnectable(hostPort[0], Integer.valueOf(hostPort[1]));
    }

    // 检查ip和端口是否可以连接
    public boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // 检查ip
    public boolean isHostReachable(String host, Integer timeOut) {
        try {
            return InetAddress.getByName(host).isReachable(timeOut);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
