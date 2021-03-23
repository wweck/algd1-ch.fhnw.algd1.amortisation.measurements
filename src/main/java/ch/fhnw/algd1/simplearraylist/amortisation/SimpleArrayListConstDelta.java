package ch.fhnw.algd1.simplearraylist.amortisation;

import java.util.AbstractList;
import java.util.List;

public class SimpleArrayListConstDelta<E> extends AbstractList<E> implements List<E> {
	private static final int MIN_ARRAY_LEN = 64;
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
		Object[] newArr = new Object[arr.length + MIN_ARRAY_LEN];
		for (int i = 0; i < size; i++) {
			newArr[i] = arr[i];
		}
		arr = newArr;
	}

	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (size == arr.length) enlarge();
		int i = size;
		while (i > index) {
			arr[i] = arr[i - 1];
			i--;
		}
		arr[index] = element;
		size++;
	}

	private void shrink() {
		Object[] newArr = new Object[arr.length - MIN_ARRAY_LEN];
		for (int i = 0; i < size; i++)
			newArr[i] = arr[i];
		arr = newArr;
	}

	@Override
	public E remove(int index) {
		E old = get(index); // includes index checking
		while (index + 1 < size) {
			arr[index] = arr[index + 1];
			index++;
		}
		size--;
		arr[size] = null;
		if (arr.length > MIN_ARRAY_LEN && size <= arr.length - 3 * MIN_ARRAY_LEN / 2) shrink();
		return old;
	}
}
