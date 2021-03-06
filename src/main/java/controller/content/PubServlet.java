package controller.content;

import constants.WEBConstants;
import service.games.impl.DeveloperFeatureImpl;
import service.games.impl.PublisherFeatureImpl;
import service.games.interfaces.GameFeatureService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.WEBConstants.ATTRIBUTE_PUBS;
import static constants.WEBConstants.PARAMETER_NAME;

public class PubServlet extends HttpServlet {
    private GameFeatureService pubService;

    @Override
    public void init() throws ServletException {
        super.init();
        pubService = new PublisherFeatureImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(ATTRIBUTE_PUBS, pubService.getAllFeatures());
        req.setAttribute(WEBConstants.ATTRIBUTE_TITLE, "Publishers");
        req.getRequestDispatcher("/game_content/publishers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(PARAMETER_NAME);
        pubService.addFeature(name);
        this.doGet(req, resp);
    }

}