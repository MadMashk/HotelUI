import items.Controller;
import items.IMenuController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.SpringConfig;


public class Main {
    public static void main(String[] args) {
       ApplicationContext contextA = new AnnotationConfigApplicationContext(SpringConfig.class);
       IMenuController controller = contextA.getBean("controller", Controller.class);
       controller.run();
    }
}