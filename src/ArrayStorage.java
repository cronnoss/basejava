import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[6];
    private int size = 0;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        this.storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        int index;
        for (index = 0; index < size; index++) {
            if (storage[index].uuid.equals(uuid)) {
                return storage[index];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index;
        for (index = 0; index < size; index++) {
            if (storage[index].uuid.equals(uuid)) {
                storage[index] = null;
                if (index != size - 1) {
                    for (int i = index; i < size - 1; i++) {
                        Resume temp = storage[i];
                        storage[i] = storage[i + 1];
                        storage[i + 1] = temp;
                    }
                    size--;
                } else {
                    size--;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
