package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.controller.factory.AppFactory;
import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.dto.GenreCUDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/genre")
public class GenreServlet extends HttpServlet {

    private final ObjectMapper mapper = AppFactory.getMapper();
    private final IGenreService genreService = AppFactory.getGenreService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id != null && !id.isBlank()){
            resp.getWriter().write(mapper.writeValueAsString(genreService.get(Long.parseLong(id)).get()));
        } else {
            List<GenreEntity> data = genreService.get();

            resp.getWriter().write(mapper.writeValueAsString(data));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenreCUDTO genre = mapper.readValue(req.getInputStream(), GenreCUDTO.class);

        GenreEntity createdGenre = this.genreService.create(genre);
        resp.getWriter().write(mapper.writeValueAsString(createdGenre));
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        GenreCUDTO genre = mapper.readValue(req.getInputStream(), GenreCUDTO.class);

        GenreEntity updatedGenre = this.genreService.update(Long.parseLong(id), genre);
        resp.getWriter().write(mapper.writeValueAsString(updatedGenre));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        this.genreService.delete(Long.parseLong(id));
    }
}
