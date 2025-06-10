package test;

import java.util.*;

public class HashMapAndTreeMapTest {
//    逐个put: 91.9687 ms
//    构造器: 24.9653 ms
    // 构造器的方式会更快
    public static void main(String[] args) {


// 方式一：TreeMap直接插入
        long t1 = System.nanoTime();
        TreeMap<Integer, List<Integer>> tree1 = new TreeMap<>();
//        for (var entry : hash.entrySet()) {
//            tree1.put(entry.getKey(), entry.getValue());
//        }
        for (int i = 0; i < 1_000_000; i++) {
            tree1.computeIfAbsent(i % 10000, k -> new ArrayList<>()).add(i);
        }
        long t2 = System.nanoTime();
        System.out.println("逐个put: " + (t2 - t1)/1e6 + " ms");

// 方式二：TreeMap(Map)
        long t3 = System.nanoTime();
        Map<Integer, List<Integer>> hash = new HashMap<>();
        for (int i = 0; i < 1_000_000; i++) {
            hash.computeIfAbsent(i % 10000, k -> new ArrayList<>()).add(i);
        }
        TreeMap<Integer, List<Integer>> tree2 = new TreeMap<>(hash);
        long t4 = System.nanoTime();
        System.out.println("构造器: " + (t4 - t3)/1e6 + " ms");
    }
}
