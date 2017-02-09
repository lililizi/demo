package org.lizi.demo.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.lizi.demo.domain.RedPacket;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by touch on 2017/2/9.
 */
public class RedisDao {
    private final JedisPool pool;
    private RuntimeSchema<RedPacket> schema=RuntimeSchema.createFrom(RedPacket.class);

    public RedisDao(String ip,int port) {
        pool=new JedisPool(ip,port);
    }
    public String putRedPacket(RedPacket packet){
        try {
            Jedis jedis=pool.getResource();
            try {
                String key="RedPacketId:"+packet.getrId();
                byte[] bytes= ProtostuffIOUtil.toByteArray(packet,schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout=3600;
                String result=jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public RedPacket getRedPacket(long rId){
        try {
            Jedis jedis=pool.getResource();
            try {
                String key="RedPacketId:"+rId;
                byte[] bytes=jedis.get(key.getBytes());
                if (bytes!=null) {
                    RedPacket redPacket=schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes,redPacket,schema);
                    return redPacket;
                }

            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
