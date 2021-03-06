package com.mosaiker.manage_hean.Util;

import com.alibaba.fastjson.JSONArray;
import com.mosaiker.manage_hean.Entity.Hean;

import java.util.List;

public class HeanUtil {

    public static JSONArray convertHeanListToJson(List<Hean> heanList) {
        JSONArray json = new JSONArray();
        heanList.forEach(hean -> json.add(hean));
        return json;
    }
}
