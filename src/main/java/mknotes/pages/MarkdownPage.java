package mknotes.pages;

import mknotes.AppConfig;
import mknotes.MarkdownParser;
import mknotes.sidebar.Sidebar;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a page of the site
 * A page has :
 *  - A path
 *  - A title
 *  - A content
 * @author mknotes
 */
public class MarkdownPage extends Page {

    /** The title of the page */
    private String title;

    /** The content of the page */
    private String content;

    /** The last modification date of the file */
    FileTime lastModified;

    /**
     * Default constructor
     * @param path
     */
    public MarkdownPage(String path) throws Exception {
        parsePath(path);
        content = MarkdownParser.parseFile(path);
        lastModified = Files.getLastModifiedTime(Paths.get(path));
    }

    /**
     * Extract the title and parse the path from
     * the path of the file from the root directory
     * @param strPath the path of the file from the root directory
     */
    private void parsePath(String strPath) {
        strPath = strPath.replace(AppConfig.get("rootDirectory"), "");
        path = new LinkedList<String>(Arrays.asList(strPath.split("/")));
        title = ((LinkedList<String>) path).getLast().replace(".md", "");
    }

    /**
     * Render the page in HTML
     * @return the html of the page
     * @throws Exception if an error occured while loading the template
     */
    public String render(Sidebar sidebar) throws Exception {
        String page = getTemplate();
        page = page.replace("{{#PROJECTNAME#}}", AppConfig.get("projectName"));
        page = page.replace("{{#TITLE#}}", title);
        page = page.replace("{{#PAGETITLE#}}", AppConfig.get("projectName") + " - " + title);
        page = page.replace("{{#CONTENT#}}", content);
        page = page.replace("{{#SIDEBAR#}}", sidebar.render(path.size()));
        page = page.replace("{{#LASTMODIFIED#}}", "Last modified on "+ DateFormat.getDateInstance(DateFormat.SHORT).format(lastModified.toMillis()) + ".");
        return page;
    }

    /**
     * Getter for the path of the file
     * @return the path of the file
     */
    public List<String> getPath() {
        return path;
    }

}
