

(function () {

        var $usernameFld, $passwordFld;
        var $removeBtn, $editBtn, $createBtn;
        var $firstNameFld, $lastNameFld;
        var $userRowTemplate, $tbody;



        $(main);


        var userService= new UserServiceClient();



        function main() {
            $tbody = $('.wbdv-tbody');
            $usernameFld=$('#userNameFld');
            $passwordFld=$('#passwordFld');
            $removeBtn=$('#wbdv-remove');
            $editBtn=$('#wbdv-edit');
            $createBtn=$('#wbdv-create');
            $firstNameFld=$('#firstNameFld');
            $lastNameFld=$('#lastNameFld');



            $userRowTemplate = $('.wbdv-template.wbdv-user')
                .clone()
                .removeClass('wbdv-hidden');

            $createBtn.click(createUser);

            findAllUsers();
        }



        function createUser(){
            var username=$usernameFld.val();
            var password=$passwordFld.val();
            var firstName=$firstNameFld.val();
            var lastName=$lastNameFld.val();

            var user={
                username:username,
                password:password,
                firstName:firstName,
                lastName:lastName
            }

            userService
                .createUser(user)
                .then(findAllUsers);

        }

        function findAllUsers(){
            userService
                .findAllUsers()
                .then(renderUser);

        }
        function findUserById(userId){
            userService
                .findUserById(userId)
                .then(renderUser);
        }

        function updateUser(event){

            var deletBtn =$(event.currentTarget);
            var userID =deletBtn
                .parent()
                .parent()
                .attr('id');

           var user= userService
                .findUserById(userId);

            var $row = $userRowTemplate.clone();
            $row.attr('id',user.id);

            $row.find('.wbdv-username')
                .html(user.username);


            var userUpdate ={
                    username:$usernameFld.val(),
                    password:$passwordFld.val(),
                    firstName:$firstNameFld.val(),
                    lastName:$lastNameFld.val()
                };

                userService
                    .updateUser(userID,user)
                    .then(success);


        }

        function success(response){
            if(response ==null){
                alert('unable to update');
            }
            alert('success');
        }

        function deleteUser(event){

            var deletBtn =$(event.currentTarget);
            var userID =deletBtn
                .parent()
                .parent()

                .attr('id');
            userService
                .deleteUser(userID)
                .then(findAllUsers);
        }
        function selectUser() {
            var deletBtn =$(event.currentTarget);
            var userID =deletBtn
                .parent()
                .parent()
                .attr('id');

            var user= userService
                .findUserById(userID);









            $('#userNameFld').value=user.username;

                $passwordFld.val(user.password);
                $firstNameFld.val(user.firstName);
                $lastNameFld.val(user.lastName);



        }

        function renderUser(users) {
            $tbody.empty();
            for (var i = 0; i < users.length; i++) {
                var user = users[i];



                var $row = $userRowTemplate.clone();
                $row.attr('id',user.id);

                $row.find('.wbdv-remove').click(deleteUser);
                $row.find('.wbdv-edit').click(selectUser);

                $row.find('.wbdv-username')
                    .html(user.username);

                $tbody.append($row);



            }

        }

    }
)();