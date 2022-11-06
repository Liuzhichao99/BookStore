package com.liu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private boolean flag;
    private String msg;
    private Integer code;
    private T data;

    /**
     * 返回一个成功对象
     * @param msg
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(String msg, Integer code, T data) {
        ResultVO<T> resultVO = new ResultVO();
        resultVO.setFlag(true);
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 返回一个失败对象
     *
     * @param msg
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> fail(String msg, Integer code, T data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setFlag(false);
        resultVO.setMsg(msg);
        resultVO.setCode(code);
        resultVO.setData(data);
        return resultVO;
    }
}
