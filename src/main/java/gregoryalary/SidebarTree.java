package gregoryalary;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SidebarTree {

    private HashMap<String, SidebarTree> subtrees;

    private String title;

    private String href;

    private boolean isRoot;

    public SidebarTree(String title, String href, boolean isRoot) {
        this.subtrees = new HashMap<>();
        this.title = title;
        this.href = href;
        this.isRoot = isRoot;
    }

    public SidebarTree(String title, String href) {
        System.out.println(title);
        this.subtrees = new HashMap<>();
        this.title = title;
        this.href = href;
        this.isRoot = false;
    }

    public void addChild(SidebarTree child, LinkedList<String> location) {
        if (!location.isEmpty()) {
            String nextLocation = location.getFirst();
            SidebarTree nextChild = subtrees.get(nextLocation);
            if (nextChild == null) {
                subtrees.put(nextLocation, new SidebarTree(nextLocation, null));
                nextChild = subtrees.get(nextLocation);
            }
            location.removeFirst();
            nextChild.addChild(child, location);
        } else {
            href = child.href;
            title = title.replace(".html", "");
        }
    }

    public String getTitle() {
        return title;
    }

    public String toString() {
        String result = title + " - " + href;
        result += " [";
        for (SidebarTree child : subtrees.values()) {
            result += child;
        }
        result += "]";
        return result;
    }

    public String render(String hrefPrefix, int deepness) {
        StringBuilder result = new StringBuilder();
        if (isRoot) {
            for (SidebarTree tree : subtrees.values()) {
                result.append(tree.render(hrefPrefix, deepness + 1));
                result.append("<hr class=\"sidebar-hr\">");
            }
        } else if (subtrees.size() == 0) {
            result.append("<li><a class='text-capitalize link-sidebar' href=\"" + hrefPrefix + href + "\">" + title + "</a></li>");
        } else {
            if (deepness == 1) {
                result.append("<li><h6 class='list-title font-weight-bold text-uppercase mt-3'>" + title + "</h6>");
            } else {
                result.append("<li><h6 class='list-title text-capitalize'>" + title + "</h6>");
            }
            result.append("<ul class='side-list'>");
            for (SidebarTree tree : subtrees.values()) {
                result.append(tree.render(hrefPrefix, deepness + 1));
            }
            result.append("</ul></li>");
        }
        return result.toString();
    }

}
