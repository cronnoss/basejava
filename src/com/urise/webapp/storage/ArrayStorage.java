package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            if (checkResume(resume) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Resume exist in the array.");
            }
        } else {
            System.out.println("ERROR: Overflow. The maximum number of resumes is 10,000.");
        }
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid) >= 0) {
            return storage[checkUuid(uuid)];
        } else {
            System.out.println("Resume not exist in the array");
            return null;
        }
    }

    public void update(Resume resume) {
        if (checkResume(resume) >= 0) {
            storage[checkResume(resume)] = resume;
            System.out.println("\nUpdated resume : " + storage[checkResume(resume)]);
        } else {
            System.out.println("Resume not exist in the array.");
        }
    }

    public void delete(String uuid) {
        if (checkUuid(uuid) >= 0) {
            storage[checkUuid(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Resume not exist in the array");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int checkUuid(String uuid) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                index = i;
            }
        }
        return index;
    }

    public int checkResume(Resume resume) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                index = i;
            }
        }
        return index;
    }
}