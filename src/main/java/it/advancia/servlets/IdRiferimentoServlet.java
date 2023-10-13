/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.advancia.servlets;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import it.advancia.repository.LogOperazioniANSCRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringJoiner;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lavoro
 */
public class IdRiferimentoServlet extends HttpServlet {

    LogOperazioniANSCRepository logOperazioniANSCRepository = LogOperazioniANSCRepository.getLogOperazioniANSCRepository();

    public IdRiferimentoServlet() {
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        
        List<String> allIdRiferimento;
        if(request.getParameter("all") == null) 
            allIdRiferimento = logOperazioniANSCRepository.getAllDistinctIdRiferimento();
        else 
            allIdRiferimento = logOperazioniANSCRepository.getAllIdRiferimento();
        
        response.setContentType("text/json");
        
        
        response.getWriter().print(listToJson(allIdRiferimento));
    }
    private String listToJson(List<String> allIdRiferimento){
    
        StringJoiner sj = new StringJoiner(", ");
        
        for(String s : allIdRiferimento){
            sj.add("\""+ s +"\"");
        }        
        return "[" + sj.toString() + "]";
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
