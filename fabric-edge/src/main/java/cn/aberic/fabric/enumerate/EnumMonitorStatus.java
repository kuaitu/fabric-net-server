package cn.aberic.fabric.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumMonitorStatus {
    TRUE("1","活动"),
    FALSE("0","停止");

    String code;
    String value;

    public static EnumMonitorStatus getStatus(boolean isOpen){
        if (isOpen) {
            return TRUE;
        }
        return FALSE;
    }
}
