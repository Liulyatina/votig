import by.it_academy.jd2.votig.controller.factory.AppFactory;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;

public class Main {
    public static void main(String[] args) {
        IArtistService artistService = AppFactory.getArtistService();
        IVoteService voteService = AppFactory.getVoteService();
        IStatService statService = AppFactory.getStatService();
        IGenreService genreService = AppFactory.getGenreService();
        System.out.println(artistService);
        System.out.println(voteService);
        System.out.println(statService);
        System.out.println(genreService);
    }
}
