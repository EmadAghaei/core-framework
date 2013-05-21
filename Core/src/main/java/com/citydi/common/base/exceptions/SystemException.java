package com.citydi.common.base.exceptions;

/**
 * Created by Emad Aghayi
 * at 2012/12/14 - 23:05
 */
public class SystemException extends RuntimeException  {
    private ExceptionCodes exceptionCode;

    /**
     *
     * @param code use ExceptionCodes interface for it
     */
    public SystemException(ExceptionCodes code) {
        this.exceptionCode = code;
    }

}
