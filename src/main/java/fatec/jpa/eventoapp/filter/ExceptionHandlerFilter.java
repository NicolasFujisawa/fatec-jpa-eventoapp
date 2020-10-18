package fatec.jpa.eventoapp.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fatec.jpa.eventoapp.dto.GenericErrorResponse;
import fatec.jpa.eventoapp.exception.BadRequestException;
import fatec.jpa.eventoapp.exception.NotAuthorizedException;
import fatec.jpa.eventoapp.exception.NotFoundException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, filterName = "exception-handler-filter")
public class ExceptionHandlerFilter implements Filter {
    private ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        this.context.log("Exception Handler filter initialized!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = "";
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        try {
            chain.doFilter(request, response);
        } catch (NotFoundException e) {
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse("NOT_FOUND", e.getMessage());
            message = objectMapper.writeValueAsString(genericErrorResponse);
            res.setStatus(404);
        } catch (BadRequestException e) {
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse("BAD_REQUEST", e.getMessage());
            message = objectMapper.writeValueAsString(genericErrorResponse);
            res.setStatus(400);
        } catch (NotAuthorizedException e) {
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse("UNAUTHORIZED", e.getMessage());
            message = objectMapper.writeValueAsString(genericErrorResponse);
            res.setStatus(401);
        } catch (Exception e) {
            GenericErrorResponse genericErrorResponse = new GenericErrorResponse("INTERNAL_SERVER_ERROR", e.getMessage());
            message = objectMapper.writeValueAsString(genericErrorResponse);
            res.setStatus(500);
        } finally {
            res.getWriter().print(message);
        }
    }

    @Override
    public void destroy() {

    }
}
