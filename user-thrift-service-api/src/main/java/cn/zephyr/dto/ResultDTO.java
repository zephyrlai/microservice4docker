package cn.zephyr.dto;

/**
 * @Author: zephyrLai
 * @Description: 结果实体
 * @Date: 2019/4/10 17:25
 */
public class ResultDTO<T> {
    private String code;
    private String msg;
    private T data;

    public static ResultDTO USERNAME_PASSWORD_INVALID=new ResultDTO("1001","USERNAME_PASSWORD_INVALID",null);
    public static ResultDTO EMAIL_OR_MOBILE_REQUIRED=new ResultDTO("1002","EMAIL_OR_MOBILE_REQUIRED",null);
    public static ResultDTO VERIFY_CODE_INVALID=new ResultDTO("1003","VERIFY_CODE_INVALID",null);

    public static ResultDTO OPEARTION_FAILURE=new ResultDTO("5001","OPEARTION_FAILURE",null);

    public ResultDTO() {
    }

    public ResultDTO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static <T> ResultDTO build(String code, String msg, T data){
        return new ResultDTO<T>(code, msg, data);
    }

    public static <T> ResultDTO operSucc(T data){
        return new ResultDTO<T>("200", "操作成功", data);
    }
}
