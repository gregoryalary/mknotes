package mknotes.pages;

import mknotes.AppConfig;
import mknotes.sidebar.Sidebar;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public abstract class Page {

    /** Static field used to lazy load the template content */
    protected static String templateContent;

    /** The path of the page */
    protected List<String> path;

    /**
     * Lazy load, cache the template content and return it
     * @return
     * @throws Exception
     */
    protected static String getTemplate() throws Exception {
        if (templateContent == null) {
            File file = new File(AppConfig.get("template"));
            templateContent = FileUtils.readFileToString(file);
        }
        return templateContent;
    }

    /**
     * Generate the site path of a page
     * Basically, concatenate the page path and the site directory path and replace .md with .html
     * @return the site path of the page
     */
    public String getSitePath() {
        StringBuilder path = new StringBuilder(AppConfig.get("siteDirectory"));
        for (String step : this.path) {
            path.append(step);
            path.append("/");
        }
        path.deleteCharAt(path.length() - 1);
        return path.toString().replace(".md", ".html");
    }

    public abstract String render(Sidebar sidebar) throws Exception;

}
