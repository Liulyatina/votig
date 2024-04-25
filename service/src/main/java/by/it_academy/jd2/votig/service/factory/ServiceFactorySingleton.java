package by.it_academy.jd2.votig.service.factory;

import by.it_academy.jd2.votig.dao.factory.DaoFactory;
import by.it_academy.jd2.votig.dao.factory.DaoFactoryHibernate;
import by.it_academy.jd2.votig.service.ArtistService;
import by.it_academy.jd2.votig.service.GenreService;
import by.it_academy.jd2.votig.service.StatService;
import by.it_academy.jd2.votig.service.VoteService;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.IStatService;
import by.it_academy.jd2.votig.service.api.IVoteService;
import by.it_academy.jd2.votig.service.converter.ArtistEntityToDTOConverter;

public class ServiceFactorySingleton {

    private volatile static IStatService statService;
    private volatile static IVoteService voteService;
    private volatile static IArtistService artistService;
    private volatile static IGenreService genreService;

    public static IStatService getStatService(){
        if(statService == null){
            synchronized (ServiceFactorySingleton.class){
                if(statService == null){
                    statService = new StatService(DaoFactoryHibernate.getStatisticsDaoHibernate(), getVoteService());
                }
            }
        }
        return statService;
    }

    public static IVoteService getVoteService(){
        if(voteService == null){
            synchronized (ServiceFactorySingleton.class){
                if(voteService == null){
                    voteService = new VoteService(DaoFactory.getVoteDao(), getGenreService(), getArtistService());
                }
            }
        }
        return voteService;
    }

    public static IArtistService getArtistService(){
        if(artistService == null){
            synchronized (ServiceFactorySingleton.class){
                if(artistService == null){
                    artistService = new ArtistService(DaoFactory.getArtistDao(), new ArtistEntityToDTOConverter());
                }
            }
        }
        return artistService;
    }

    public static IGenreService getGenreService(){
        if(genreService == null){
            synchronized (ServiceFactorySingleton.class){
                if(genreService == null){
                    genreService = new GenreService(DaoFactory.getGenreDao(), new GenreEntityToDTOConverter());
                }
            }
        }
        return genreService;
    }
}
