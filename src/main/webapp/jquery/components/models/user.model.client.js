


function User(username, password, firstName, lastName) {
    this.username = username;
    this.password = password;
    this.firstname=firstName;
    this.lastname=lastName;

    this.setUsername = setUsername;
    this.setPassword=setPassword;
    this.setFirstname=setFirstname;
    this.setLastname=setLastname;


    this.getUsername=getUsername;
    this.getPassword = getPassword;
    this.getFirstname=getFirstname;
    this.getLastname=getLastname;


   self=this;



    function setUsername(username) {
        self.username = username;
    }

    function setPassword(password) {
        self.password = password;
    }

    function setFirstname(firstName) {
        self.firstname = firstName;
    }

    function setLastname(lastName) {
        self.lastname = lastName;
    }




    function getUsername() {
        return self.username;
    }
    function getPassword() {
        return self.password;
    }
    function getFirstname() {
        return self.firstname;
    }
    function getLastname() {
        return self.lastname;
    }

}
