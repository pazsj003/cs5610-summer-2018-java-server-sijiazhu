(function () {
    var $usernameFld, $passwordFld, $verifyPasswordFld,
        $firstNameFld, $lastNameFld;

    var $registerBtn;
    var userService = new UserServiceClient();
    $(main);


    function main() {
        $usernameFld = $('#inputUsername');
        $passwordFld = $('#inputPassword');
        $verifyPasswordFld = $('#verifyPassword');
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $registerBtn = $('#registerBtn');

        $registerBtn.click(register);
    }


    function register() {


        var username = $usernameFld.val();
        var password = $passwordFld.val();


        var user = {
            username: username,
            password: password,
            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),

        }

        userService
            .register(user);
    }


})();
