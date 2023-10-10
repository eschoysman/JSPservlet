/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.advancia.model;

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
    private String date;
    private String time;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
