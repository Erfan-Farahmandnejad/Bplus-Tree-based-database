package com.example.bplustree.Implementation.Map;

interface Map<K, V> {
    int size();

    boolean isEmpty();

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    Iterable<K> KeySet();

    Iterable<V> Values();

    Iterable<Entry<K, V>> entrySet();
}