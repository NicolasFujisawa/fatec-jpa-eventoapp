package fatec.jpa.eventoapp.controllers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BaseControllerInterface {
    void status(Integer status, HttpServletResponse res) throws IOException;
    void statusAndSend(Integer status, HttpServletResponse res, String data) throws IOException;
}
