package fatec.jpa.eventoapp.filter;

import fatec.jpa.eventoapp.dao.ModeratorDaoJpa;
import fatec.jpa.eventoapp.dao.UserDaoJpa;
import fatec.jpa.eventoapp.entity.Moderator;
import fatec.jpa.eventoapp.entity.PersistenceManager;
import fatec.jpa.eventoapp.entity.User;
import fatec.jpa.eventoapp.enums.ModeratorLevelEnum;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@WebFilter(urlPatterns = {"/*"}, filterName = "authFilter")
public class AuthFilter implements Filter {
    private ServletContext context;
    private EntityManager manager;
    private List<String> ignoreRoutes = List.of("/users");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
        manager = PersistenceManager.getInstance().getEntityManager();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (isExcludedRoute(request.getRequestURI())) {
            chain.doFilter(req, res);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            System.out.println("[AUTH] Header not found");
            unauthorized(response);
            return;
        }

        String[] credentials = getUserAndPassword(authHeader);

        if (credentials == null) {
            System.out.println("[AUTH] Invalid user or password");
            unauthorized(response);
            return;
        }

        UserDaoJpa userDaoJpa = new UserDaoJpa(manager);
        User user = userDaoJpa.findByNameAndPassword(credentials[0], credentials[1]);

        if (user == null) {
            System.out.println("[AUTH] User not found");
            unauthorized(response);
            return;
        }

        if (isAdminRoute(request.getRequestURI(), request.getMethod()) && !isAdmin(user)) {
            unauthorized(response);
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

    private void unauthorized(HttpServletResponse res) throws IOException {
        res.setStatus(401);
        res.getWriter().flush();
    }

    private String[] getUserAndPassword(String header) {

        StringTokenizer st = new StringTokenizer(header);

        if (!st.hasMoreTokens()) return null;

        String basic = st.nextToken();

        if (!basic.equalsIgnoreCase("basic")) return null;

        return extractCredentials(st);
    }

    private String[] extractCredentials(StringTokenizer token) {
        try {
            String credentials = new String(Base64.getDecoder().decode(token.nextToken()));

            Integer p = credentials.indexOf(":");

            if (p == -1) return null;

            String username = credentials.substring(0, p).trim();
            String password = credentials.substring(p + 1).trim();
            String[] vetCredentials = {username, password};
            return vetCredentials;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isAdminRoute(String route, String method) {
        return (route.equalsIgnoreCase("/events") && method.equalsIgnoreCase("PUT"))
                || (route.equalsIgnoreCase("/events") && method.equalsIgnoreCase("DELETE"));
    }

    private boolean isAdmin(User user) {
        ModeratorDaoJpa moderatorDaoJpa = new ModeratorDaoJpa(manager);
        Moderator moderator = moderatorDaoJpa.find(user.getId());

        return (moderator != null && moderator.getLevel().equals(ModeratorLevelEnum.MANAGER));
    }

    private boolean isExcludedRoute(String route) {
        return ignoreRoutes.contains(route);
    }
}
