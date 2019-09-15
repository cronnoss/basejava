package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean uuidExist(Resume r) {
        int index = getIndex(r.getUuid());
        if (index == -1) {
            return false;
        }
        return true;
    }

    @Override
    public void saveResume(Resume r) {
        storage[size] = r;
        size++;
    }

    @Override
    public void deleteResume(String uuid) {
        int index = getIndex(uuid);
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}