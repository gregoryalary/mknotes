package gregoryalary;

import org.apache.commons.io.FileUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;

/**
 * Render the site pages from the markdown sources
 */
public final class MarkdownParser {

    private MarkdownParser() { }

    /**
     * Read a file and returns its content.
     * @param path the path of the file
     * @return the content of the file
     * @throws Exception if the file does not exist
     */
    private static String getFileContent(String path) throws Exception {
        File file = new File(path);
        return FileUtils.readFileToString(file);
    }

    /**
     * Return the content of the file parsed from markdown to HTML
     * @param file the path of the file
     * @return the content in html
     * @throws Exception if the file does not exist
     */
    public static String parseFile(String file) throws Exception {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(getFileContent(file));
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

}
