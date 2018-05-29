package com.n26.txnstatastics.domain;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class CacheConcurrentHashMap<K, V> extends ConcurrentHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private Map<K, Long> tmpMapforTime = new ConcurrentHashMap<K, Long>();
    private long expireTimings = 0;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss:SSS");


    public CacheConcurrentHashMap(long expireTimings) {
        this.expireTimings = expireTimings;
        initialize();
    }

    void initialize() {
        new ElementRemovalThread().start();
    }

    @Override
    public V put(K key, V value) {
        Date date = new Date();
        tmpMapforTime.put(key, date.getTime());
        System.out.println("Inserting  elements in the cache map: " + date.getTime() + " : " + key + " : " + value);
        V returnVal = super.put(key, value);
        return returnVal;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (K key : m.keySet()) {
            put(key, m.get(key));
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        if (!containsKey(key))
            return put(key, value);
        else
            return get(key);
    }

    class ElementRemovalThread extends Thread {
        @Override
        public void run() {
            System.out.println("Initiating Cleaner Thread..");
            while (true) {
                removeElementAfterSomeTime();
                try {
                    Thread.sleep(expireTimings / 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void removeElementAfterSomeTime() {
            long currTime = new Date().getTime();
            for (K key : tmpMapforTime.keySet()) {
                if (currTime > (tmpMapforTime.get(key) + expireTimings)) {
                    V value = remove(key);
                    tmpMapforTime.remove(key);
                    System.out.println("Removing element which passed 60 seconds : " + new Date().toString() + " : " + key + " : " + value);
                }
            }
        }
    }
}