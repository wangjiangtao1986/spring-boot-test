package com.icbc.restFull;

/**
 * RestFullResult 工具类
 * @author Administrator
 *
 */
public class RestResultGenerator {


    /**
     * normal
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(boolean success, String message, T data) {
        RestResult<T> result = RestResult.newInstance();
        result.setSuccess(success);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    /**
     * success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(T data) {
        return genResult(true, null, data);
    }

    /**
     * error message
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(String message) {
        return genResult(false, message, null);
    }

    /**
     * error
     * @param error error enum
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(ReturnCode error) {
        return genErrorResult(error.getMessage());
    }
}
