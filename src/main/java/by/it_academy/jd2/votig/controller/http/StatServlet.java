package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.dto.HibStatDTO;
import jakarta.servlet.http.HttpServlet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/stat")
public class StatServlet extends HttpServlet {

    private final IStatService statService;

    public StatServlet(IStatService statService) {
        this.statService = statService;
    }

    @GetMapping()
    public List<HibStatDTO> get()
            throws IOException {
        List<HibStatDTO> data = statService.get();

        return data;
    }
}
