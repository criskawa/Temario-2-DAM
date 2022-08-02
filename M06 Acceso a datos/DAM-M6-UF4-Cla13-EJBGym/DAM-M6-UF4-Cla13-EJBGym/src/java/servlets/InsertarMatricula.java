/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.GymEJB;
import clasesPOJO.Actividad;
import clasesPOJO.Matricula;
import clasesPOJO.Socio;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InsertarMatricula", urlPatterns = {"/InsertarMatricula"})
public class InsertarMatricula extends HttpServlet {
    
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertarMatricula</title>");
            out.println("<link rel=\"stylesheet\" href=\"testEJBStyle.css\" />");
            out.println("</head>");
            out.println("<body>");
            // Recogemos los datos del formulario
            String id = request.getParameter("id");
            String numSocio = request.getParameter("numSocio");
            String nomActividad = request.getParameter("nomActividad");
            String fecha = request.getParameter("fecha");
            
            int idInt = Integer.parseInt(id);
            int numSocioInt = Integer.parseInt(numSocio);
            
            Actividad tmpA = new Actividad(nomActividad, new BigDecimal(0), 0);
            Socio tmpS = new Socio(numSocioInt, "", "");
            if (!gymEJB.existeActividad(tmpA)) {
                out.println("No existe la actividad, no se puede crear la matricula");
            } else if (!gymEJB.existeSocio(tmpS)) {
                out.println("No existe el socio, no se puede crear la matricula");
            } else {
                Matricula matri = new Matricula(idInt, fecha);
                Actividad aM = gymEJB.findActividadByName(nomActividad);
                Socio sM = gymEJB.findSocioByNumber(numSocioInt);
                matri.setActividad(aM);
                matri.setSocio(sM);
                if (gymEJB.insertarMatricula(matri)) {
                    out.println("Matricula dada de alta.");
                } else {
                    out.println("Ya existe una matricula con ese id.");
                }
            }
            out.println("<form action=\"index.jsp\" method=\"POST\">"
                    + "Volver a la pagina inicial"
                    + "<input type=\"submit\" name=\"volver\" value=\"Volver\" />"
                    + "</form>");
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
