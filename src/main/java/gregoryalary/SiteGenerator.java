package gregoryalary;

import gregoryalary.sidebar.Sidebar;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SiteGenerator {

    Sidebar sidebarManager;

    Collection<File> files;

    public SiteGenerator() {
        files = StructureExtractor.getRootStructure();
        initSidebar();
    }

    /**
     * Generate all the notes pages
     * @throws Exception
     */
    public void generateSite() throws Exception {
        for (File f : files) {
            // Instanciate the new page
            Page page = new Page(f.toString());
            String path = page.getSitePath();
            // Create new file and overwrite the content
            FileUtils.touch(new File(path));
            File file = new File(path);
            FileUtils.write(file, page.render(sidebarManager));
        }
    }

    /**
     * Initialize the sideBarManager by create a new LinkedList containing all the
     * site files
     */
    private void initSidebar() {
        List<String> pageList = new LinkedList<>();
        for (File f : files) {
            pageList.add(f.toString().replace(AppConfig.get("rootDirectory"), "").replace(".md", ".html"));
        }
        sidebarManager = new Sidebar(pageList);
    }

}
