/*jslint browser:true */

function login() {
    "use strict";
    var form = document.getElementById('login_form'),
        fail_case = document.getElementById('fail'),
        email = document.getElementById('email').value,
        pass = document.getElementById('password').value,
        submit = document.getElementById("submit");
    if (email === 'admin' && pass === 'pass') {
        form.setAttribute('action', 'home.html');
    } else {
        submit.style.margin = "20px 0 30px 0";
        fail_case.textContent = "*Username or Password Incorrect*";
    }
}

function logout() {
    "use strict";
    var lout = document.getElementById('logout');
    lout.setAttribute('href', 'index.html');
}


function openNav() {
    "use strict";
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    "use strict";
    document.getElementById("mySidenav").style.width = "0";
}
