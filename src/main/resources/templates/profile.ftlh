<#include "./base/standartPage.ftl" />
<#import "spring.ftl" as spring/>
<@standardPage>
    <body>
    <div class="row py-5 px-4">
        <div class="col-md-5 mx-auto">
            <div class="bg-white shadow rounded overflow-hidden">
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">
                        <div class="profile mr-3">
                            <#if avatarImageName?has_content>
                                <img src="/img/${avatarImageName}" width="130" class="rounded mb-2 img-thumbnail">
                            <#else>
                                <img src="https://www.seekpng.com/png/detail/110-1100707_person-avatar-placeholder.png" width="130" class="rounded mb-2 img-thumbnail">
                            </#if>
                            <#if isOwner == true>
                            <a href="/editProfile" class="btn btn-outline-dark btn-sm btn-block"><@spring.message 'profile.edit_profile'/></a></div>
                        </#if>
                        <div class="px-2 mb-5">
                            <h4 class="mt-0 mb-0">${username}</h4>
                        </div>
                    </div>
                </div>
                <div class="px-4 py-5">
                    <h5 class="mb-0"><@spring.message 'profile.about'/></h5>
                    <div class="p-4 rounded shadow-sm bg-light">
                        <p><@spring.message 'profile.email'/>: ${email}</p>
                        <p><@spring.message 'profile.role'/>: ${role}</p>
                        <#if phone?has_content>
                            <p><@spring.message 'profile.phone'/>: ${phone}</p>
                        <#else>
                            <p><@spring.message 'profile.phone'/>: <@spring.message 'profile.phoneNotInput'/></p>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</@standardPage>
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