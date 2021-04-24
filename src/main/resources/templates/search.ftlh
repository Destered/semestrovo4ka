<!doctype html>
<html lang="en">
<head>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Document</title>
</head>
<body>
<script type="application/javascript">
    function search() {
        let form = $("#searchForm")[0];
        let data = getFormData($("#searchForm"));
        console.log(JSON.stringify(data));

        $.ajax({
            type: "POST",
            url: form.action,
            data: JSON.stringify(data),
            processData: false,
            cache: false,
            contentType: 'application/json',
            timeout: 600000,
            success: function (data) {
                var a = JSON.stringify(data);
                $('#result').text(a);
            },
            error: function (err) {
                console.log(JSON.stringify(err));
                alert(JSON.stringify(err));
            }
        });
    }
    function getFormData($form){
        var unindexed_array = $form.serializeArray();
        var indexed_array = {};

        $.map(unindexed_array, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }

</script>
<form id="searchForm" action="/search">
    <input name="name" type="text" id="name" placeholder="Введите имя пользователя"/>
    <input type="number" name="page" id="page" placeholder="Введите номер страницы"/>
    <input type="number" name="size" id="size" placeholder="Введите размер страницы"/>
    <button type="button" onclick="search()"> Поиск по пользователям</button>
</form>
<div id="result">

</div>
</body>
</html>