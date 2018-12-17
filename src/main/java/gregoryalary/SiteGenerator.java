package gregoryalary;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SiteGenerator {

    /**
     * Generate the site path of a page
     * Basically, concatenate the page path and the site directory path and replace .md with .html
     * @param page
     * @return the site path of the page
     */
    private static String getSitePath(Page page) {
        StringBuilder path = new StringBuilder(AppConfig.get("siteDirectory"));
        for (String step : page.getPath()) {
            path.append(step);
            path.append("/");
        }
        path.deleteCharAt(path.length() - 1);
        return path.toString().replace(".md", ".html");
    }

    /**
     * Copy the css directory
     */
    public static void copyCssDirectory() throws Exception {
        FileUtils.copyDirectory(new File(AppConfig.get("template") + "css/"), new File(AppConfig.get("siteDirectory") + "css/"));
    }

    /**
     * Generate all the notes pages
     * @throws Exception
     */
    public static void generateSite() throws Exception {
        Collection<File> files = StructureExtractor.getRootStructure();
        SidebarManager sidebar = initSidebar(files);
        for (File f : files) {
            // Instanciate the new page
            Page page = new Page(f.toString(), sidebar);
            String path = getSitePath(page);
            // Create new file and overwrite the content
            FileUtils.touch(new File(path));
            File file = new File(path);
            FileUtils.write(file, page.render());
        }
        copyCssDirectory();
    }

    private static SidebarManager initSidebar(Collection<File> files) {
        List<String> pageList = new LinkedList<>();
        for (File f : files) {
            pageList.add(f.toString().replace(AppConfig.get("rootDirectory"), "").replace(".md", ".html"));
        }
        return new SidebarManager(pageList);
    }

}
