import java.util.Objects;

class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 8;
    private static final double RATE_INCREASE_CAPACITY = 1.5;
    
    private int size;
    private T[] arrayData;
    
    MyArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Размер массива должен быть неотрицательным: "
                                                + capacity);
        }
        arrayData = (T[]) new Object[capacity];
        size = 0;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return arrayData[index];
    }
    
    public void set(int index, T o) {
        Objects.checkIndex(index, size);
        arrayData[index] = o;
    }    

    public void add(T o) {
        add(size, o);
    }
    
    public void add(int index, T o) {
        Objects.checkIndex(index, size + 1);
        if (size == arrayData.length) increaseCapacity();
        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = o;
        size++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(arrayData, index + 1, arrayData, index, size - index - 1);
        size--;
    }
    
    public MyArrayList<Integer> findAll(T o) {
        var indexItem = new MyArrayList<Integer>(1);        
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arrayData[i] == null) indexItem.add(i);
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arrayData[i])) {
                    indexItem.add(i);
                }
            }
        }
        indexItem.restrictCapacity();
        return indexItem;
    }

    public void restrictCapacity() {
        if (arrayData.length > size) {
            @SuppressWarnings("unchecked")
            T[] newData = (T[]) new Object[size];
            System.arraycopy(arrayData, 0, newData, 0, size);
            arrayData = newData;
        }
    }
    
    public int size() {
        return size;
    }
    
    private void increaseCapacity() {
        int newCapacity = (int) (arrayData.length * RATE_INCREASE_CAPACITY + 1);
        @SuppressWarnings("unchecked")
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(arrayData, 0, newData, 0, size);
        arrayData = newData;
    }
    
    public void print() {
        System.out.printf("Размер массива: %d; " +
                          "Количество элементов: %d\n%s\n\n",
                           arrayData.length, size, toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            result.append(Objects.toString(arrayData[i], "null"));
            result.append(" ");
        }
        return result.toString();
    }

}
