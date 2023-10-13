/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.advancia.servlets;

import it.advancia.model.Anagrafica;
import it.advancia.model.Multa;
import it.advancia.repository.AnagraficaRepository;
import it.advancia.repository.MultaRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lavoro
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/ricercaMulte"})
public class RicercaMulteServlet extends HttpServlet {

    private AnagraficaRepository anagraficaRepository = AnagraficaRepository.getAnagraficaRepository();
    private MultaRepository multaRepository = MultaRepository.getMultaRepository();
    
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
        
        String idAnagraficaString = request.getParameter("idUtente");
        if(idAnagraficaString!=null && !idAnagraficaString.trim().isEmpty()) {
            Long idAnagrafica = Long.valueOf(idAnagraficaString);
            List<Multa> multe = multaRepository.recuperaPerAnagrafica(idAnagrafica);
            if(!multe.isEmpty()) {
                request.setAttribute("listaMulte", multe);
                request.getServletContext().getRequestDispatcher("/elencoMulte.jsp").forward(request, response);
            }
        }
        else {
            List<Anagrafica> allAnagrafica = anagraficaRepository.getAllAnagrafica();
            request.setAttribute("anagrafiche", allAnagrafica);
            request.getServletContext().getRequestDispatcher("/ricercaMulte.jsp").forward(request, response);
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
