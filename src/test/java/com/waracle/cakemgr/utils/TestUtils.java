package com.waracle.cakemgr.utils;

import com.waracle.cakemgr.model.Cake;

public class TestUtils {

    public static Cake getTestCake() {
        return new Cake(1L, "testCake", "description", "imageUrl");
    }
}
