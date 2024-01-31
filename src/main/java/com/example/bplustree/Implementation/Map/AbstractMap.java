package com.example.bplustree.Implementation.Map;

import java.util.Iterator;

abstract class AbstractMap<K, V> implements Map<K, V> {

    public boolean isEmpty() {
        return size() == 0;
    }

    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K k; // key
        private V v; // value

        public MapEntry(K key, V value) {
            k = key;
            v = value;
        }
        // public methods of the Entry interface

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        // utilities not exposed as part of the Entry interface
        protected void setKey(K key) {
            k = key;
        }

        protected V setValue(V value) {
            V old = v;
            v = value;
            return old;
        }

    } //end of nested MapEntry class

    // Support for public keySet method...
    private class Keylterator implements Iterator<K> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        public boolean hasNext() {
            return entries.hasNext();
        }

        public K next() {
            return entries.next().getKey();
        } // return key!

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Keylterable implements Iterable<K> {

        public Iterator<K> iterator() {
            return new Keylterator();
        }
    }

    public Iterable<K> keySet() {
        return new Keylterable();
    }

    // Support for public values method...
    private class Valuelterator implements Iterator<V> {

        private Iterator<Entry<K, V>> entries = entrySet().iterator(); // reuse entrySet

        public boolean hasNext() {
            return entries.hasNext();
        }

        public V next() {
            return entries.next().getValue();
        }

        // return value!
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Valuelterable implements Iterable<V> {
        public Iterator<V> iterator() {
            return new Valuelterator();
        }
    }

    public Iterable<V> values() {
        return new Valuelterable();
    }
}