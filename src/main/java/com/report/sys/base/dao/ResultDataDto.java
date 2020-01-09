package com.report.sys.base.dao;

import com.report.sys.base.service.exception.RuntimeFunctionException;
import com.report.sys.base.service.exception.RuntimeOtherException;
import com.report.sys.base.service.exception.RuntimeServiceException;
import com.report.sys.base.service.exception.RuntimeWebException;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * author：ccf
 */
@Data
public class ResultDataDto implements Serializable {
    /**
     * 200-成功
     */
    public final static String CODE_SUCCESS = "20000";

    /**
     *  301-代表永久性转移
     */
    public final static String CODE_PERMANENTLY_MOVED = "301";

    /**
     * 500-业务逻辑错误
     */
    public final static String CODE_ERROR_SERVICE = "500";

    /**
     * 501-功能不完善，无对应方法
     */
    public final static String CODE_ERROR_FUNCTION = "501";

    /**
     * 502-网络异常
     */
    public final static String CODE_ERROR_WEB = "502";
    /**
     * 503-未知其它
     */
    public final static String CODE_ERROR_OTHER = "503";

    public static ResultDataDto addSuccess() {
        return new ResultDataDto(CODE_SUCCESS, null);
    }

    public static ResultDataDto addSuccess(String message) {
        return new ResultDataDto(CODE_SUCCESS, message);
    }

    public static ResultDataDto addAddSuccess() {
        return new ResultDataDto(CODE_SUCCESS, "新增成功");
    }

    public static ResultDataDto addUpdateSuccess() {
        return new ResultDataDto(CODE_SUCCESS, "更新成功");
    }

    public static ResultDataDto addUpdateCheckSuccess() {
        return new ResultDataDto(CODE_SUCCESS, "确定成功");
    }

    public static ResultDataDto addDeleteSuccess() {
        return new ResultDataDto(CODE_SUCCESS, "删除成功");
    }
    public static ResultDataDto addOperationSuccess() {
        return new ResultDataDto(CODE_SUCCESS, "操作成功");
    }
    public ResultDataDto() {
        super();
    }

    public ResultDataDto(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ResultDataDto(String message) {
        super();
        this.code = CODE_SUCCESS;
        this.message = message;
    }

    /**
     * 返回单个实体
     */
    public<T> ResultDataDto(T entity) {
        super();
        this.code = CODE_SUCCESS;
        this.datas = entity;
    }

    /**
     * 返回集合类型
     */
    public ResultDataDto(List<?> list) {
        super();
        this.code = CODE_SUCCESS;
        this.datas = list;
    }

    /**
     * 返回Map集合类型
     */
    public ResultDataDto(Map<String, Object> map) {
        super();
        this.code = CODE_SUCCESS;
        this.datas = map;
    }


    /**
     * 500-业务逻辑错误
     */
    public ResultDataDto(RuntimeServiceException rex) {
        super();
        this.code = CODE_ERROR_SERVICE;
        this.message = rex.getMessage();
    }

    /**
     * 501-功能不完善，无对应方法
     */
    public ResultDataDto(RuntimeFunctionException rex) {
        super();
        this.code = CODE_ERROR_FUNCTION;
        this.message = rex.getMessage();
    }

    /**
     * 502-网络异常
     */
    public ResultDataDto(RuntimeWebException rex) {
        super();
        this.code = CODE_ERROR_WEB;
        this.message = rex.getMessage();
    }

    /**
     * 503-未知其它
     */
    public ResultDataDto(RuntimeOtherException rex) {
        super();
        this.code = CODE_ERROR_OTHER;
        this.message = rex.getMessage();
    }

    /**
     * 异常
     */
    public ResultDataDto(Exception ex) {
        super();
        this.code = CODE_ERROR_OTHER;
        this.message = getErrorMessage(ex);
        ex.printStackTrace();
    }

    /**
     * 运行时异常
     */
    public ResultDataDto(RuntimeException rex) {
        super();
        this.code = CODE_ERROR_OTHER;
        this.message = rex.getMessage();
    }

    /**
     * 运行时异常
     */
    public ResultDataDto(Throwable tx) {
        super();
        this.code = CODE_ERROR_OTHER;
        this.message = tx.getMessage();
    }

    /**
     * 结果编码
     */
    @XmlElement(name="code")
    private String code;

    /**
     * 消息
     */
    private String message;

    /**
     * 结果数据，单个实体 或 List<T>
     */
    private Object datas;
    /**
     * 扩展，如果有需要多个返回data
     */
    private Object datas2;

    //private Object datas3;

    private static String getErrorMessage(Exception ex) {
        if (ex instanceof ArithmeticException) {
            return "系统异常：计算错误";
        }
        if (ex instanceof NullPointerException) {
            return "系统异常：输入错误，缺少输入值";
        }
        if (ex instanceof ClassCastException) {
            return "系统异常：类型转换错误";
        }
        if (ex instanceof NegativeArraySizeException) {
            return "系统异常：集合负数";
        }
        if (ex instanceof ArrayIndexOutOfBoundsException) {
            return "系统异常：集合超出范围";
        }
        if (ex instanceof NumberFormatException) {
            return "系统异常：输入数字错误";
        }
        if (ex instanceof SQLException) {
            return "系统异常：数据库异常";
        }
        if (ex instanceof NoSuchMethodException) {
            return "系统异常：方法找不到";
        }
        return ex.getMessage();
    }
}
