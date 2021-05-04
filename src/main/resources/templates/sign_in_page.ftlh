<#include "./base/standartPage.ftl" />

<@standardPage>
    <div>
    <#if error?has_content>
        <div class="reg-container">
            <p><div class="alert alert-warning alert-dismissible fade show" role="alert">
                ${error}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div></p>
        </div>
    <#elseif info?has_content>
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">Поздравляю!</h4>
            <p>Ваш аккаунт был зарегестрирован, перед входом нужно подтвердить электронную почту</p>
        </div>
    </#if>
    <body>
    <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="/login" method="post">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="email" class="text-info">Email:</label><br>
                                <input type="text" name="email" id="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="remember" class="text-info">Remember me:</label><br>
                                <input type="checkbox" name="justRememberMePls" id="remember"/>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="Enter">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="/signUp" class="text-info">Register here</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</@standardPage>