package org.lizi.demo.Exception;

/**抢红包关闭异常
 * Created by touch on 2017/1/18.
 */
public class GetCloseException extends RuntimeException {
    public GetCloseException(String message){
        super(message);
    }

    public GetCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
