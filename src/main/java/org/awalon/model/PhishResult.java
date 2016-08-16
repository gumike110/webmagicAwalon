package org.awalon.model;

/**
 * Created by guming on 2016-08-16.
 */
public class PhishResult {
    private Integer success;
    private Integer phish;
    private Integer errno;
    private String msg;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getPhish() {
        return phish;
    }

    public void setPhish(Integer phish) {
        this.phish = phish;
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
