package by.it_academy.jd2.votig.controller.filter;

import by.it_academy.jd2.votig.controller.factory.ControllerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class ExceptionHandlerFilter implements Filter {
    private final static Logger logger = LogManager.getLogger();
    private final ObjectMapper mapper = ControllerFactory.getMapper();

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        boolean error = false;
        int errorStatusCode = 0;
        String errorMessage = "Ошибка на стороне сервера";

        try{
            chain.doFilter(request, response);
        } catch (IllegalArgumentException e){
            logger.log(Level.WARN, "Пользователь сделал что-то не так", e);
            error = true;
            errorStatusCode = HttpServletResponse.SC_BAD_REQUEST;
            errorMessage = e.getMessage();
        } catch (Exception e){
            logger.log(Level.ERROR, "Ошибка на стороне сервера", e);
            error = true;
            errorStatusCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        } finally {
            if(error){
                if(response instanceof HttpServletResponse){
                    HttpServletResponse castResponse = (HttpServletResponse) response;

                    castResponse.setStatus(errorStatusCode);

                    PrintWriter writer = castResponse.getWriter();

                    if(isJsonResponse(castResponse)){

                        Map<String, Object> errorObj = new HashMap<>();
                        errorObj.put("error", errorMessage);

                        writer.write(mapper.writeValueAsString(errorObj));
                    } else {
                        writer.write(errorMessage);
                    }
                }
            }
        }
    }

    private boolean isJsonResponse(HttpServletResponse response){
        return response.getContentType().startsWith("application/json");
    }
}
