package java_code;

import java.util.Arrays;
import java.util.logging.Logger;


class MyVector {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_CAPACITY_INCREMENT = 2;

    private int[] vector;
    private int size;
    private int capacity;
    private int initialCapacity;
    private int capacityIncrement;

    public MyVector() {
        this.initialCapacity = DEFAULT_INITIAL_CAPACITY;
        this.capacityIncrement = DEFAULT_CAPACITY_INCREMENT;
        this.size = 0;
        this.capacity = initialCapacity;
        this.vector = new int[initialCapacity];
    }

    public MyVector(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.capacityIncrement = DEFAULT_CAPACITY_INCREMENT;
        this.size = 0;
        this.capacity = initialCapacity;
        this.vector = new int[initialCapacity];
    }

    public MyVector(int initialCapacity, int capacityIncrement) {
        this.initialCapacity = initialCapacity;
        this.capacityIncrement = capacityIncrement;
        this.size = 0;
        this.capacity = initialCapacity;
        this.vector = new int[initialCapacity];
    }

    /**
     * @return number of items in vector
     */
    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int at(int index) {
        return this.vector[index];
    }

    public void push(int value) {
        if (size < capacity) {
            this.vector[size] = value;
        } else {
            resize(capacity * capacityIncrement);
            this.vector[size] = value;
        }
        size++;
    }

    public void insert(int index, int value) {
        if (this.size >= this.capacity)
            this.resize(this.capacity * this.capacityIncrement);

        int prev = this.vector[value];
        this.vector[index] = value;

        for (int i = index + 1; i < this.size; i++) {
            int temp = this.vector[i];
            this.vector[i] = prev;
            prev = temp;
        }
        size++;
    }

    public void prepend(int value) {
        this.insert(0, value);
    }

    public int pop() {
        int temp = this.at(size);
        this.delete(size);
        return temp;

    }

    public void delete(int index) {

        for (int i = index + 1; i < this.size; i++) {
            this.vector[index] = this.vector[i];
            index++;
        }

        this.vector[this.size] = 0;
        this.size--;

        if (this.size * 4 < this.capacity) {
            resize(capacity / 2);
        }

    }

    public int remove(int value) {
        int index = 0;
        int counter = 0;
        while (index < this.size) {
            if (this.vector[index] == value) {
                delete(index);
                counter++;
            } else {
                index++;
            }
        }

        return counter;
    }

    public int find(int value) {
        for (int i = 0; i < this.size; i++) {
            if (this.vector[i] == value)
                return i;
        }
        return -1;
    }

    private void resize(int newLength) {
        this.capacity = newLength;
        this.vector = Arrays.copyOf(this.vector, newLength);
    }

    public static void main(String[] args) {
        MyVector myVector = new MyVector();

        assert myVector.isEmpty();

        for (int i = 0; i < 50; i++) {
            myVector.push(i);
            assert i == myVector.size;
        }

        for (int i = 0; i < myVector.size; i++) {
            System.out.println(myVector.at(i));
        }

        System.out.println(myVector.size);
        System.out.println(myVector.capacity);
        
        for (int i = 0; i < 50; i += 2) {
            myVector.remove(i);
        }

        for (int i = 0; i < myVector.size ; i++) {
            System.out.println(myVector.at(i));

        }
        System.out.println(myVector.size);
        System.out.println(myVector.capacity);

    }

}