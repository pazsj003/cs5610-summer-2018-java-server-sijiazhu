(function () {

    var $usernameFld, $firstNameFld, $lastNameFld, $PhoneFld, $EmailFld, $roleFld, $BirthDateFld;
    var $updateBtn, $Logout;

    var userService = new UserServiceClient();


    $(main);

    function main() {
        $usernameFld = $("#UsernameFld");
        $EmailFld = $("#EmailFld");
        $PhoneFld = $("#Phone");
        $roleFld = $("#roleFld");
        $BirthDateFld = $("#DateBirthFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");

        profile();
        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $Logout = $("#logoutBtn").click(logout);


    }

    function logout() {
        userService
            .logout();

    }

    function profile() {
        userService
            .profile()
            .then(renderUser);
    }

    function updateProfile() {
        var user = {
            username: $usernameFld.val(),

            firstName: $firstNameFld.val(),
            lastName: $lastNameFld.val(),
            email: $EmailFld.val(),
            phone: $PhoneFld.val(),
            role: $roleFld.val(),
            dateOfBirth: $BirthDateFld.val()

        };

        userService
            .updateProfile(user);

    }


    function renderUser(user) {
        console.log(user);
        $usernameFld.val(user.username);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $EmailFld.val(user.email);
        $PhoneFld.val(user.phone);
        $roleFld.val(user.role);
        $BirthDateFld.val(user.dateOfBirth);

    }

})();