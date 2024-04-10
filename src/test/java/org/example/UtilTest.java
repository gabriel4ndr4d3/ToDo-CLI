package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class UtilTest {

    private static final String TEST_DIRECTORY_PATH = "testDirectory";

    @Test
    void testGetGlobalDir() {
        File globalDir = Util.getGlobalDir();
        Assertions.assertNotNull(globalDir);
        Assertions.assertTrue(globalDir.isDirectory());
        Assertions.assertEquals("todo", globalDir.getName());
        Assertions.assertEquals(System.getProperty("user.home"), globalDir.getParent());
    }

    @Test
    void testWriteAndRead() {
        File testFile = new File(TEST_DIRECTORY_PATH, "test.txt");
        String text = "Test content";
        Util.write(testFile, text);
        String content = Util.read(testFile);
        Assertions.assertEquals("Test content\n", content);
    }

}
