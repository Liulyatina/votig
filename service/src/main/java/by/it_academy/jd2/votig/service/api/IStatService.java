package by.it_academy.jd2.votig.service.api;


import by.it_academy.jd2.votig.dao.api.dto.StatDTO;
import by.it_academy.jd2.voting.core.dto.HibStatDTO;

import java.util.List;

public interface IStatService {

    List<HibStatDTO> getStat();

    StatDTO get();
}
