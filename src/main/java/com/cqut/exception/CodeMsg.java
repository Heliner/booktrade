package com.cqut.exception;

public class CodeMsg {
    //00000004 服务器异常
    public static final CodeMsg SERVER_ERROR = new CodeMsg(00000004, "服务器异常");

    //10000000用户
    public static final CodeMsg USER_NOT_EXITS = new CodeMsg(10000000, "用户不存在");
    public static final CodeMsg USER_EXITS = new CodeMsg(10000001, "用户已存在");
    public static final CodeMsg IMG_VAL_ERROR = new CodeMsg(10000002, "图片验证码有误");
    public static final CodeMsg MAIL_VAL_ERROR = new CodeMsg(10000003, "邮箱验证码有误");
    public static final CodeMsg PASSWORD_ERROR = new CodeMsg(10000004, "用户密码错误");
    //20000000权限异常

    //30000000参数异常
    public static final CodeMsg BIND_ERROR = new CodeMsg(30000001, "参数有误");
    private int code;
    private String massage;

    private CodeMsg() {
    }

    private CodeMsg(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return massage;
    }

    public void setMsg(String massage) {
        this.massage = massage;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.massage, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", massage=" + massage + "]";
    }
}
