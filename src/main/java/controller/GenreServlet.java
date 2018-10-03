package controller;

import constants.WEBConstants;
import service.games.impl.GenreFeatureImpl;
import service.games.interfaces.GameFeatureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GenreServlet extends HttpServlet {
    private GameFeatureService genreService;

    @Override
    public void init() throws ServletException {
        super.init();
        genreService = new GenreFeatureImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(WEBConstants.ATTRIBUTE_TITLE, "Genres");
        req.setAttribute("genres", genreService.getAllFeatures());
        req.getRequestDispatcher("/genres.jsp").forward(req,resp);
    }
}