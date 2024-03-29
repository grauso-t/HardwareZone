package Controller;

import Model.CategoryBean;
import Model.CategoryDAO;
import Model.ProductBean;
import Model.ProductDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "showSalesServlet", value = "/show-sales-servlet")
public class ShowSales extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        CategoryDAO serviceCategory = new CategoryDAO();
        ArrayList<CategoryBean> listCategories = serviceCategory.doRetrieveAll();

        ProductDAO service = new ProductDAO();
        ArrayList<ProductBean> listProducts = service.doRetrieveSales();

        request.setAttribute("categories", listCategories);
        request.setAttribute("products", listProducts);

        String address = "/WEB-INF/sales.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}