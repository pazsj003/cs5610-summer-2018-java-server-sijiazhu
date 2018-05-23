

(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {

        $usernameFld = $('#inputUsername');
        $passwordFld = $('#inputPassword');

        $loginBtn = $('#SignInBtn');

        $loginBtn.click(login);
    }
    function login() {

        var username=$usernameFld.val();
        var password=$passwordFld.val();



        var user={
            username:username,
            password:password,

        }

        userService
            .login(username,password)
    }
})();
