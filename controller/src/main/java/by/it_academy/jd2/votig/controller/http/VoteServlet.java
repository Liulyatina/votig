package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.controller.factory.ControllerFactory;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.api.dto.VoteDTO;
import by.it_academy.jd2.votig.service.factory.ServiceFactorySingleton;
import by.it_academy.jd2.voting.core.dto.HibStatDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/api/vote")
public class VoteServlet extends HttpServlet {

    private final IVoteService voteService = ServiceFactorySingleton.getVoteService();
    private final IStatService statService = ServiceFactorySingleton.getStatService();
    private final ObjectMapper mapper = ControllerFactory.getMapper();

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        VoteDTO vote = mapper.readValue(req.getInputStream(), VoteDTO.class);

        this.voteService.save(vote);

        List<HibStatDTO> stat = this.statService.get();


        resp.getWriter().write(mapper.writeValueAsString(stat));
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}
