<#include "./base/standartPage.ftl" />
<#import "spring.ftl" as spring/>

<@standardPage>
    <div>
    <#if error?has_content>
        <div class="reg-container">
            <p>Error: ${error}</p>
        </div>
    <#else>
    </#if>

    <body>
    <div id="login">
        <h3 class="text-center text-white pt-5"><@spring.message 'sign_up_page.registration.title'/></h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="/signUp" method="post">
                            <h3 class="text-center text-info"><@spring.message 'sign_up_page.registration.title'/></h3>
                            <div class="form-group">
                                <label for="email" class="text-info"><@spring.message 'sign_up_page.registration.email'/>:</label><br>
                                <input type="text" name="email" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="username" class="text-info"><@spring.message 'sign_up_page.registration.username'/>:</label><br>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info"><@spring.message 'sign_up_page.registration.password'/>:</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="<@spring.message 'sign_up_page.registration.sendButton'/>">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="/signIn" class="text-info"><@spring.message 'sign_up_page.registration.login'/></a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</@standardPage>