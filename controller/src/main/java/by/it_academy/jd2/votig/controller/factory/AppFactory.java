package by.it_academy.jd2.votig.controller.factory;

import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppFactory {

    private static volatile ApplicationContext context;

    private final static ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper getMapper() {
        return mapper;
    }

    static {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    public static IVoteService getVoteService() {
        return context.getBean(IVoteService.class);
    }

    public static IStatService getStatService() {
        return context.getBean(IStatService.class);
    }

    public static IGenreService getGenreService() {
        return context.getBean(IGenreService.class);
    }

    public static IArtistService getArtistService() {
        return context.getBean(IArtistService.class);
    }
}
