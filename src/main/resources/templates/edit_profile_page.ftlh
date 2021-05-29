<#include "./base/standartPage.ftl" />
<#import "spring.ftl" as spring/>
<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit profile page</title>


    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/cover/">

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.5/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">


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

        function getFormData($form) {
            var unindexed_array = $form.serializeArray();
            var indexed_array = {};

            $.map(unindexed_array, function (n, i) {
                indexed_array[n['name']] = n['value'];
            });

            return indexed_array;
        }

    </script>
    <style>
        #margin {
            margin-inline: 30%;
        }
    </style>

</head>
<body>
<@standardPage>
<form method="post" action="/editProfileHandler">
    <div class="form-group" id="margin">
        <label for="inputUsername">Username</label>
        <input type="text" name="username" v-bind:value="info.username" class="form-control" id="inputUsername"
               aria-describedby="usernameHelper">
        <small id="usernameHelp" v-bind:value="info.phone" class="form-text text-muted">Enter you new username.</small>
    </div>
    <div class="form-group" id="margin">
        <label for="inputPhoneNumber">Phone Number</label>
        <input type="text" name="phone" class="form-control" id="inputPhoneNumber" aria-describedby="phoneHelper">
        <small id="phoneHelp" class="form-text text-muted">Enter new phone (8XXXXXXXXXX).</small>
    </div>
    <button id="margin" type="submit" class="btn btn-primary">Обновить</button>
</form>

    <br>
    <br>

<div class="form-group" id="margin">
    <form method="POST" enctype="multipart/form-data"
          action="/uploadAvatar">
        <label class="form-label" for="customFile">Select user avatar:</label>
        <input  class="file" type="file" name="file"/>
        <input type="submit" class="btn btn-primary" value="Upload">
    </form>
</div>
</@standardPage>
</body>
<script>
    let vm = new Vue({
        el: "#info",
        data: {info: []},
        mounted() {
            axios.get('/getProfile').then(
                res => {
                    this.info = res.data;
                }
            );
        }
    });
</script>
</html>