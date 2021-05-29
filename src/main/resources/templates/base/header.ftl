<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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

</head>
<body class="text-center">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/main">DisorganiseDeficiency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if isLogged!false == true>
                <li class="nav-item active">
                    <a class="nav-link" href="/profile">Profile <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/createPost">Create post</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/allPosts">List of posts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Logout</a>
                </li>
            <#else>
                <li class="nav-item active">
                    <a class="nav-link" href="/signUp">Sign Up <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Sign In</a>
                </li>
            </#if>
            <li class="nav-item">
                <a class="nav-link" href="/support">Support</a>
            </li>
            <li>
                <div class="search_box">
                    <form autocomplete="off" id="searchForm">
                        <input type="text" name="name" id="name" placeholder="<@spring.message 'header_search.text'/>">
                        <button type="button" onclick="findUser()"> <@spring.message 'header_search.button'/></button>
                    </form>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div id="search_box-result"></div>


</div>