
<div id="modalInserimentoDati" hidden="false">
    <div class="backdrop" onclick="document.getElementById('modalInserimentoDati').hidden = true"></div>
    <div class="modal">
        <form action="inserisci" method="POST" enctype="multipart/form-data">
            <table class="tavolo">
                <tr>
                    <th class="title" colspan="4">Inserisci dati</th>
                </tr>
                <tr>
                    <td class="label">
                        idArchivio
                    </td>
                    <td>
                        <input name="idArchivio" type="text" disabled/>
                    </td>
                    <td class="label">
                        idOperazioneANSC
                    </td>
                    <td>
                        <input name="idOperazioneANSC" type="number" />
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        idOperazioneComune
                    </td>
                    <td>
                        <input name="idOperazioneComune" type="number" />
                    </td>
                    <td class="label">
                        idRiferimento
                    </td>
                    <td>
                        <input name="idRiferimento" type="number" />
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        Codice operazione ANSC
                    </td>
                    <td colspan="3">
                        <input name="codiceOperazioneANSC" type="text" />
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        Nome Cognome
                    </td>
                    <td colspan="3">
                        <input name="nome" type="text" placeholder="Nome" />
                        <input name="cognome" type="text" placeholder="Cognome" />
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        Eseguita
                    </td>
                    <td>
                        <select name="eseguita">
                            <option value="sì">Sì</option>
                            <option value="no">No</option>
                        </select>               
                    </td>
                    <td class="label" rowspan="2">
                        Note
                    </td>
                    <td rowspan="2">
                        <textarea name="note"></textarea>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        Operatore
                    </td>
                    <td>
                        <select name="operatore">
                            <option value="entedev">entedev</option>
                        </select>                
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        Data
                    </td>
                    <td>
                        <input type="date" name="data" />
                    </td>
                    <td class="label">
                        idAtto
                    </td>
                    <td>
                        <input name="idAtto" type="number" />
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        Id Operazione Ann ANSC
                    </td>
                    <td>
                        <input name="idOperazioneAnnANSC" type="number" />
                    </td>
                    <td class="label">
                        id Operazione Ann Comune
                    </td>
                    <td>
                        <input name="idOperazioneAnnComune" type="number" />
                    </td>
                </tr>
            </table>
            <center>
                <input type="submit"/>
            </center>
        </form>
    </div>
</div>