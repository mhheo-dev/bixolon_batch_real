package com.bixpos.batch.project.common;

import org.springframework.lang.Nullable;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

/**
 * StringUtils Customizing
 * 
 * @author jaebeom(ITR Solution)
 *
 */
public class StringUtils {

    /**
     * String 변수가 비워있는지 체크한다. null인 경우에도 비워 있는 것으로 판단한다.
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(@Nullable String str) {
        return (str == null || str.isEmpty());
    }
    
    /**
     * Object 변수가 비워 있는지 체크한다. null인 경우에도 비워 있는 것으로 판단한다.
     * 
     * @param obj
     * @return
     */
    public static boolean isEmpty(@Nullable Object obj) {
        return (obj == null || obj.toString().isEmpty());
    }
    
    /**
     * String 변수가 공백인지 체크한다. null인 경우에도 공백으로 판단한다.
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(@Nullable String str) {
        return (str == null || str.isBlank());
    }
    
    /**
     * Object 변수가 공백인지 체크한다. null인 경우에도 공백으로 판단한다.
     * 
     * @param obj
     * @return
     */
    public static boolean isBlank(@Nullable Object obj) {
        return (obj == null || obj.toString().isBlank());
    }

    /**
     * String 변수의 값이 있는지(문자열 길이가 0 보다 큰지) 체크한다. null인 경우에는 값이 없는 것으로 판단한다.
     * isEmpty()와 반대 되는 개념이다.
     * 
     * @param str
     * @return
     */
    public static boolean hasLength(@Nullable String str) {
        return org.springframework.util.StringUtils.hasLength(str);
    }    

    public static String defaultString(String str) {
        return str == null ? "" : str;
    }

    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }
    
    /**
     * 사용 예)
     *   System.out.println(format("96506550","#####-###"));
     * @param data
     * @param mask
     * @return
     */
    public static String format(String data, String mask) {
        String ret = "";
        try {
            MaskFormatter mf;
            mf = new MaskFormatter(mask);
            mf.setPlaceholderCharacter('_');
            ret = mf.valueToString(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 문자열의 오른쪽에 공백을 채운다.
     * @param str
     * @param size
     * @return
     */
    public static String rightPad(final String str, final int size) {
        return org.apache.commons.lang3.StringUtils.rightPad(str, size);
    }
}
