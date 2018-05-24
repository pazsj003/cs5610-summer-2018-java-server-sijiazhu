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

        $registerBtn.click(verifyPassword);
    }


    function verifyPassword() {
        if ($passwordFld.val() != $verifyPasswordFld.val()) {
            alert("Password dont match");

            window.location.href = '../register/register.template.client.html';

        } else if ($usernameFld.val() == "" || $passwordFld.val() == "") {
            alert("please input username and password");

            window.location.href = '../register/register.template.client.html';

        }


        else {
            register();
        }

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
