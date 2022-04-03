package pro.sky;


import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {

    private String[] array = new String[10];
    private int size = 0;

    @Override
    public String add(String item) {
        if (size < array.length) {
            String[] arrayB = new String[array.length * 2];
            System.arraycopy(array, 0, arrayB, 0, array.length);
            array = arrayB;
        }
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        add(item);
        for (int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        String str = get(index);
        array[index] = item;
        return str;
    }

    @Override
    public String remove(String item) {
        int start = -1;
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                start = i;
                break;
            }
        }
        if (start != -1) {
            remove(start);
        } else {
            throw new NotFoundException("Object not found");
        }
        return item;
    }

    @Override
    public String remove(int index) {
        String end = get(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return end;
    }

    @Override
    public boolean contains(String item) {
        boolean contains = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(item, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        int last = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                last = i;
                break;
            }
        }
        return last;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        boolean result = true;
        if (this.size != otherList.size()) {
            return false;
        } else {
            for (int i = 0; i < this.size; i++) {
                if (!this.get(i).equals(otherList.get(i))) {
                    result = false;
                    break;
                }
            }
            return result;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;
        for (String str : array) {
            if (str != null) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        array = new String[10];
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] newArray = new String[this.size];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = this.get(i);
        }
        return newArray;
    }

    @Override
    public String toString() {
        return "StringListImpl{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }
}
