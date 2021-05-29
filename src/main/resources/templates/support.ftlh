<#include "./base/standartPage.ftl" />
<#import "spring.ftl" as spring/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create post</title>


    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/cover/">

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">


    <script type="application/javascript">
        function findUser() {
            let form = $("#searchForm")[0];
            let data = getFormData($("#searchForm"));
            let rootEl = document.getElementById("search_box-result");
            rootEl.innerHTML = '';
            console.log(JSON.stringify(data));

            $.ajax({
                type: "POST",
                url: "/search",
                data: JSON.stringify(data),
                processData: false,
                cache: false,
                contentType: 'application/json; charset=utf-8',
                timeout: 600000,
                success: function (data) {
                    for (let i = 0; i < data.length; i++) {
                        let msg = data[i] + ""
                        let parsed = msg.split(";")
                        let el = document.createElement("div");
                        el.innerHTML =
                            "<div class=\"search_result\"><table><tr><td class=\"search_result-name\">" + parsed[1] + "</a></td><td class=\"search_result-btn\"><a href=\"#\">Перейти</a></td> </tr> </table> </div>"
                        rootEl.appendChild(el);
                    }

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

    <style>
        .margin{
            margin-inline: 30%;
        }
    </style>

</head>
<body>
<@standardPage>
    <form method="post" action="/sendSupportMessage">
        <div class="form-group margin">
            <label for="emailInput">Email address</label>
            <input type="email" name="email" class="form-control" id="emailInput" placeholder="name@example.com">
        </div>
        <div class="form-group margin">
            <label for="textAreaInput">Input message</label>
            <textarea name="text" class="form-control" id="textAreaInput" rows="5"></textarea>
        </div>
        <br>
        <button type="submit" class="btn btn-success margin">Send</button>
    </form>
</@standardPage>
</body>

</html>