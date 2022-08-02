package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>EJB Gym</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>Gym DAM</h1>\n");
      out.write("        <h2>-------------------------------------------------</h2>\n");
      out.write("        <h2>Obtener datos</h2>\n");
      out.write("        <form action=\"ServletActividad\" method=\"POST\">Presiona el botón para obtener las actividades.\n");
      out.write("            <input type=\"submit\" name=\"enviar\" value=\"Enviar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"ServletSocio\" method=\"POST\">Presiona el botón para obtener los socios.\n");
      out.write("            <input type=\"submit\" name=\"enviar\" value=\"Enviar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"ServletMatricula\" method=\"POST\">Presiona el botón para obtener las matriculas.\n");
      out.write("            <input type=\"submit\" name=\"enviar\" value=\"Enviar\" />\n");
      out.write("        </form>\n");
      out.write("        <br>\n");
      out.write("        <form action=\"Buscar.html\" method=\"POST\">Presiona el botón para hacer una busqueda personalizada.\n");
      out.write("            <input type=\"submit\" name=\"enviar\" value=\"Enviar\" />\n");
      out.write("        </form>\n");
      out.write("        <h2>-------------------------------------------------</h2>\n");
      out.write("        <h2>Gestión Actividades</h2>\n");
      out.write("        <form action=\"insertarActividad.html\" method=\"POST\">Presiona el botón para añadir las actividades.\n");
      out.write("            <input type=\"submit\" name=\"insertActividad\" value=\"Insertar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"modificarActividad.html\" method=\"POST\">Presiona el botón para modificar las actividades.\n");
      out.write("            <input type=\"submit\" name=\"modActividad\" value=\"Modificar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"eliminarActividad.html\" method=\"POST\">Presiona el botón para eliminar las actividades.\n");
      out.write("            <input type=\"submit\" name=\"elActividad\" value=\"Eliminar\" />\n");
      out.write("        </form>\n");
      out.write("        <h2>-------------------------------------------------</h2>\n");
      out.write("        <h2>Gestión Socios</h2>\n");
      out.write("        <form action=\"insertarSocio.html\" method=\"POST\">Presiona el botón para añadir los socios.\n");
      out.write("            <input type=\"submit\" name=\"insertSocio\" value=\"Insertar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"modificarSocio.html\" method=\"POST\">Presiona el botón para modificar los socios.\n");
      out.write("            <input type=\"submit\" name=\"modSocio\" value=\"Modificar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"eliminarSocio.html\" method=\"POST\">Presiona el botón para eliminar los socios.\n");
      out.write("            <input type=\"submit\" name=\"elSocio\" value=\"Eliminar\" />\n");
      out.write("        </form>\n");
      out.write("        <h2>-------------------------------------------------</h2>\n");
      out.write("        <h2>Gestión Matrículas</h2>\n");
      out.write("        <form action=\"insertarMatricula.html\" method=\"POST\">Presiona el botón para añadir las matriculas.\n");
      out.write("            <input type=\"submit\" name=\"insertSocio\" value=\"Insertar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"modificarMatricula.html\" method=\"POST\">Presiona el botón para modificar las matriculas.\n");
      out.write("            <input type=\"submit\" name=\"modSocio\" value=\"Modificar\" />\n");
      out.write("        </form>\n");
      out.write("        <form action=\"eliminarMatricula.html\" method=\"POST\">Presiona el botón para eliminar las matriculas.\n");
      out.write("            <input type=\"submit\" name=\"elSocio\" value=\"Eliminar\" />\n");
      out.write("        </form>\n");
      out.write("        <h2>-------------------------------------------------</h2>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
