
<div id="container" hidden="false">
    <div class="backdrop" onclick="document.getElementById('container').hidden = true" >
    </div>
    <div class="modal">
        <form action="inserisci" method="POST" enctype="multipart/form-data">
            <table class="tavolo">
                <tr>
                    <th class="title" colspan="2">Inserisci dati</th>
                </tr>
                <tr>
                    <th class="label">
                        idArchivio        
                    </th>
                    <td>
                        <input type="text" disabled/>
                    </td>
                </tr>
                <tr>
                    <th class="label">idAtto</th>
                    <td><input name="file" type="file"/></td>
                </tr>
                <tr>
                    <th class="label">note</th>
                    <td><input type="text" name="note"/></td>
                </tr>
            </table>
            <input type="submit"/>
        </form>
    </div>
</div>