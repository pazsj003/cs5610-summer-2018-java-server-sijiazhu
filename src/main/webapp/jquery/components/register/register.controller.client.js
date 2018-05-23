(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld;
    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);


    function main() {
        $usernameFld = $('#inputUsername');
        $passwordFld = $('#inputPassword');
        $verifyPasswordFld = $('#verifyPassword');
        $registerBtn = $('#registerBtn');


        $registerBtn.click(register);
    }


    function register() {


        var username = $usernameFld.val();
        var password = $passwordFld.val();


        var user = {
            username: username,
            password: password,

        }

        userService
            .register(user);
    }


})();
