/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.advancia.servlets;

import it.advancia.model.LogOperazioniANSC;
import it.advancia.repository.LogOperazioniANSCRepository;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Lavoro
 */
@MultipartConfig
public class InsertLogServlet extends HttpServlet {

    LogOperazioniANSCRepository logOperazioniANSCRepository = LogOperazioniANSCRepository.getLogOperazioniANSCRepository();

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
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
            out.println("<title>Servlet InsertLogServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertLogServlet at " + request.getContextPath() + "</h1>");
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

        LogOperazioniANSC loansc = logOperazioniANSCRepository.getBlob( Long.parseLong(request.getParameter("id")));

        InputStream blob = loansc.getAttachment();
        String fileName = loansc.getFileName();

        OutputStream outputStream = response.getOutputStream();

        while (blob.available() > 0) {
            outputStream.write(blob.read());
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", fileName));

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
    //        Part part = request.getPart("file");
    //        System.out.println(part);
            request.getParameterMap().forEach((k,v)->System.out.println(k+" : "+Arrays.toString(v)));

            LogOperazioniANSC logOperazioniANSC = new LogOperazioniANSC();
            logOperazioniANSC.setIdOperazioneANSC(Long.parseLong(request.getParameter("idOperazioneANSC")));
            logOperazioniANSC.setIdOperazioneComune(Long.parseLong(request.getParameter("idOperazioneComune")));
            logOperazioniANSC.setIdRiferimento(request.getParameter("idRiferimento"));
            logOperazioniANSC.setCodiceOperazioneANSC(request.getParameter("codiceOperazioneANSC"));
            logOperazioniANSC.setNome(request.getParameter("nome"));
            logOperazioniANSC.setCognome(request.getParameter("cognome"));
            logOperazioniANSC.setEseguita(request.getParameter("eseguita"));
            logOperazioniANSC.setNote(request.getParameter("note"));
            logOperazioniANSC.setOperatore(request.getParameter("operatore"));
            logOperazioniANSC.setData(DATE_FORMAT.parse(request.getParameter("data")));
            logOperazioniANSC.setIdAtto(Long.parseLong(request.getParameter("idAtto")));
            logOperazioniANSC.setIdOperazioneAnnANSC(Long.parseLong(request.getParameter("idOperazioneAnnANSC")));
            logOperazioniANSC.setIdOperazioneAnnComune(Long.parseLong(request.getParameter("idOperazioneAnnComune")));
            logOperazioniANSC.setFileName("attachment_"+Instant.now().getEpochSecond()+".pdf");
            // TODO $$$ auto creazione del pdf tramite iText
            createPDF(logOperazioniANSC);
            logOperazioniANSC.setAttachment(null);

            int idArchivio = logOperazioniANSCRepository.save(logOperazioniANSC);
            System.out.println("idArchivio generato: "+idArchivio);
            request.setAttribute("idArchivio", idArchivio);
            getServletContext().getRequestDispatcher("/ricerca").forward(request,response);

            //processRequest(request, response);
        } catch(ParseException e) {
            throw new ServletException(e);
        }
    }

    private void createPDF(LogOperazioniANSC logOperazioniANSC) {
        
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
