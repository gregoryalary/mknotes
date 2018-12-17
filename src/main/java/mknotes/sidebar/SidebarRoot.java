package mknotes.sidebar;

public class SidebarRoot extends SidebarNode {

    public SidebarRoot() {
        super("_root", "_root");
    }

    @Override
    public String render(int deepness) {
        StringBuilder sb = new StringBuilder();
        for (SidebarNode tree : subtrees.values()) {
            sb.append(tree.render(deepness + 1));
            sb.append("<hr class=\"sidebar-hr\">");
        }
        return sb.toString();
    }

}
