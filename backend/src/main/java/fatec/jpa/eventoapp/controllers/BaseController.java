package fatec.jpa.eventoapp.controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseController extends HttpServlet implements BaseControllerInterface {
    @Override
    public void statusAndSend(Integer status, HttpServletResponse res, String data) throws IOException {
        res.setStatus(status);
        res.getWriter().print(data);
        res.getWriter().flush();
    }

    @Override
    public void status(Integer status, HttpServletResponse res) throws IOException {
        res.setStatus(status);
        res.getWriter().flush();
    }
}
