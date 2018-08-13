package com.manning.logic;

import com.google.common.collect.Lists;

import java.util.*;


public class GzcCustomerService {
    public final static Long mumuBeijing = 1002495141949L;  //city = 1101  北京
    public final static Long mumuHuadong = 1002495141951L; //city = 3101  上海
    public final static Long mumuHuanan = 1002495158713L;  //city = 4401  广州
    public final static Long mumuHuazhong = 1002495158714L;//city = 4201  武汉
    public final static Long mumuXibei = 1002495158715L;   //city = 6101  西安
    public final static Long mumuXinan = 1002495158716L;   //city = 5001  重庆
    public final static Long mumuDongbei = 1002495158717L; //city = 2102  大连
    public final static Long mumuHuabei = 1002495160596L;   //city = 1201  天津

    public final static Long zpcService = 1002493226282L;
    public final static List<Long> mumuList =
            Lists.newArrayList(mumuBeijing,mumuHuabei,mumuHuadong, mumuHuanan,mumuHuazhong,mumuXibei,mumuXinan,mumuDongbei,zpcService);


    public final static Map<Integer, Long> cityMumuMap = new LinkedHashMap<>(100);
    public final static Map<Long, ArrayList<Integer>> mumuCityMap = new LinkedHashMap<>(32);
    public final static List<Integer> commonFeedSuffixList = new ArrayList<>(10);

    static {
        cityMumuMap.put(1101, mumuBeijing);  //北京
        cityMumuMap.put(3101, mumuHuadong);  //上海
        cityMumuMap.put(4401, mumuHuanan);
        cityMumuMap.put(4403, mumuHuanan);
        cityMumuMap.put(1201, mumuHuabei);
        cityMumuMap.put(5001, mumuXinan);
        cityMumuMap.put(4201, mumuHuazhong);
        cityMumuMap.put(5101, mumuXinan);
        cityMumuMap.put(3301, mumuHuadong);
        cityMumuMap.put(4101, mumuHuazhong);
        cityMumuMap.put(3701, mumuHuadong);
        cityMumuMap.put(6101, mumuXibei);

        cityMumuMap.put(2102, mumuDongbei);
        cityMumuMap.put(3501, mumuHuanan);
        cityMumuMap.put(4501, mumuHuanan);
        cityMumuMap.put(4419, mumuHuanan);
        cityMumuMap.put(3502, mumuHuadong);
        cityMumuMap.put(3202, mumuHuadong);
        cityMumuMap.put(3702, mumuHuadong);
        cityMumuMap.put(1401, mumuHuabei);
        cityMumuMap.put(1301, mumuHuabei);
        cityMumuMap.put(3201, mumuHuadong);
        cityMumuMap.put(3401, mumuHuadong);
        cityMumuMap.put(3205, mumuHuadong);
        cityMumuMap.put(2201, mumuDongbei);
        cityMumuMap.put(4301, mumuHuazhong);
        cityMumuMap.put(2301, mumuDongbei);
        cityMumuMap.put(2101, mumuDongbei);
        cityMumuMap.put(1405, mumuHuabei);
        cityMumuMap.put(1501, mumuHuabei);

        cityMumuMap.put(1302, mumuHuabei);
        cityMumuMap.put(1303, mumuHuabei);
        cityMumuMap.put(1304, mumuHuabei);
        cityMumuMap.put(1305, mumuHuabei);
        cityMumuMap.put(1307, mumuHuabei);
        cityMumuMap.put(1308, mumuHuabei);
        cityMumuMap.put(1309, mumuHuabei);
        cityMumuMap.put(1310, mumuHuabei);
        cityMumuMap.put(1311, mumuHuabei);
        cityMumuMap.put(1402, mumuHuabei);
        cityMumuMap.put(1404, mumuHuabei);
        cityMumuMap.put(1407, mumuHuabei);
        cityMumuMap.put(1408, mumuHuabei);
        cityMumuMap.put(1410, mumuHuabei);

        cityMumuMap.put(1306, mumuHuabei);
        cityMumuMap.put(3204, mumuHuadong);
        cityMumuMap.put(3203, mumuHuadong);
        cityMumuMap.put(3206, mumuHuadong);
        cityMumuMap.put(3302, mumuHuadong);
        cityMumuMap.put(3303, mumuHuadong);
        cityMumuMap.put(3210, mumuHuadong);
        cityMumuMap.put(3307, mumuHuadong);
        cityMumuMap.put(4406, mumuHuanan);
        cityMumuMap.put(4420, mumuHuanan);
        cityMumuMap.put(4404, mumuHuanan);
        cityMumuMap.put(4413, mumuHuanan);
        cityMumuMap.put(3505, mumuHuadong);
        cityMumuMap.put(3601, mumuHuadong);
        cityMumuMap.put(5201, mumuXinan);
        cityMumuMap.put(5107, mumuXinan);
        cityMumuMap.put(5301, mumuXinan);
        cityMumuMap.put(4103, mumuHuazhong);
        cityMumuMap.put(4107, mumuHuazhong);
        cityMumuMap.put(4113, mumuHuazhong);
        cityMumuMap.put(3713, mumuHuadong);
        cityMumuMap.put(3714, mumuHuadong);
        cityMumuMap.put(3707, mumuHuadong);
        cityMumuMap.put(6201, mumuXibei);

        for (Map.Entry<Integer, Long> entry : cityMumuMap.entrySet()) {
            Integer sh = entry.getKey();
            Long id = entry.getValue();

            if (mumuCityMap.containsKey(id)) {
                ArrayList<Integer> list = mumuCityMap.get(id);
                list.add(sh);
            } else {
                mumuCityMap.put(id, Lists.newArrayList(sh));
            }
        }

        for (ArrayList<Integer> list : mumuCityMap.values()) {
            commonFeedSuffixList.add(list.get(0));
        }
    }

    /**
     * 通过城市获取mumuId(对应DB中的value). eg: 1101 -> mumuHuabei
     */
    public static Long getMumuId(final Integer city) {
        final Long mumuId = cityMumuMap.get(city);

        if (mumuId == null) {
            return mumuHuabei;
        }

        return mumuId;
    }

    /**
     * 通过mumuId获取城市列表. eg: mumuHuabei -> 1101,1201...
     */
    public static ArrayList<Integer> getCityList(final Long mumuId) {

        final ArrayList<Integer> cityIds = mumuCityMap.get(mumuId);

        if (cityIds == null || cityIds.isEmpty()) {
            return mumuCityMap.get(mumuHuabei);
        }

        return cityIds;
    }

    /**
     * 通过城市获取公共的发现动态ID
     */
    public static Integer getCommonFeedSuffix(final Integer city) {
        Long mumuId = getMumuId(city);
        ArrayList<Integer> cityList = getCityList(mumuId);
        return cityList.get(0);
    }
}
