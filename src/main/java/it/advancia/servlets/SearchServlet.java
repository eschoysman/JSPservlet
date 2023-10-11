/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.advancia.servlets;

import it.advancia.model.LogOperazioniANSC;
import it.advancia.repository.LogOperazioniANSCRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.misc.MessageUtils;

/**
 *
 * @author Lavoro
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/ricerca"})
public class SearchServlet extends HttpServlet {

    private LogOperazioniANSCRepository logOperazioniANSCRepository = LogOperazioniANSCRepository.getLogOperazioniANSCRepository(); 
    
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
        
        
        if(request.getAttribute("LogOperazioniANSC")!= null){
        
        getServletContext().getRequestDispatcher("/risultatiRicerca.jsp").forward(request, response);
        
        }
        
        
        
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchServlet at " + request.getContextPath() + "</h1>");
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
        
        
        Map<String, String[]> parameterMap = request.getParameterMap();
        
//        StringBuilder builder = new StringBuilder();
        StringJoiner where = new StringJoiner(" AND ").setEmptyValue("true");
        
        for(Entry<String,String[]> entry : parameterMap.entrySet() ){
        
            String key = entry.getKey();
            String value = entry.getValue()[0];

            if(!value.trim().isEmpty()){
                if(key.equals("dataFrom")){
                    where.add(String.format("\"data\" >= '%s'", value));
                }
                else if(key.equals("dataTo")){
                    where.add(String.format("\"data\" <= '%s'", value));
                }
                else{
                    where.add(String.format("\"%s\"::TEXT like '%%%s%%'", key, value));
                }
            }
        }
        
        List<LogOperazioniANSC> executeQueryWhere = logOperazioniANSCRepository.executeQueryWhere(where.toString());
        
        if(!executeQueryWhere.isEmpty()) {
            request.setAttribute("LogOperazioniANSC", executeQueryWhere);
        }
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
