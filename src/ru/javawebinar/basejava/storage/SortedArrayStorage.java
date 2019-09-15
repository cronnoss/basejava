package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public boolean uuidExist(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            return false;
        }
        return true;
    }

    @Override
    public void saveResume(Resume r) {
        int index = getIndex(r.getUuid());
        index = -index - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
        size++;
    }

    @Override
    public void deleteResume(String uuid) {
        int index = getIndex(uuid);
        storage[index] = null;
        if (index != size - 1) {
            for (int j = index; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
        }
        size--;
    }
}