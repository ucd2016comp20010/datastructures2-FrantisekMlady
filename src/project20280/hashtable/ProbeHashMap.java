package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.LinkedList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    int findSlot(int h, K k) {
        int i = h;
        int firstAvailableSlot = -1;
        do {

             if(table[i] == null){
                if(firstAvailableSlot == -1){
                    return i;
                }
                return firstAvailableSlot;
            }
            else if(table[i] == DEFUNCT){
                if(firstAvailableSlot == -1){
                    firstAvailableSlot = i;
                }
            }
            else if (table[i].getKey().equals(k)){
                return i;
            }

            i = (i+1)%capacity;
        } while (i!=h);

        return firstAvailableSlot;
    }

    @Override
    protected V bucketGet(int h, K k) {
        int slot = findSlot(h, k);

        if (slot == -1 || table[slot] == null || table[slot] == DEFUNCT) {
            return null;
        }
        return table[slot].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int slot = findSlot(h, k);
        if (table[slot] == null || table[slot] == DEFUNCT) {
            table[slot] = new MapEntry<>(k, v);
            return null;
        } else {
            V old = table[slot].getValue();
            table[slot].setValue(v);
            return old;
        }
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int slot = findSlot(h, k);
        if (table[slot] == null || table[slot] == DEFUNCT) {
            return null;
        } else {
            V old = table[slot].getValue();
            table[slot] = DEFUNCT;
            n--;
            return old;
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        LinkedList<Entry<K, V>> entries = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null && table[i] != DEFUNCT) {
                entries.add(table[i]);
            }
        }
        return entries;
    }
}
