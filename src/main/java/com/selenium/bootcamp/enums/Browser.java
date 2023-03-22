package com.selenium.bootcamp.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum Browser {
    Chrome("Chrome"),
    Firefox("Firefox"),
    IE("IE"),
    Edge("Edge"),
    Chromium("Chromium");

    private static final Map<String, Browser> enumMAP;

    static {
        Map<String, Browser> mainNavMap = Arrays
                .stream(values())
                .collect(toMap(cg -> cg.browserName, e -> e));

        enumMAP = Collections.unmodifiableMap(mainNavMap);
    }

    private final String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public static Browser getEnum(String browserName) {
        return enumMAP.get(browserName);
    }

    public String getBrowserName() {
        return browserName;
    }
}
