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
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script>
        let webSocket;

        function connect() {
            webSocket = new WebSocket('ws://localhost:8080/chatConnect');

            webSocket.onopen = function loadMsg() {
                let message = {
                    "text" : '[init chat]',
                    "from": '-1',
                };
                webSocket.send(JSON.stringify(message));
            }
            webSocket.onmessage = function receiveMessage(response) {
                let json = JSON.parse(response['data']);
                $('#messagesList').first().after("<li>" + json['from'] + ': ' + json['text'] + "</li>")
            }
        }

    </script>
</head>
<body onload="connect()">
<@standardPage>
<div id="info">

    <h3>Чат</h3>
    <label for="message">Текст сообщения</label>
    <input name="message" id="message" placeholder="Сообщение">
    <button v-on:click="sendMessage">Отправить</button>
    <ul id="messagesList">

    </ul>

</div>
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
        },
        methods: {
            sendMessage:
                function (event){
                    let message = {
                        "text" : document.getElementById('message').value,
                        "from": '${username}',
                    };
                    webSocket.send(JSON.stringify(message));
                    document.getElementById('message').value = '';
            }
        }
    });
</script>
</@standardPage>
</body>
</html>
