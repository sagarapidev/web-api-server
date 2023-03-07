package com.webapiserver.common;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)

public final class SpecialCharConstant {
    public static final String PERIOD = ".";
    public static final String COMMA = ",";
    public static final String EMPTY_SINGLE_SPACE = " ";
    public static final String DEFAULT_TAB_SPACE = "    ";
    public static final String UNDERSCORE = "_";
    public static final String HYPHEN = "-";
    public static final String SINGLE_QUOTE ="\"" ;
    public static final String DOUBLE_BACKWARD_SLASH ="\\" ;
    public static final String DOUBLE_FORWARD_SLASH ="//" ;
    public static final String NULL_STR ="null" ;
    public static String DOUBLE_QUOTE="\"\"";
    public static String NULL=null;
}
