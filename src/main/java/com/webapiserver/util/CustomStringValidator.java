package com.webapiserver.util;


import com.webapiserver.common.SpecialCharConstant;
import lombok.extern.slf4j.Slf4j;


@SuppressWarnings("ALL")
@Slf4j
public class CustomStringValidator {

    public static boolean emptyORnull(String value) {
       boolean itsTrue = false;
        if (value.equals(SpecialCharConstant.SINGLE_QUOTE)
                ||
                String.valueOf(value).toLowerCase().equals(SpecialCharConstant.NULL_STR)
                ||
                String.valueOf(value).toLowerCase().equals(SpecialCharConstant.DOUBLE_QUOTE)
                ||
                String.valueOf(value).equals(SpecialCharConstant.NULL)
        ) {
            itsTrue = true;
            return itsTrue;

        }
       return itsTrue;
    }
}