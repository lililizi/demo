package org.lizi.demo.Exception;

/**重复秒杀异常
 * Created by touch on 2017/1/18.
 */
public class RepeatException extends RedPacketException {
    public RepeatException(String message) {
        super(message);
    }

    public RepeatException(String message, Throwable cause) {
        super(message, cause);
    }
}
