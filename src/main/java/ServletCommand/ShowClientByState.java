/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServletCommand;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simplejdbc.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author asegrest
 */
@WebServlet(name = "ShowClientByState", urlPatterns = {"/ShowClientByState"})
public class ShowClientByState extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style> \n" + "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "  border-collapse: collapse;\n" +
                        "}\n" + "</style>");
            out.println("<title>Servlet ShowClientByState</title>");            
            out.println("</head>");
            out.println("<body>");
            try {
            String state = request.getParameter("state");
            if (state == null){
                throw new Exception("Paramètre state non fournis");
            }
            
            DAO d = new DAO(DataSourceFactory.getDataSource());
            List<CustomerEntity> liste = d.customersInState(state);
            if (liste.isEmpty()){
                throw new Exception("Il n'y pas de client dans cet état");
            }
            out.println("<table style='width:100%'>\n" + "<tr>\n" +
                        "    <th>Id</th>\n" +
                        "    <th>Name</th>\n" +
                        "    <th>Address</th>\n" +
                        "  </tr>");
            
            for(CustomerEntity e : liste){
                out.println("<tr>\n" +
                            "    <td>" +e.getCustomerId()+"</td>\n" +
                            "    <td>" +e.getName()+"</td>\n" +
                            "    <td>"+e.getAddressLine1()+"</td>\n" +
                            "  </tr>");
            }
            }catch (Exception e) {
                out.printf("Erreur : %s", e.getMessage());
            }
            out.println("<h1>Servlet ShowClientByState at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception ex) {
            Logger.getLogger("servlet").log(Level.SEVERE, "Erreur de traitement", ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ShowClientByState.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ShowClientByState.class.getName()).log(Level.SEVERE, null, ex);
        }
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
