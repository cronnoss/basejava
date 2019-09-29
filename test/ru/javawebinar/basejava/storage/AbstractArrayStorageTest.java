package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private Resume[] result = new Resume[3];

    protected AbstractArrayStorageTest() {
        this.storage = new ArrayStorage();
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() {
        storage.clear();
        Resume resume1 = new Resume(UUID_1);
        Resume resume2 = new Resume(UUID_2);
        Resume resume3 = new Resume(UUID_3);
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
        this.result = new Resume[]{resume1, resume2, resume3};
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Resume[] result = new Resume[0];
        Assert.assertEquals(storage.getAll(), result);
    }

    @Test
    public void updateSuccess() {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        storage.update(resume4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateFailed() {
        Resume resume4 = new Resume("uuid4");
        storage.update(resume4);
    }

    @Test
    public void saveSuccess() {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveFailed() {
        Resume resume1 = new Resume("uuid1");
        storage.save(resume1);
    }

    @Test
    public void getExist() {
        Resume test = result[0];
        Assert.assertEquals(test, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void deleteSuccess() {
        storage.delete("uuid1");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteFailed() {
        storage.delete("uuid4");
    }

    @Test
    public void getAll() {
        Resume[] test = storage.getAll();
        Assert.assertEquals(test, result);
    }

    @Test(expected = StorageException.class)
    public void isStorageOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < 10_001; i++) {
                Resume resume = new Resume();
                storage.save(resume);
            }
            Assert.fail("Expected Exception not thrown");
        } catch (StorageException thrown) {
            Resume resume = new Resume();
            storage.save(resume);
            Assert.assertEquals(10_001, storage.size());
        }
    }
}