package mknotes.sidebar;

public class SidebarInnerNode extends SidebarNode {

    public SidebarInnerNode(String title) {
        super(title, "_inner_node");
    }

    @Override
    public String render(int deepness) {
        StringBuilder sb = new StringBuilder("<li>");
        if (deepness == 1) {
            sb.append(String.format("<h6 class='list-title font-weight-bold text-uppercase mt-3'>%s</h6>", title));
        } else {
            sb.append(String.format("<h6 class='list-title text-capitalize'>" + title + "</h6>", title));
        }
        sb.append("<ul class='side-list'>");
        for (SidebarNode tree : subtrees.values()) {
            sb.append(tree.render(deepness + 1));
        }
        sb.append("</ul></li>");
        return sb.toString();
    }

}
