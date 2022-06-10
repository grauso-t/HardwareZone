package Controller;

import java.io.*;

import Model.UtenteBean;
import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "registrazioneServlet", value = "/registrazione-servlet")
public class Registrazione extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String citta = request.getParameter("citta");
        String provincia = request.getParameter("provincia");
        String codice_postale = request.getParameter("codice-postale");
        String indirizzo = request.getParameter("indirizzo");
        String data = request.getParameter("data-di-nascita");

        UtenteDAO service = new UtenteDAO();

        if (!service.isAlreadyRegistered(email)) {

            UtenteBean utente = new UtenteBean();
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setEmail(email);
            utente.setPassword(password);
            utente.setTelefono(telefono);
            utente.setCitta(citta);
            utente.setProvincia(provincia);
            utente.setCodicePostale(codice_postale);
            utente.setIndirizzo(indirizzo);
            utente.setDataNascita(data);
            utente.setDataRegistrazione();

            service.doSave(utente);

            HttpSession session = request.getSession();
            session.setAttribute("nome-utente", utente.getNome());
            session.setAttribute("id-utente", utente.getId());

            PrintWriter out = response.getWriter();
            out.println("<div class=\"success\">\n" +
                    "    <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \n" +
                    "    Registrazione effettuata\n" +
                    "    </div>");

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.include(request, response);
        } else {

            PrintWriter out = response.getWriter();
            out.println("<div class=\"alert\">\n" +
                    "    <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \n" +
                    "    <strong>Attenzione!</strong> Email già in uso\n" +
                    "    </div>");

            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.include(request, response);
        }
    }

    public void destroy() {
    }
}