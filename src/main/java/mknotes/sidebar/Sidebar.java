package mknotes.sidebar;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Sidebar {

    private List<String> pages;

    /** The root node of the sidebar tree. */
    SidebarNode root;

    String rendered;

    /**
     * Instanciate a new Sidebar and build its tree
     * @param pages
     */
    public Sidebar(List<String> pages) {
        this.pages = pages;
        buildSidebarTree();
    }

    /**
     * Add all the pages in a root node to create the sidebar tree
     */
    private void buildSidebarTree() {
        root = new SidebarRoot();
        for (String page : pages) {
            LinkedList<String> location = new LinkedList<>(Arrays.asList(page.split("/")));
            SidebarNode child = new SidebarLeaf(location.getLast().replace(".html", ""), page);
            location.removeLast();
            root.addChild(child, location);
        }
    }

    /**
     * Return the rendered side bar and cache it
     * @return the rendered side bar
     */
    public String render(int deepness) {
        if (rendered == null) {
            rendered = "<ul class='first-side-list'>" + root.render(0) + "</ul>";
        }
        String hrefPrefix = "";
        if (deepness > 1) {
            for (int count = 0; count < (deepness - 1); count++) {
                hrefPrefix = "../" + hrefPrefix;
            }
        }
        return rendered.replace("{{#HREFPREFIX#}}", hrefPrefix);
    }

}
