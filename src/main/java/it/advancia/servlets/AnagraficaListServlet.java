/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.advancia.servlets;

import it.advancia.model.Anagrafica;
import it.advancia.model.LogOperazioniANSC;
import it.advancia.repository.AnagraficaRepository;
import it.advancia.repository.LogOperazioniANSCRepository;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lavoro
 */
public class AnagraficaListServlet extends HttpServlet {

    
    private AnagraficaRepository anagraficaRepository = AnagraficaRepository.getAnagraficaRepository();
    LogOperazioniANSCRepository logOperazioniANSCRepository = LogOperazioniANSCRepository.getLogOperazioniANSCRepository();
    
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
            out.println("<title>Servlet AnagraficaListServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AnagraficaListServlet at " + request.getContextPath() + "</h1>");
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
        
        Map<Anagrafica, List<LogOperazioniANSC>> allAnagraficheWithLogOperazioniANSC = anagraficaRepository.getAllAnagraficheWithLogOperazioniANSC();
        List<String> allIdRiferimento = logOperazioniANSCRepository.getAllIdRiferimento();
        
        request.setAttribute("anagraficheConLog", allAnagraficheWithLogOperazioniANSC);
        request.setAttribute("idRiferimentoAll", allIdRiferimento);
        
        getServletContext().getRequestDispatcher("/elencoAnagrafiche.jsp").forward(request, response);
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
        
        Map<Long,String> idRiferimentoPerAnagrafica = new HashMap<>();
        for(Map.Entry<String,String[]> entry : request.getParameterMap().entrySet()) {
            Long newKey = Long.valueOf(entry.getKey().replace("idRiferimento_",""));
            String newValue = entry.getValue()[0];
            idRiferimentoPerAnagrafica.put(newKey,newValue);
        }
        
        if(idRiferimentoPerAnagrafica.size()!=idRiferimentoPerAnagrafica.values().stream().distinct().count()) {
            // due idRiferimento uguali
            request.setAttribute("error", "Errore nel salvataggio dei idRiferimento: due o più anagrafiche hanno lo stesso valore");
        }
        else {
            // ok, posso salvare
            anagraficaRepository.update(idRiferimentoPerAnagrafica);
            request.setAttribute("esito", "Salvataggio eseguito con successo");
        }
//        List<LogOperazioniANSC> executeQueryWhere = logOperazioniANSCRepository.executeQueryWhere(where.toString());
        
//        if(!executeQueryWhere.isEmpty()) {
//            request.setAttribute("LogOperazioniANSC", executeQueryWhere);
//        }
//        response.sendRedirect("/anagrafiche");
        
        //processRequest(request, response);
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
