<h1> Gerenciador de Livros - Tutorial </h1>

<h2> Funcionalidades </h2>
<ol>
    <li> <h3> Inserir livros </h3> </li>
    <ul>
        <li> Padrão: informe o título, autores, data de publicação, ISBN, editoras <br>
            e livros semelhantes (opcional) para o livro ser gravado da forma padrão. </li>
        <li> Utilizando ISBN: informe o ISBN do livro para que os dados sejam preenchidos <br> automaticamente. </li>
        <li> Importando um arquivo com os livros: o arquivo deve ser do tipo .csv e conter <b>sempre</b> o cabeçalho<br>
            no seguinte formato: "titulo,autores,publicacao,isbn,editoras,semelhantes", vide arquivo <br> 
            `resources/csv/test.csv` como exemplo. se o livro contido no csv já estiver na base de dados, ele <br>
            será atualizado com os novos dados do csv. 
        </li>
    </ul>

    <li> <h3> Editar Livros </h3> </li>
    <ul>
        <li> Selecione o livro que deseja editar e, no menu, selecione a opção "Editar", uma janela se abrirá <br>
            com as informações atuais do livro e você poderá editá-las.
        </li>
    </ul>
    
    <li> <h3> Excluir Livros </h3> </li>
    <ul>    
        <li> Selecione a opção "Deletar" no menu principal e, em seguida, digite o ID do livro que deseja excluir. </li>
    </ul>
    
    <li> <h3> Listar Livros </h3> </li>
    <ul>    
        <li> A janela principal já lista todos os livros cadastrados automaticamente, mas você pode utilizar quaisquer campos <br>
            para filtrá-los também. Basta selecionar o campo, digitar o termo de busca e pressionar o botão de pesquisa.
        </li>
        <li> Existe também o botão para recarregar a tabela, caso queira ver as atualizações mais recentes ou se quiser que a <br>
            tabela volte a mostrar todos os registros.
        </li>
        <li> Para pesquisar por datas, utilize o formato "yyyy-MM-dd" </li>
</ol>