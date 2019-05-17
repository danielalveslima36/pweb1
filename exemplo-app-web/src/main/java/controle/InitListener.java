package controle;

import modelo.Curso;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        String cursoStr = event.getServletContext().getInitParameter("curso");
        Curso cursoObj = new Curso();
        cursoObj.setNome(cursoStr);
        event.getServletContext().setAttribute("curso", cursoObj);
    }


}
