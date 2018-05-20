

(function () {


        $(main);
        var tbody;
        var template;
        var userService= new UserServiceClient();
        function main() {
            tbody = $('tbody');

            template = $('.template');
            $('#createUser').click(createUser);

            findAllUsers();
        }

        function findAllUsers(){
             userService
                 .findAllUsers()
                 .then(renderUser);

        }

        function createUser(){
            var username=$('#userNameFld').val();
            var password=$('#passwordFld').val();
            var firstName=$('#firstNameFld').val();
            var lastName=$('#lastNameFld').val();

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
        function renderUser(users) {
            tbody.empty();
            for (var i = 0; i < users.length; i++) {
                var user = users[i];

                var clone = template.clone();
                 clone.attr('id',user.id);
                clone.find('.delete').click(deleteUser);
                clone.find('.edit').click(deleteUser);
                clone.find('.username')
                    .html(user.username);

                tbody.append(clone);
            }

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

        function editUser(event){

        }
    }
)();