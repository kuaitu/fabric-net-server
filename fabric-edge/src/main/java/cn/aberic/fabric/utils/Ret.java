package cn.aberic.fabric.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ret<T> {
    private T data;
    private Long total;
    private Boolean success;
    private String message;

    public static <T> Ret<T> ok(T data) {
        return new Ret(data, 0L, true, "操作成功");
    }

    public static <T> Ret<T> ok(T data, long total) {
        return new Ret(data, total, true, "操作成功");
    }

    public static Ret<Void> ok() {
        return new Ret((Object)null, 0L, true, "操作成功");
    }

    public static <T> Ret<T> err(String msg) {
        return new Ret((Object)null, 0L, false, msg);
    }
}
