package servlets;

import beans.GymEJB;
import clasesPOJO.Actividad;
import clasesPOJO.Matricula;
import clasesPOJO.Socio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ServletBusqueda"})
public class ServletBusqueda extends HttpServlet {
    
    @EJB
    GymEJB gymEJB;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Busqueda por Socio</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado de la busqueda</h1>");
            
             // Recogemos los datos del formulario
            String numSocio = request.getParameter("numSocio");
            int numSocioInt = Integer.parseInt(numSocio);
            Socio soc = new Socio(numSocioInt, "", "");
            if (gymEJB.existeSocio(soc)) {
                out.println("<h2>Socio numero: " + numSocio + "</h2>");
            } else {
                out.println("No existe un socio con ese numero.");
            }
            
            List<Matricula> l = gymEJB.findMatriculaBySocio(numSocioInt);
            for(int i=0; i<10;i++){
                out.print("<br><b>ID Matricula: </b>" + 
                        l.get(i).getIdmatricula() + 
                        ", <b>Actividad: </b>" + 
                        l.get(i).getActividad().getNombre() + 
                        "<b>Fecha: </b>" + 
                        l.get(i).getFecha() + "<br>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
