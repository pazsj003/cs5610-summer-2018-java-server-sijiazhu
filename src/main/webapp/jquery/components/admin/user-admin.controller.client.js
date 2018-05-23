(function () {

        var $usernameFld, $passwordFld;
        var $removeBtn, $editBtn, $createBtn, $updateBtn;
        var $firstNameFld, $lastNameFld, roleFld;
        var $userRowTemplate, $tbody;
        var userService = new UserServiceClient();
        var currentUserId;


        $(main);

        function main() {
            $tbody = $('.wbdv-tbody');
            $usernameFld = $('#userNameFld');
            $passwordFld = $('#passwordFld');
            $removeBtn = $('#wbdv-remove');
            $editBtn = $('#wbdv-edit');
            $updateBtn = $('#wbdv-update');
            $createBtn = $('#wbdv-create');
            $firstNameFld = $('#firstNameFld');
            $lastNameFld = $('#lastNameFld');
            $roleFld = $('#roleFld');


            $userRowTemplate = $('.wbdv-template.wbdv-user')
                .clone()
                .removeClass('wbdv-hidden');

            $createBtn.click(createUser);

            findAllUsers();
        }

        function deleteUser(event) {

            var deletBtn = $(event.currentTarget);
            var userID = deletBtn
                .parent()
                .parent()
                .parent()
                .attr('id');
            userService
                .deleteUser(userID)
                .then(findAllUsers);
        }


        function createUser() {
            var username = $usernameFld.val();
            var password = $passwordFld.val();
            var firstName = $firstNameFld.val();
            var lastName = $lastNameFld.val();
            var role = $roleFld.val();

            var user = {
                username: username,
                password: password,
                firstName: firstName,
                lastName: lastName,
                role: role,
            }

            userService
                .createUser(user)
                .then(findAllUsers);

        }

        function findAllUsers() {
            userService
                .findAllUsers()
                .then(renderUsers);

        }

        function findUserById(userId) {
            userService
                .findUserById(userId)
                .then(renderUser);
        }


        function updateUser() {


            var userUpdate = {
                username: $usernameFld.val(),
                password: $passwordFld.val(),
                firstName: $firstNameFld.val(),
                lastName: $lastNameFld.val(),
                role: $roleFld.val(),
            };

            userService
                .updateUser(currentUserId, userUpdate)
                .then(findAllUsers);

            currentUserId=null;
        }


        function selectUser() {
            var selectBtn = $(event.currentTarget);
            var userID = selectBtn
                .parent()
                .parent()
                .parent()
                .attr('id');

            console.log(userID);
            currentUserId = userID;
            findUserById(userID);
            $updateBtn.click(updateUser);

        }

        function renderUser(user) {

            $usernameFld.val(user.username);
            $passwordFld.val(user.password);
            $firstNameFld.val(user.firstName);
            $lastNameFld.val(user.lastName);
            $tbody.empty();
            var $row = $userRowTemplate.clone();
            $row.attr('id', user.id);

            $row.find('.wbdv-remove').click(deleteUser);
            $row.find('.wbdv-edit').click(selectUser);

            $row.find('.wbdv-username')
                .html(user.username);
            $row.find('.wbdv-first-name')
                .html(user.firstName);
            $row.find('.wbdv-last-name')
                .html(user.lastName);
            $row.find('.wbdv-role')
                .html(user.role);
            $tbody.append($row);

        }

        function renderUsers(users) {
            $tbody.empty();
            $usernameFld.val("");
            $passwordFld.val("");
            $firstNameFld.val("");
            $lastNameFld.val("");
            for (var i = 0; i < users.length; i++) {
                var user = users[i];
                var $row = $userRowTemplate.clone();
                $row.attr('id', user.id);

                $row.find('.wbdv-username')
                    .html(user.username);
                $row.find('.wbdv-password')
                    .html("*********");
                $row.find('.wbdv-first-name')
                    .html(user.firstName);
                $row.find('.wbdv-last-name')
                    .html(user.lastName);
                $row.find('.wbdv-role')
                    .html(user.role);

                $tbody.append($row);

                $row.find('.wbdv-remove').click(deleteUser);
                $row.find('.wbdv-edit').click(selectUser);

            }

        }

    }
)();