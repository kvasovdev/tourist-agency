package ru.vsu.touristagency.servlets;

import ru.vsu.touristagency.domain.Client;
import ru.vsu.touristagency.services.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ClientServlet extends HttpServlet {

    private ClientService service = new ClientService();

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Client> clients = service.getClients();
        req.setAttribute("clients", clients);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/showClients.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fullName = req.getParameter("fullName");
        int numberPhone = Integer.parseInt(req.getParameter("numberPhone"));
        Client client = new Client(fullName, numberPhone);
        service.createClient(client);
        resp.sendRedirect("/clients");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Client client = service.getClient(id);
        client.setFullName(req.getParameter("fullName"));
        client.setNumberPhone(Integer.parseInt("numberPhone"));
        service.updateClient(client);
        resp.sendRedirect("/clients");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long id = Long.parseLong(req.getParameter("id"));
        service.deleteClient(id);
    }
}