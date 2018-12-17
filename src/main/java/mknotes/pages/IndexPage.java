package mknotes.pages;

import mknotes.AppConfig;
import mknotes.sidebar.Sidebar;

import java.text.DateFormat;
import java.util.LinkedList;

public class IndexPage extends Page {

    public IndexPage() {
        path = new LinkedList<>();
        path.add("index.html");
    }

    @Override
    public String render(Sidebar sidebar) throws Exception {
        String page = MarkdownPage.getTemplate();
        page = page.replace("{{#PROJECTNAME#}}", AppConfig.get("projectName"));
        page = page.replace("{{#TITLE#}}", AppConfig.get("projectName"));
        page = page.replace("{{#PAGETITLE#}}", AppConfig.get("projectName"));
        page = page.replace("{{#CONTENT#}}", "<h4 class='mt-4 mb-4'>Made with mknotes.</h4>");
        page = page.replace("{{#SIDEBAR#}}", sidebar.render(0));
        page = page.replace("{{#LASTMODIFIED#}}", "");
        return page;
    }

}
