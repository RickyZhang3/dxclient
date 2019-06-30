package org.com.dx.common;

public class RespData<T> {
	public static final String SUCCESS = "0";
	public static final String FAIL = "1";
    public static final String DEFAULT_MSG = "操作成功";
    public static final String ERROR_MSG = "接口异常";
    public static final String LOGIN_ERROR_MSG = "用户不存在";
    public static final String NOT_LOGIN_ERROR_CODE = "0-0001";
    public static final String NOT_LOGIN_ERROR_MSG = "用户未登录";
    protected String code;
    protected String msg;
    protected T data;
    
    public RespData() {
    }

    public RespData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespData(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> RespData<T> success(T data) {
        return new RespData("0", "", data);
    }

    public static <T> RespData<T> success(String msg, T data) {
        return new RespData("0", msg, data);
    }

    public static RespData error(String code, String msg) {
        return new RespData(code, msg);
    }

    public static RespData notLoginError() {
        return new RespData("0-0004", "用户未登录");
    }

    public static <T> RespData<T> error(String code, String msg, T data) {
        return new RespData(code, msg, data);
    }

    public static boolean isSucc(RespData respData) {
        return null != respData && "0".equals(respData.getCode());
    }

    public static boolean isSuccAndDataNotNull(RespData respData) {
        return isSucc(respData) && null != respData.getData();
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
