import java.io.File;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {
    public static void main(String[] args) throws Exception {
        File viewDir = new File("src/main/view");
        if (!viewDir.exists()) {
            viewDir = new File("Code/src/main/view");
        }

        Tomcat tomcat = new Tomcat();

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.parseInt(webPort));
        tomcat.getConnector().setURIEncoding("UTF-8");

        File baseDir = new File("target/tomcat");
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
        tomcat.setBaseDir(baseDir.getAbsolutePath());

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", viewDir.getAbsolutePath());
        ctx.setParentClassLoader(Main.class.getClassLoader());

        File additionWebInfClasses = new File("target/classes");
        if (!additionWebInfClasses.exists()) {
            additionWebInfClasses = new File("Code/target/classes");
        }

        if (additionWebInfClasses.exists()) {
            WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                    additionWebInfClasses.getAbsolutePath(), "/"));
            ctx.setResources(resources);
        }

        System.out.println("=======================================================");
        System.out.println("  He thong Quan li Sieu thi Dien may Maririn");
        System.out.println("  Web Root: " + viewDir.getAbsolutePath());
        System.out.println("  Trang chu (Khach):   http://localhost:" + webPort + "/user/HomePageFrm3.jsp");
        System.out.println("  Dang nhap (Quan li): http://localhost:" + webPort + "/user/LoginFrm3.jsp");
        System.out.println("=======================================================");

        tomcat.start();
        tomcat.getServer().await();
    }
}
