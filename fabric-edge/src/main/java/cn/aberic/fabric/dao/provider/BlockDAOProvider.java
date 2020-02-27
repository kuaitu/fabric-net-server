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

package cn.aberic.fabric.dao.provider;

import cn.aberic.fabric.dao.entity.Block;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author : Aberic 【2018-08-22 13:52】
 */
public class BlockDAOProvider {

    public String insertAll(Map map) {
        List<Block> roles = (List<Block>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into fns_block ");
        sb.append("(channel_id,height,data_hash," +
                "calculated_hash,previous_hash,envelope_count," +
                "tx_count,r_w_set_count,timestamp,calculate_date,create_date) ");
        sb.append("values ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].channelId}, #'{'list[{0}].height}, #'{'list[{0}].dataHash}, " +
                "#'{'list[{0}].calculatedHash}, #'{'list[{0}].previousHash}, #'{'list[{0}].envelopeCount}, " +
                "#'{'list[{0}].txCount}, #'{'list[{0}].rwSetCount}, #'{'list[{0}].timestamp}, #'{'list[{0}].calculateDate}, #'{'list[{0}].createDate})");
        for (int i = 0; i < roles.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < roles.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

}
