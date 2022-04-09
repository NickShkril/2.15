package pro.sky;


import java.util.Arrays;


public class IntegerListImpl implements IntegerList {

    private static final int DEFAULT_CAPACITY = 4;

    private Integer[] array;
    private int size;

    public IntegerListImpl() {
        this(DEFAULT_CAPACITY);
    }

    public IntegerListImpl(int capacity) {
        this.array = new Integer[capacity];
    }

    @Override
    public Integer add(Integer item) {
        checkNotNull(item);
        checkCapacity();
        array[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, int item) {
        checkNotNull(item);
        checkIndex(index);
        checkCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        array[index] = item;
        return item;
    }

    @Override
    public Integer removeA(Integer item) {
        checkNotNull(item);
        checkItemExist(item);
        checkCapacity();
        int index = indexOf(item);
        removeItem(index);
        return item;
    }

    @Override
    public Integer removeB(int index) {
        checkIndex(index);
        Integer item = get(index);
        removeItem(index);
        return item;
    }

    @Override
    public void removeItem(Integer index) {
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }
        array[--size] = null;
    }


    @Override
    public boolean contains(Integer item) {
        sort();
        return binarySearch(item);
    }

    private void sort() {
        SortUtils.sortInsertion(array);
    }

    private boolean binarySearch(Integer item) {
        checkNotNull(item);
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(array[mid])) {
                return true;
            }
            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        checkNotNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            return false;
        }
        return Arrays.equals(toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        array = new Integer[DEFAULT_CAPACITY];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public String toString() {
        return "StringListImpl{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    private Integer[] grow() {
        return Arrays.copyOf(array, (int) (size * 1.5));
    }

    private Integer[] resize() {
        return Arrays.copyOf(array, (array.length * 2 / 3));
    }

    private void checkCapacity() {
        if (size == array.length) {
            array = grow();
        } else if (size < array.length / 2) {
            array = resize();
        }
    }

    private void checkNotNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Null");
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть положительный и больше " + size);
        }
    }

    private void checkItemExist(Integer item) {
        if (indexOf(item) == -1) {
            throw new IllegalArgumentException("Объекта " + item + " нет в списке");
        }
    }
}

