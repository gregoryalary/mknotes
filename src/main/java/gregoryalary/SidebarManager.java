package gregoryalary;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SidebarManager {

    private List<String> pages;

    SidebarTree root;

    public SidebarManager(List<String> pages) {
        this.pages = pages;
        buildSidebarTree();
    }

    private void buildSidebarTree() {
        root = new SidebarTree("Root", "Root", true);
        for (String page : pages) {
            LinkedList<String> location = new LinkedList<>(Arrays.asList(page.split("/")));
            root.addChild(new SidebarTree(location.getLast().replace(".html", ""), page), location);
        }
    }

    /**
     * Return the rendered side bar and cache it
     * @return the rendered side bar
     */
    public String render(int deepness) {
        String hrefPrefix = "";
        if (deepness > 1) {
            for (int count = 0; count < (deepness - 1); count++) {
                hrefPrefix = "../" + hrefPrefix;
            }
        }
        return "<ul class='first-side-list'>" + root.render(hrefPrefix, 0) + "</ul>";
    }

}
