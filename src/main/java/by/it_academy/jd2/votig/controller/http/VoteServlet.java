package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.api.dto.VoteDTO;
import jakarta.servlet.http.HttpServlet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
public class VoteServlet extends HttpServlet {
    private final IVoteService voteService;

    public VoteServlet(IVoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody VoteDTO voteDTO) {
        this.voteService.save(voteDTO);
    }
}
