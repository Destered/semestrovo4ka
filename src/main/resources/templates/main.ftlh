<#include "./base/standartPage.ftl" />
<#import "spring.ftl" as spring/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Site site site</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/cover/">

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">


    <script type="application/javascript">
        function findUser() {
            let form = $("#searchForm")[0];
            let data = getFormData($("#searchForm"));
            let rootEl = document.getElementById("search_box-result");
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

        function getFormData($form) {
            var unindexed_array = $form.serializeArray();
            var indexed_array = {};

            $.map(unindexed_array, function (n, i) {
                indexed_array[n['name']] = n['value'];
            });

            return indexed_array;
        }

    </script>

</head>
<body class="text-center">
<@standardPage>

    <h1><@spring.message 'main.title'/></h1>
    <h2><a href="?lang=en">EN</a></h2>
    <h2><a href="?lang=ru">РУ</a></h2>


</@standardPage>
</body>
</html>