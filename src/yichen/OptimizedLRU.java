package yichen;

import java.util.HashMap;

class LRUNode {
    int key;
    int value;
    LRUNode next;
    LRUNode prev;

    public LRUNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class OptimizedLRU {
    // 1 2 3 4 5
    // 1 2
    // 2 1
    public static void main(String[] args) {
        var lruCache = new OptimizedLRU(3);
        lruCache.put(1,2);
        lruCache.put(2,3);
        lruCache.put(3,4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(1) == 2);
        lruCache.put(4,5);
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(2) == -1);
    }

    private final HashMap<Integer, LRUNode> map = new HashMap<>();

    private LRUNode last = null;
    private LRUNode head = null;

    private final int capacity;

    public OptimizedLRU(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        var result = map.get(key);
        updateKey(result);
        return result == null? -1: result.value;
    }

    public void put(int key, int value) {
        var node = map.get(key);
        if(node != null){
            node.value = value;

            updateKey(node);
        } else {
            var newNode = new LRUNode(key, value);
            map.put(key, newNode);

            if(head == null){
                head = newNode;
            }

            if(last != null){
                newNode.prev = last;
                last.next = newNode;
            }

            last = newNode;

            if(map.size() > capacity){
                map.remove(head.key);
                if(head.next != null){
                    head = head.next;
                }
            }
        }
    }

    private void updateKey(LRUNode node){
        if(node == null){return;}

        if(node == head && node == last){
        } else if(node == head && node != last){
            node.next.prev = null;
            head = node.next;
            node.next = null;
            node.prev = last;
            last.next = node;
            last = node;
        } else if(node != head && node == last){
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = last;
            last.next = node;
            node.next = null;
            last = node;
        }
    }
}
