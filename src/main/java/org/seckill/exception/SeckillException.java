package org.seckill.exception;

/**
 * Created by zhj on 2017-7-5.
 */
public class SeckillException  extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
