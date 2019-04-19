package com.mutaz.ds.arrays;

import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
	private static final int DEFAUTL_CAPACITY = 16;

	private T[] data;
	private int size;
	private int capacity;

	public DynamicArray() {
		this(DEFAUTL_CAPACITY);
	}

	public DynamicArray(int initalCapacity) {
		this.capacity = initalCapacity;
		this.size = 0;
		this.data = (T[]) new Object[initalCapacity];
	}

	public void add(T item) {
		/*
		 * + 1 is needed here since arrays are zero based, if the capacity is 16 and the array is full 
		 * the size will be 15 
		 */
		if (size + 1 == capacity) {
			if(capacity == 0) capacity = 1;
			// shorter than this -> capacity = capacity * 2;
			else capacity *= 2;
			
			T[] newData = (T[]) new Object[capacity];
			for (int i = 0; i < size; i++) {
				newData[i] = data[i];
			}
		}
		data[size++] = item;
	}

	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return data[index];
	}

	public void set(int index, T item) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		data[index] = item;
	}

	public void remove(int index) {
		T[] newData = (T[]) new Object[capacity];
		for(int i=0,j=0;i<size;i++,j++) {
			if(i == index) j--;
			else newData[j] = data[i]; 	
		}
		data = newData;
		size--;
	}

	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		this.size = 0;
	}

	public int size() {
		return this.size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < size;
			}

			@Override
			public T next() {
				/*
				T item = data[currentIndex];
				currentIndex++;
				return item;
				*/
				return data[currentIndex++];

			}
		};
	}

	@Override
	public String toString() {
		return "DynamicArray [data=" + Arrays.toString(data) + ", size=" + size + ", capacity=" + capacity + "]";
	}
}
