package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.controller.factory.ControllerFactory;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.dto.ArtistCUDTO;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;
import by.it_academy.jd2.votig.service.factory.ServiceFactorySingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/artist")
public class ArtistServlet extends HttpServlet {

    private final ObjectMapper mapper = ControllerFactory.getMapper();
    private final IArtistService artistService = ServiceFactorySingleton.getArtistService();


    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id != null && !id.isBlank()){
            resp.getWriter().write(mapper.writeValueAsString(artistService.get(Long.parseLong(id)).get()));
        } else {
            List<ArtistDTO> data = artistService.get();

            resp.getWriter().write(mapper.writeValueAsString(data));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArtistCUDTO vote = mapper.readValue(req.getInputStream(), ArtistCUDTO.class);

        this.artistService.create(vote);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        ArtistCUDTO vote = mapper.readValue(req.getInputStream(), ArtistCUDTO.class);

        this.artistService.update(Long.parseLong(id), vote);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        this.artistService.delete(Long.parseLong(id));
    }
}
