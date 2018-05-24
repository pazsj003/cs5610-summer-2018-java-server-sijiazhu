function UserServiceClient() {
    this.createUser = createUser;
    this.findAllUsers = findAllUsers;
    this.findUserById = findUserById;
    this.deleteUser = deleteUser;
    this.updateUser = updateUser;
    this.updateProfile = updateProfile;
    this.register = register;
    this.login = login;
    this.logout = logout;
    this.profile = Profile;
    this.updateProfile_url = 'http://localhost:8080/api/profile';
    this.user_url = 'http://localhost:8080/api/user';
    this.reg = 'http://localhost:8080/api/register';
    this.login_url = 'http://localhost:8080/api/login';
    this.logout_url = 'http://localhost:8080/api/logout';
    var self = this;

    function updateProfile(user) {
        return fetch(self.updateProfile_url, {
            method: 'put',
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(verifyUpdate).catch((error) => {
            alert(error);
        });


    }

    function Profile() {
        return fetch(self.updateProfile_url, {
            credentials: 'same-origin',
        }).then(function (response) {
            return response.json()

        })
    }


    function verifyUpdate(response) {
        if (response.ok) {

            alert("success update");
        }
        else if (response.status == 409) {
            alert("same username used");
        }
        else throw new Error('cant update');
    }


    function logout() {
        return fetch(self.logout_url).then(goToLogin);
    }

    function goToLogin() {
        window.location.href = '../login/login.template.client.html';
    }

    function register(user) {
        return fetch(self.reg, {
            method: 'post',
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }

        }).then(success).catch((error) => {
            alert(error);
        });


    }

    function success(response) {
        if (response.ok) {

            alert("success register");
            goToProfile();
        }

        else throw new Error('username used');

    }


    function login(username, password) {
        return fetch(self.login_url, {
            method: 'post',
            credentials: 'same-origin',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(verifyLogin).catch((error) => {
            alert(error);
        });


    }

    function verifyLogin(response) {
        if (response.ok) {

            alert("success Log In")
            goToProfile();
        }
        else {
            throw new Error('username password dont match')
        }

    }

    function goToProfile() {
        window.location.href = '../profile/profile.template.client.html';
    }

    function findUserById(userId) {
        return fetch(self.user_url + '/' + userId)
            .then(function (response) {
                return response.json()

            })
    }

    function updateUser(userId, user) {
        return fetch(self.user_url + '/' + userId, {
            method: 'put',
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(verifyUpdate).catch((error) => {
            console.log(error);
        });

    }


    function findAllUsers() {
        return fetch(self.user_url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.user_url, {
            method: 'post',
            credentials: 'same-origin',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(successCreate).catch((error) => {
            alert(error);
        });
    }

    function successCreate(response) {
        if (response.ok) {

            alert("success Create an Account")

        } else if (response.status == 409) {
            alert("same user name used");
        }

        else throw new Error('cant Create')

    }


    function deleteUser(userID) {
        return fetch(self.user_url + '/' + userID, {
            method: 'delete'
        }).then(successdelete).catch((error) => {
            alert(error);
        });


    }

    function successdelete(response) {
        if (response.ok) {

            alert("success delete")

        }

        else throw new Error('cant not delete')

    }
}
