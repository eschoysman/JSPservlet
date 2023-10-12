
<div id="modalInserimentoAnagrafico" hidden="false">
    <div class="backdrop" onclick="document.getElementById('modalInserimentoAnagrafico').hidden = true"></div>
    <div class="modal">
        <form action="inserisciAnagrafica" method="POST" >
            <table class="tavolo">
                <tr>
                    <th class="title" colspan="2">Inserisci dati</th>
                </tr>
                <tr>
                    <th class="label">
                        Nome
                    </th>
                    <td>
                        <input type="text" name="nome"/>
                    </td>
                </tr>
                <tr>
                    <th class="label">Cognome</th>
                    <td><input name="cognome" type="text"/></td>
                </tr>
                <tr>
                    <th class="label">Data di Nascita</th>
                    <td><input type="date" name="dataNascita"/></td>
                </tr>
                
                <tr>
                    <th class="label">Luogo di Nascita</th>
                    <td><input type="text" name="luogoNascita"/></td>
                </tr>
                
                
            </table>
            <input type="submit"/>
        </form>
    </div>
</div>