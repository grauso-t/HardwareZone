package Controller;

import Model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "addProductServlet", value = "/add-product-servlet")
public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user.isAdmin().equalsIgnoreCase("true")) {
            CategoryDAO service = new CategoryDAO();
            ArrayList<CategoryBean> categoryList = service.doRetrieveAll();

            request.setAttribute("categories", categoryList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/add-product.jsp");
            dispatcher.include(request, response);
        }

        else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");

        if (user.isAdmin().equalsIgnoreCase("true")) {

            final Pattern decimal_String = Pattern.compile("^(\\d+(?:[.,]\\d{2})?)$");
            final Pattern int_String = Pattern.compile("^\\d+$");
            int level = 0;

            String name = request.getParameter("name");
            if (name.length() <= 20 && name.length() != 0)
                level++;

            String description = request.getParameter("description");
            if (description.length() <= 255 && description.length() != 0)
                level++;

            String price = request.getParameter("price").replace(",", ".");
            Matcher matcher = decimal_String.matcher(price);
            boolean matchFound = matcher.find();
            if (matchFound)
                level++;

            String quantity = request.getParameter("quantity");
            matcher = int_String.matcher(quantity);
            matchFound = matcher.find();
            if (matchFound)
                level++;

            String sales = request.getParameter("sales");
            matcher = int_String.matcher(sales);
            matchFound = matcher.find();
            if (matchFound)
                level++;

            String category = request.getParameter("category");
            if (category.length() <= 20 && category.length() != 0)
                level++;

            Part part = request.getPart("image");
            String subpath;
            if (!part.getSubmittedFileName().isEmpty()) {
                String uploadPath = getServletContext().getRealPath("") + "\\img\\products";
                String imagepath = uploadPath + File.separator + part.getSubmittedFileName();
                part.write(imagepath);
                subpath = "./img/products/" + part.getSubmittedFileName();
            }
            else {
                subpath = "./img/products/nophoto.png";
            }

            ProductDAO service = new ProductDAO();

            if (level == 6 && !service.isAlreadyRegistered(name, description)) {
                ProductBean product = new ProductBean();

                CategoryDAO serviceCategory = new CategoryDAO();
                ArrayList<CategoryBean> categoryList = serviceCategory.doRetrieveAll();

                boolean categoryExist = false;
                for (CategoryBean categorySaved : categoryList) {

                    if (categorySaved.getNome().equalsIgnoreCase(category)) {
                        categoryExist = true;
                        break;
                    }
                }
                if (!categoryExist)
                    serviceCategory.doSave(category);

                product.setName(name);
                product.setDescription(description);
                product.setPrice(Double.parseDouble(price));
                product.setQuantity(Integer.parseInt(quantity));
                product.setSales(Integer.parseInt(sales));
                product.setCategory(category);
                product.setImage(subpath);

                service.doSave(product);

                ArrayList<CategoryBean> listCategories = serviceCategory.doRetrieveAll();

                request.setAttribute("categories", listCategories);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/add-product.jsp");
                dispatcher.include(request, response);
            }

            else {

                if (service.isAlreadyRegistered(name, description)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Prodotto già presente");
                    request.setAttribute("redirect", "add-product-servlet");
                    dispatcher.include(request, response);
                }

                else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/error.jsp");
                    request.setAttribute("type", "alert");
                    request.setAttribute("msg", "Errore inserimento");
                    request.setAttribute("redirect", "add-product-servlet");
                    dispatcher.include(request, response);
                }
            }
        }

        else {

            String address = "/WEB-INF/results/products.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(address);
            dispatcher.forward(request,response);
        }
    }
}