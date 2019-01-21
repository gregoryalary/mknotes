package mknotes.sidebar;

import java.util.*;

public abstract class SidebarNode {

    /** Containg the child of the node <Title, Object> */
    protected Map<String, SidebarNode> subtrees;

    protected String title;

    protected String href;

    public SidebarNode(String title, String href) {
        this.subtrees = new TreeMap<String, SidebarNode>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toLowerCase().compareTo(o2.toLowerCase());
            }
        }); // ordered sidebar
        this.title = title;
        this.href = href;
    }

    public void addChild(SidebarNode child, LinkedList<String> location) {
        if (location.isEmpty()) {
            subtrees.put(child.getTitle(), child);
        } else {
            String nextLocation = location.removeFirst();
            SidebarNode nextChild = subtrees.get(nextLocation);
            if (nextChild == null) {
                nextChild = new SidebarInnerNode(nextLocation);
                subtrees.put(nextLocation, nextChild);
            }
            nextChild.addChild(child, location);
        }
    }

    /**
     * Return the title of the node
     * @return
     */
    public String getTitle() {
        return title;
    }

    public abstract String render(int deepness);

}
