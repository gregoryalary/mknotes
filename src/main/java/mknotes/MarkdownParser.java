package mknotes;

import org.apache.commons.io.FileUtils;
import org.commonmark.node.Image;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.File;
import java.util.Map;

/**
 * Render the site pages from the markdown sources
 */
public final class MarkdownParser {

    static class ImageAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Image) {
                attributes.put("class", "img-fluid mx-auto d-block");
            }
        }
    }

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
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new ImageAttributeProvider();
                    }
                }).build();
        return renderer.render(document);
    }

}
