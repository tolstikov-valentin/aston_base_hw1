class MyHashMap<K, V> {
    
    private static final int DEFAULT_CAPACITY = 1 << 3;
    private static final double DEFAULT_LOAD_FACTOR = 2.0;
    
    private Node<K, V>[] hashTable;
    private int size;
    private double loadFactor;
    
    @SuppressWarnings("hiding")
    private class Node<K, V> {
        int hashKey;
        K key;
        V value;
        Node<K, V> nextNode;
        
        Node(int hashKey, K key, V value, Node<K, V> nextNode) {
            this.hashKey = hashKey;
            this.key = key;
            this.value = value;
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "(key = %s, value = %s); ".formatted(key == null ? "null" : key.toString(),
                                                        value.toString());
        }
    }
    
    MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    
    @SuppressWarnings("unchecked")
    MyHashMap(int capacity, double loadFactor) {
        hashTable = (Node<K, V>[]) new Node[capacity];
        size = 0;
        this.loadFactor = loadFactor;
    }
    
    private Node<K, V> findNode(K key) {
        int hashKey = key == null ? 0 : key.hashCode();
        int index = hashKey & (hashTable.length - 1);
        for (Node<K, V> node = hashTable[index]; node != null; node = node.nextNode) {
            if (node.hashKey == hashKey) {
                if (matchObjects(node.key, key)) {
                    return node;
                }
            }
        }
        return null;
    }
    
    public void put(K key, V value) {
        Node<K, V> node = findNode(key);
        if (node != null) {
            node.value = value;
        } else {
            int hashKey = key == null ? 0 : key.hashCode();
            int index = hashKey & (hashTable.length - 1);
            addNode(index, hashKey, key, value, hashTable);
        }
        if (size > loadFactor * hashTable.length) increaseCapacity();
    }
    
    public V get(K key) {
        Node<K, V> node = findNode(key);
        return node == null ? null : node.value;
    }

    public boolean containsKey(K key) {
        Node<K, V> node = findNode(key);
        return node == null ? false : true;
    }
    
    public boolean containsValue(V value) {
        for (int i = 0; i < hashTable.length; i++) {
            for (Node<K, V> node = hashTable[i]; node != null; node = node.nextNode) {
                if (matchObjects(node.value, value)) return true;
            }
        }
        return false;
    }
    
    public V remove(K key) {
        int hashKey = key == null ? 0 : key.hashCode();
        int index = hashKey & (hashTable.length - 1);
        
        if (hashTable[index] == null) return null;
        
        if (hashTable[index].hashKey == hashKey) {
            if (matchObjects(hashTable[index].key, key)) {
                V removeValue = hashTable[index].value;
                hashTable[index] = hashTable[index].nextNode;
                size--;
                return removeValue;
            }

        } else {
            for (Node<K, V> node = hashTable[index]; node != null; node = node.nextNode) {
                
                if (node.nextNode == null) return null;
                
                if (node.nextNode.hashKey == hashKey) {
                    if (matchObjects(node.nextNode.key, key)) {
                        V removeValue = node.nextNode.value;
                        node.nextNode = node.nextNode.nextNode;
                        size--;
                        return removeValue;
                    }
                }
            }
        }
        return null;
    }
    
    public void clear() {
        if (size > 0) {
            for (int i = 0; i < hashTable.length; i++) {
                hashTable[i] = null;
            }
        }
        size = 0;
    }
    
    public int size() {
        return size;
    }
    
    private boolean matchObjects(Object o1, Object o2) {
        return (o1 == o2 || o1 != null && o1.equals(o2));
    }
    
    private void addNode(int index, int hashKey, K key, V value, Node<K, V>[] hashTable) {
        Node<K, V> oldNode = hashTable[index];
        hashTable[index] = new Node<K, V>(hashKey, key, value, oldNode);
        size++;
    }
    
    private void increaseCapacity() {
        @SuppressWarnings("unchecked")
        Node<K, V>[] newHashTable = (Node<K, V>[]) new Node[hashTable.length << 1];
        size = 0;
        for (int i = 0; i < hashTable.length; i++) {
            for (Node<K, V> node = hashTable[i]; node != null; node = node.nextNode) {
                int index = node.hashKey & (newHashTable.length - 1);
                addNode(index, node.hashKey, node.key, node.value, newHashTable);
            }
        }
        hashTable = newHashTable;
    }
    
    public void print() {
        System.out.printf("Размер хеш-таблицы: %d; " +
                          "Количество элементов: %d\n%s\n",
                           hashTable.length, size, toString());
    }

    @Override
    public String toString() {
        var result = new StringBuilder("");
        for (int index = 0; index < hashTable.length; index++) {
            result.append("HashMap index: %d, ".formatted(index));
            for (Node<K, V> node = hashTable[index]; node != null; node = node.nextNode) {
                result.append(node.toString());
            }
            result.append("\n");
        }
        return result.toString();
    }

}
