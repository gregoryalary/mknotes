package mknotes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarkdownParserTest {

    @Before
    public void init() {
        // nothing so far
    }

    /**
     * The parsing of an empty file should return an empty string
     */
    @Test
    public void testEmpty() throws Exception {
        assertEquals("", MarkdownParser.parseFile("in/test/markdown/empty.md"));
    }

    /**
     * Parsing of a title and a paragraph
     */
    @Test
    public void testTitleParagraph() throws Exception {
        assertEquals("<h1>Title</h1>\n<p>Followed by a paragraph.</p>\n", MarkdownParser.parseFile("in/test/markdown/tp.md"));
    }

    /**
     * Image
     */
    @Test
    public void testImage() throws Exception {
        assertEquals("<h1>Images</h1>\n<p>are important</p>\n<p><img src=\"url.png\" alt=\"desc\" /></p>\n", MarkdownParser.parseFile("in/test/markdown/image.md"));
    }

}