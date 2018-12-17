package mknotes;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class StructureExtractorTest {

    @Before
    public void init() {
        // nothing so far
    }

    /**
     * Test if for an empty directory, we get an empty collections
     */
    @Test
    public void emptyDirectory() {
        assertTrue(StructureExtractor.getRootStructure("in/test/empty").isEmpty());
    }

    /**
     * Test if images and empty directories are ignored. For :
     * |── 1
     * │   ├── one.md
     * │   ├── un.md
     * │   └── uno.md
     * ├── 2
     * │   ├── 2.md
     * │   └── images
     * │       ├── img.jpg
     * │       └── img.md
     * └── 3
     *     └── images
     * it should return :
     * ├── 1
     * │   ├── one.md
     * │   ├── un.md
     * │   └── uno.md
     * ├── 2
     * │   ├── 2.md
     */
    @Test
    public void normalDirectory() {
        Collection<File> files = StructureExtractor.getRootStructure("in/test/normal");
        List<String> expected = new LinkedList<String>(Arrays.asList("in/test/normal/1/one.md",
                "in/test/normal/1/uno.md",
                "in/test/normal/1/un.md",
                "in/test/normal/2/images/img.md",
                "in/test/normal/2/2.md")
        );
        for (String e : expected) {
            assertTrue(files.contains(new File(e)));
        }
        assertEquals(expected.size(), files.size());
    }

}