package org.lizi.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.lizi.demo.domain.RedPacket;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by touch on 2017/1/18.
 */
public interface redpacketsMapper {
    /**
     * 减去抢的金额
     * @param rId
     * @return
     */
    int reduceMoney(@Param("rId") long rId,@Param("money") float money);

    /**
     * 按id查询红包对象
     * @param rId
     * @return
     */
    RedPacket queryById(long rId);

    /**
     * 分页查询
     * @return
     */
    List<RedPacket> queryList();

    void getbyPro(Map<String,Object> paramMap);
}
