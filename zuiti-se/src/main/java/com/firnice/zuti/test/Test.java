//package com.firnice.zuti.test;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.SortedMap;
//
//public class Test {
//    /**
//     * 编程题目: 假设有100亿全球数据，数据内容为IMEI，APP名称，浏览时间（时间戳），地域，时区; 现在想求出每个地域，日落时候访问次数最多的APP TOP10
//     * 存在磁盘 一行一条数据，用竖线分隔
//     *
//     * count(imei) 访问次数
//     * where 日落时间：6：00 - 7：00
//     * group 地域 hash\APP名称
//     * limit 10
//     *
//     *
//     * 分块，每块查询TOP10
//     */
//
//
//    /**
//     * Map<"地域", SortedMap<"APP名称","count(imei)">>
//     *
//     * @param args
//     * @throws IOException
//     */
//    static Map<String, SortedMap<String, Long>> map = new HashMap();
//
//    public static void main(String[] args) throws IOException {
//
//
//        //分治
//        FileInputStream file = new FileInputStream("xxx");
//
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
//        //todo
//        String s = bufferedReader.readLine();
//        readLine(s);
//
////        //循环
////        Set<Map.Entry<String, SortedMap<String, Long>>> entries = map.entrySet();
////        for (Map.Entry<String, SortedMap<String, Long>> entry : entries) {
////            SortedMap<String, Long> value = entry.getValue();
////            Set<String> strings = value.keySet();
////            strings.forEach(s1 -> {
////                value.get(s1);
////            });
////
////        }
//
//    }
//
//    public static void readLine(String line) {
//        // IMEI，APP名称，浏览时间（时间戳），地域，时区;
//        String[] split = line.split("|");
//        String imei = split[0];
//        String app = split[1];
//        String time = split[2]; //时间戳根据时间格式化
//        String addr = split[3];
//        String zoneId = split[4];
//        //todo 时间戳根据时间格式化
//
//
//        //包含这个地域
//        if (map.containsKey(addr)) {
//            SortedMap<String, Long> stringLongSortedMap = map.get(addr);
//            if (stringLongSortedMap.containsKey(app)) {
//                stringLongSortedMap.put(app, stringLongSortedMap.get(app) + 1);
//            } else {
//                //没有此app
//                stringLongSortedMap.put(app, 1L);
//            }
//        } else {
//            //不包含这个地域
//            SortedMap<String, Long> stringLongSortedMap = new SortedMap<String, Long>();
//            stringLongSortedMap.put(app, 1L);
//            map.put(addr, stringLongSortedMap);
//        }
//
//
//    }
//
//}
