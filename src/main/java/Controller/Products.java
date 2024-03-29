package Controller;

import Model.ProductBean;
import Model.ProductDAO;
import Model.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "productsServlet", value = "/products-servlet")
public class Products extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user.isAdmin().equalsIgnoreCase("true")) {

            ProductDAO service = new ProductDAO();
            List<ProductBean> listProducts = service.doRetrieveAll();

            request.setAttribute("products", listProducts);

            String address = "/WEB-INF/results/products.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request, response);
        }

        else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }
    }
}