package io.github.plizzzhealme.controller.command.navigation;

import io.github.plizzzhealme.controller.command.Command;
import io.github.plizzzhealme.controller.util.Util;
import io.github.plizzzhealme.service.CategoryService;
import io.github.plizzzhealme.service.ServiceFactory;
import io.github.plizzzhealme.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToCategoriesPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ServiceException {

        CategoryService categoryService = ServiceFactory.INSTANCE.getCategoryService();
        List<String> categories = categoryService.findAll();

        request.setAttribute(Util.CATEGORY_LIST, categories);

        RequestDispatcher dispatcher = request.getRequestDispatcher(Util.CATEGORIES_JSP);
        dispatcher.forward(request, response);
    }
}
