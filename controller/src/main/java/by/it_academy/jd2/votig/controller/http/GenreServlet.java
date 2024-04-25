package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.controller.factory.ControllerFactory;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.dto.ArtistDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreCUDTO;
import by.it_academy.jd2.votig.service.api.dto.GenreDTO;
import by.it_academy.jd2.votig.service.api.dto.VoteDTO;
import by.it_academy.jd2.votig.service.factory.ServiceFactorySingleton;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * RESTful api
 * Базовый URL - /api/genre
 * CRUD
 * C - Create - BODY
 *    POST / -
 * R - Read - NOBODY
 *    GET / +
 *    GET /?id={id} -
 * U - Update - BODY
 *    PUT /?id={id} -
 *    PATCH /?id={id} - опционально
 * D - Delete - BODY
 *    DELETE /?id={id} -
 */
@WebServlet("/api/genre")
public class GenreServlet extends HttpServlet {

    private final ObjectMapper mapper = ControllerFactory.getMapper();
    private final IGenreService genreService = ServiceFactorySingleton.getGenreService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id != null && !id.isBlank()){
            resp.getWriter().write(mapper.writeValueAsString(genreService.get(Long.parseLong(id)).get()));
        } else {
            List<GenreDTO> data = genreService.get();

            resp.getWriter().write(mapper.writeValueAsString(data));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenreCUDTO vote = mapper.readValue(req.getInputStream(), GenreCUDTO.class);

        this.genreService.create(vote);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        GenreCUDTO vote = mapper.readValue(req.getInputStream(), GenreCUDTO.class);

        this.genreService.update(Long.parseLong(id), vote);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        this.genreService.delete(Long.parseLong(id));
    }
}
