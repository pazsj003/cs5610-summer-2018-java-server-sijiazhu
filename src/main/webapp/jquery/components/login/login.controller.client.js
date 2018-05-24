(function () {
    var $usernameFld, $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {

        $usernameFld = $('#inputUsername');
        $passwordFld = $('#inputPassword');

        $loginBtn = $('#SignInBtn');

        $loginBtn.click(verifyLogin);
    }


    function verifyLogin() {
        if ($usernameFld.val() == "" || $passwordFld.val() == "") {
            alert("please input username and password");

            window.location.href = '../login/login.template.client.html';

        }


        else {
            login();
        }

    }

    function login() {


        var username = $usernameFld.val();
        var password = $passwordFld.val();


        userService
            .login(username, password)
    }
})();
