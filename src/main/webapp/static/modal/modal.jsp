
<div id="container" hidden="false">
    <div class="backdrop" onclick="document.getElementById('container').hidden =true" >





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
                <th>
                    <input type="text" disabled/>

                </th>


            </tr>

                        <tr>
                <th class="label">idAtto</th>
                <th><input name="file" type="file"/></th>
            </tr>
            
            <tr>
                <th class="label">note</th>
                <th><input type="text"/></th>
            </tr>


        </table>
            
            <input type="submit"/>
        </form>

    </div>
</div>