package gregoryalary;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent a page of the site
 * A page has :
 *  - A path
 *  - A title
 *  - A content
 * @author gregoryalary
 */
public class Page {

    /** Static field used to lazy load the template content */
    private static String templateContent;

    private final SidebarManager sidebar;

    /** The path of the page */
    private List<String> path;

    /** The title of the page */
    private String title;

    /** The content of the page */
    private String content;

    /**
     * Default constructor
     * @param path
     */
    public Page(String path, SidebarManager sidebar) throws Exception {
        parsePath(path);
        content = MarkdownParser.parseFile(path);
        this.sidebar = sidebar;
    }

    private void parsePath(String strPath) {
        strPath = strPath.replace(AppConfig.get("rootDirectory"), "");
        path = new LinkedList<String>(Arrays.asList(strPath.split("/")));
        title = ((LinkedList<String>) path).getLast().replace(".md", "");
    }

    public String render() throws Exception {
        String page = Page.getTemplate();
        page = page.replace("{{#PROJECTNAME#}}", AppConfig.get("projectName"));
        page = page.replace("{{#TITLE#}}", title);
        page = page.replace("{{#PAGETITLE#}}", AppConfig.get("projectName") + " - " + title);
        page = page.replace("{{#CONTENT#}}", content);
        page = page.replace("{{#SIDEBAR#}}", sidebar.render(path.size()));
        // Css link
        String cssPath = "css/style.css";
        if (path.size() > 1) {
            for (int count = 0; count < (path.size() - 1); count++) {
                cssPath = "../" + cssPath;
            }
        }
        page = page.replace("{{#CSS#}}", "<link rel=\"stylesheet\" href=\"" + cssPath + "\">");
        return page;
    }

    public List<String> getPath() {
        return path;
    }

    /**
     * Lazy load, cache the template content and return it
     * @return
     * @throws Exception
     */
    private static String getTemplate() throws Exception {
        if (templateContent == null) {
            File file = new File(AppConfig.get("template") + "template.html");
            templateContent = FileUtils.readFileToString(file);
        }
        return templateContent;
    }

}
