package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.controller.factory.ControllerFactory;
import by.it_academy.jd2.votig.dao.api.dto.StatDTO;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.factory.ServiceFactorySingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/api/stat")
public class StatServlet extends HttpServlet {

    private IStatService statService = ServiceFactorySingleton.getStatService();
    private final ObjectMapper mapper = ControllerFactory.getMapper();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        StatDTO stat = this.statService.get();

        resp.getWriter().write(mapper.writeValueAsString(stat));
    }
}
