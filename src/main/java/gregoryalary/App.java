package gregoryalary;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception
    {
        AppConfig.init();
        SiteGenerator.generateSite();
    }

}
