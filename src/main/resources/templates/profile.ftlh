<#include "./base/standartPage.ftl" />
<!doctype html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
</head>
<body>
<@standardPage>
    <div id="info">
        <a href="/customLogout">logout</a>
        <h1>Profile Page</h1>
        <p>Username: {{ info.username }}</p>
        <p>Email: {{ info.email }}</p>
        <p>Role: {{ info.role }}</p>
        <a href="/editProfile">edit</a>
    </div>
</@standardPage>

<script>
    let vm = new Vue({
        el: "#info",
        data: {info:[]},
        mounted() {
            axios.get('/getProfile').then(
                res => {
                    this.info = res.data;
                }
            );
        }
    });
</script>
</body>
</html>