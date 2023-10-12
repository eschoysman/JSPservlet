/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package it.advancia.servlets;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import it.advancia.model.LogOperazioniANSC;
import it.advancia.repository.LogOperazioniANSCRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        LogOperazioniANSC logOperazioniANSC = logOperazioniANSCRepository.getBlob( Long.parseLong(request.getParameter("id")));

        InputStream blob = logOperazioniANSC.getAttachment();
        String fileName = logOperazioniANSC.getFileName();

        OutputStream outputStream = response.getOutputStream();

        while(blob.available()>0) {
            outputStream.write(blob.read());
        }
        response.setContentType("application/pdf");
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
            if(!request.getParameter("idOperazioneANSC").trim().isEmpty()) {
                logOperazioniANSC.setIdOperazioneANSC(Long.parseLong(request.getParameter("idOperazioneANSC")));
            }
            if(!request.getParameter("idOperazioneComune").trim().isEmpty()) {
                logOperazioniANSC.setIdOperazioneComune(Long.parseLong(request.getParameter("idOperazioneComune")));
            }
            if(!request.getParameter("idRiferimento").trim().isEmpty()) {
                logOperazioniANSC.setIdRiferimento(request.getParameter("idRiferimento"));
            }
            if(!request.getParameter("codiceOperazioneANSC").trim().isEmpty()) {
                logOperazioniANSC.setCodiceOperazioneANSC(request.getParameter("codiceOperazioneANSC"));
            }
            if(!request.getParameter("nome").trim().isEmpty()) {
                logOperazioniANSC.setNome(request.getParameter("nome"));
            }
            if(!request.getParameter("cognome").trim().isEmpty()) {
                logOperazioniANSC.setCognome(request.getParameter("cognome"));
            }
            if(!request.getParameter("eseguita").trim().isEmpty()) {
                logOperazioniANSC.setEseguita(request.getParameter("eseguita"));
            }
            if(!request.getParameter("note").trim().isEmpty()) {
                logOperazioniANSC.setNote(request.getParameter("note"));
            }
            if(!request.getParameter("operatore").trim().isEmpty()) {
                logOperazioniANSC.setOperatore(request.getParameter("operatore"));
            }
            if(!request.getParameter("data").trim().isEmpty()) {
                logOperazioniANSC.setData(DATE_FORMAT.parse(request.getParameter("data")));
            }
            if(!request.getParameter("idAtto").trim().isEmpty()) {
                logOperazioniANSC.setIdAtto(Long.parseLong(request.getParameter("idAtto")));
            }
            if(!request.getParameter("idOperazioneAnnANSC").trim().isEmpty()) {
                logOperazioniANSC.setIdOperazioneAnnANSC(Long.parseLong(request.getParameter("idOperazioneAnnANSC")));
            }
            if(!request.getParameter("idOperazioneAnnComune").trim().isEmpty()) {
                logOperazioniANSC.setIdOperazioneAnnComune(Long.parseLong(request.getParameter("idOperazioneAnnComune")));
            }

            logOperazioniANSCRepository.save(logOperazioniANSC);
            // creazione del PDF CON il idArchivio
            createAndSetPDF(logOperazioniANSC);
            logOperazioniANSCRepository.saveAttachment(logOperazioniANSC);
            
            request.setAttribute("idArchivio", logOperazioniANSC.getIdArchivio());
            getServletContext().getRequestDispatcher("/ricerca").forward(request,response);
            //processRequest(request, response);
        } catch(ParseException | DocumentException e) {
            throw new ServletException(e);
        }
    }

    private void createAndSetPDF(LogOperazioniANSC logOperazioniANSC) throws IOException, DocumentException {
        String fileName = "attachment_"+Instant.now().getEpochSecond()+".pdf";
        System.out.println("START - Creazione PDF con fileName \""+fileName+"\"");
        logOperazioniANSC.setFileName(fileName);
        InputStream inputStream = createPDF(logOperazioniANSC);
        logOperazioniANSC.setAttachment(inputStream);
        System.out.println("END - Creazione PDF");
    }
    
    private InputStream createPDF(LogOperazioniANSC logOperazioniANSC) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);

        document.open();
//        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
//        Chunk title = new Chunk("Riepilogo Log Operazioni ANSC", font);
//        document.add(title);

        PdfPTable table = new PdfPTable(2);
        
        setHeaders(table,"Campo", "Valore");
        
        addRow(table, "ID Archivio", logOperazioniANSC.getIdArchivio());
        addRow(table, "ID Operazione ANSC", logOperazioniANSC.getIdOperazioneANSC());
        addRow(table, "ID Operazione Comune", logOperazioniANSC.getIdOperazioneComune());
        addRow(table, "ID Riferimento", logOperazioniANSC.getIdRiferimento());
        addRow(table, "Codice Operazione ANSC", logOperazioniANSC.getCodiceOperazioneANSC());
        addRow(table, "Nome", logOperazioniANSC.getNome());
        addRow(table, "Cognome", logOperazioniANSC.getCognome());
        addRow(table, "Eseguita", logOperazioniANSC.getEseguita());
        addRow(table, "Note", logOperazioniANSC.getNote());
        addRow(table, "Operatore", logOperazioniANSC.getOperatore());
        addRow(table, "Data", logOperazioniANSC.getData()!=null ? DATE_FORMAT.format(logOperazioniANSC.getData()) : null);
        addRow(table, "ID Atto", logOperazioniANSC.getIdAtto());
        addRow(table, "ID Operazione Ann ANSC", logOperazioniANSC.getIdOperazioneAnnANSC());
        addRow(table, "ID Operazione Ann Comune", logOperazioniANSC.getIdOperazioneAnnComune());

        document.add(table);
        document.close();
        
        InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
        return inputStream;
    }
    
    private void setHeaders(PdfPTable table, String... headers) {
        Arrays.stream(headers)
                .forEach(columnTitle -> {
                  PdfPCell header = new PdfPCell();
                  header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                  header.setBorderWidth(2);
                  header.setPhrase(new Phrase(columnTitle));
                  table.addCell(header);
              });
    }
    private void addRow(PdfPTable table, String campo, Object value) {
        table.addCell(campo);
        table.addCell(value==null ? "" : value.toString());
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
