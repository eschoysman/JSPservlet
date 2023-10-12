/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.model;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Lavoro
 */
public class LogOperazioniANSC {
    
    private long idArchivio;
    private long idOperazioneANSC;
    private long idOperazioneComune;
    private String idRiferimento;
    private String codiceOperazioneANSC;
    private String operatore;
    private Date data;
    private String eseguita;
    private long idAtto;
    private String note;
    private long idOperazioneAnnANSC;
    private long idOperazioneAnnComune;
    private String nome;
    private String cognome;
    private InputStream attachment;
    private String fileName;

    public long getIdArchivio() {
        return idArchivio;
    }

    public void setIdArchivio(long idArchivio) {
        this.idArchivio = idArchivio;
    }

    public long getIdOperazioneANSC() {
        return idOperazioneANSC;
    }

    public void setIdOperazioneANSC(long idOperazioneANSC) {
        this.idOperazioneANSC = idOperazioneANSC;
    }

    public long getIdOperazioneComune() {
        return idOperazioneComune;
    }

    public void setIdOperazioneComune(long idOperazioneComune) {
        this.idOperazioneComune = idOperazioneComune;
    }

    public String getIdRiferimento() {
        return idRiferimento;
    }

    public void setIdRiferimento(String idRiferimento) {
        this.idRiferimento = idRiferimento;
    }

    public String getCodiceOperazioneANSC() {
        return codiceOperazioneANSC;
    }

    public void setCodiceOperazioneANSC(String codiceOperazioneANSC) {
        this.codiceOperazioneANSC = codiceOperazioneANSC;
    }

    public String getOperatore() {
        return operatore;
    }

    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEseguita() {
        return eseguita;
    }

    public void setEseguita(String eseguita) {
        this.eseguita = eseguita;
    }

    public long getIdAtto() {
        return idAtto;
    }

    public void setIdAtto(long idAtto) {
        this.idAtto = idAtto;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getIdOperazioneAnnANSC() {
        return idOperazioneAnnANSC;
    }

    public void setIdOperazioneAnnANSC(long idOperazioneAnnANSC) {
        this.idOperazioneAnnANSC = idOperazioneAnnANSC;
    }

    public long getIdOperazioneAnnComune() {
        return idOperazioneAnnComune;
    }

    public void setIdOperazioneAnnComune(long idOperazioneAnnComune) {
        this.idOperazioneAnnComune = idOperazioneAnnComune;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public InputStream getAttachment() {
        return attachment;
    }

    public void setAttachment(InputStream attachment) {
        this.attachment = attachment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
