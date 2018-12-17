package gregoryalary.sidebar;

public class SidebarLeaf extends SidebarNode {

    public SidebarLeaf(String title, String href) {
        super(title, href);
    }

    @Override
    public String render(int deepness) {
        return String.format("<li><a class='text-capitalize link-sidebar' href=\"{{#HREFPREFIX#}}%s\">%s</a></li>", href, title);
    }

}
