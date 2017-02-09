package org.lizi.demo.Exception;

/**
 * Created by touch on 2017/1/18.
 */
public class RedPacketException extends GetCloseException{
    public RedPacketException(String message) {
        super(message);
    }

    public RedPacketException(String message, Throwable cause) {
        super(message, cause);
    }
}
