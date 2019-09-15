package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (!uuidExist(r)) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
            System.out.println("\nUpdated resume : " + storage[index]);
        }
    }

    public void save(Resume r) {
        if (uuidExist(r)) {
            System.out.println("Resume " + r.getUuid() + " already exist");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            saveResume(r);
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[getIndex(uuid)];
    }

    public void delete(String uuid) {
        if (getIndex(uuid) < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            deleteResume(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract boolean uuidExist(Resume r);

    public abstract void saveResume(Resume r);

    public abstract void deleteResume(String uuid);
}