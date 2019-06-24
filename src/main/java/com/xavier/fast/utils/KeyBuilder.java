//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xavier.fast.utils;

import org.apache.commons.lang3.StringUtils;

public final class KeyBuilder {
    public static final char SPLIT = ':';

    private KeyBuilder() {
    }

    public static String build(Object... array) {
        return StringUtils.join(array, ':');
    }
}
