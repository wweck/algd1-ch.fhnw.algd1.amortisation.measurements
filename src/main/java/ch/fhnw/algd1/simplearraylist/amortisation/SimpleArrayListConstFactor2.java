package ch.fhnw.algd1.simplearraylist.amortisation;

import java.util.AbstractList;
import java.util.List;

public class SimpleArrayListConstFactor2<E> extends AbstractList<E> implements List<E> {
	private static final int MIN_ARRAY_LEN = 16;
	private Object[] arr = new Object[MIN_ARRAY_LEN];
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return (E)arr[index];
	}

	@Override
	public E set(int index, E element) {
		E old = get(index); // includes index checking
		arr[index] = element;
		return old;
	}

	private void enlarge() {
		Object[] newArr = new Object[arr.length * 2];
		System.arraycopy(arr, 0, newArr, 0, size);
		arr = newArr;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (size == arr.length) enlarge();
		System.arraycopy(arr, index, arr, index + 1, size - index);
		arr[index] = element;
		size++;
	}

	private void shrink() {
		Object[] newArr = new Object[arr.length / 2];
		System.arraycopy(arr, 0, newArr, 0, size);
		arr = newArr;
	}

	@Override
	public E remove(int index) {
		E old = get(index); // includes index checking
		System.arraycopy(arr, index + 1, arr, index, size - index - 1);
		size--;
		arr[size] = null;
		if (arr.length > MIN_ARRAY_LEN && size <= arr.length / 3) shrink();
		return old;
	}
}
