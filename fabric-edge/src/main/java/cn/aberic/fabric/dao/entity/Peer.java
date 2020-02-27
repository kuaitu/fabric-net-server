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

package cn.aberic.fabric.dao.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 作者：Aberic on 2018/6/27 21:15
 * 邮箱：abericyang@gmail.com
 */
@Setter
@Getter
@ToString
@Table(name = "fns_peer")
public class Peer {

    @Column(name = "id",type = MySqlTypeConstant.INT,length = 9,isKey = true,isAutoIncrement = true)
    private int id; // required
    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 128)
    private String name; // required
    @Column(name = "location",type = MySqlTypeConstant.VARCHAR,length = 256)
    private String location; // required
    @Column(name = "event_hub_location",type = MySqlTypeConstant.VARCHAR,length = 256)
    private String eventHubLocation; // required
    @Column(name = "org_id",type = MySqlTypeConstant.INT,length = 9)
    private int orgId; // required
    @Column(name = "server_crt_path",type = MySqlTypeConstant.VARCHAR,length = 512)
    private String serverCrtPath;
    @Column(name = "client_cert_path",type = MySqlTypeConstant.VARCHAR,length = 512)
    private String clientCertPath;
    @Column(name = "client_key_path",type = MySqlTypeConstant.VARCHAR,length = 512)
    private String clientKeyPath;
    @Column(name = "date",type = MySqlTypeConstant.VARCHAR,length = 14)
    private String date; // required
    @Column(name = "monitor_status",type = MySqlTypeConstant.CHAR,length = 1, defaultValue = "0")
    private String monitorStatus; // required
    @Column(name = "last_detection_time",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String lastDetectionTime; // required

    private String orgName; // required
    private String leagueName; // optional
    private int channelCount; // required
}
