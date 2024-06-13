package com.javatutorials.java.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class ResourceReader {

    public static String readResource(String resourceName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    @Test
    public void testReadResource() {
        try {
            String content = ResourceReader.readResource("system_log.xml");
            assertNotNull(content);
            assertFalse(content.isEmpty());
        } catch (IOException e) {
            fail("IOException thrown: " + e.getMessage());
        }
    }
}
