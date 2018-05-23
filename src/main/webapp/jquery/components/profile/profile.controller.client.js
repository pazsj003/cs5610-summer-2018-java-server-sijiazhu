(function () {

    var $usernameFld, $PhoneFld, $EmailFld, $roleFld, $BirthDateFld;
    var $updateBtn, $Logout;

    var userService = new UserServiceClient();


    $(main);

    function main() {
        $usernameFld = $("#UsernameFld");
        $EmailFld = $("#EmailFld");
        $PhoneFld = $("#Phone");
        $roleFld = $("#roleFld");
        $BirthDateFld = $("#DateBirthFld");
        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $Logout = $("#logoutBtn").click(logout);


        //findUserById(12);


    }

    function logout() {
        userService
            .logout();

    }

    function updateProfile() {
        var user = {
            username: $usernameFld.val(),
            email: $EmailFld.val(),
            phone: $PhoneFld.val(),
            role: $roleFld.val(),
            dateOfBirth: $BirthDateFld.val()


        };

        userService
            .updateProfile(user);


    }


    function success(response) {
        if (response == null) {
            alert('unable to update');
        }
        alert('success');
    }



    function renderUser(user) {
        console.log(user);
        $usernameFld.val(user.username);
        $EmailFld.val(user.email);
        $PhoneFld.val(user.phone);
        $roleFld.val(user.role);
        $BirthDateFld.val(user.dateOfBirth);
    }

})();