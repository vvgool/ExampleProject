package org.project.net.ticket;

/**
 * Created by wiesen on 2016/11/7.
 */

public class TicketResponse <T>{
    public String  reason;
    public int resultcode;
    public T result;
    public int 	error_code;

    @Override
    public String toString() {
        return "resultcode:"+resultcode +"\t"+"reason:"+reason;
    }
}
