package com.yc.jpaplus.core.repository.exception;

/**
 * @Author: TJM
 * @Date: 2020/3/25 23:16
 */
public class DtoValidException extends RuntimeException{
    private static final long serialVersionUID = 234122996006267687L;

    /**
     * Constructs an <code>IndexOutOfBoundsException</code> with no
     * detail message.
     */
    public DtoValidException() {
        super();
    }

    /**
     * Constructs an <code>IndexOutOfBoundsException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public DtoValidException(String s) {
        super(s);
    }
}