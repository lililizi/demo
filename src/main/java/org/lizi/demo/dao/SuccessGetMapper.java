package org.lizi.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.lizi.demo.domain.SuccessGet;

/**
 * Created by touch on 2017/1/18.
 */
public interface SuccessGetMapper {
    /**
     * 插入抢红包记录
     * @param rId
     * @param money
     * @param userPhone
     * @return
     */
    int insertSuccessGet(@Param("rId") long rId,@Param("money") float money,@Param("phone") long userPhone);

    /**
     *
     * @param rId
     * @param userPhone
     * @return
     */
    SuccessGet queryByIdWithRedPacket(@Param("rId") long rId,@Param("phone") long userPhone);
}
