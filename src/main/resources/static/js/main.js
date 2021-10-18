function checkPasswords() {
    if (document.getElementById('confirm').value !== document.getElementById('password').value) {
        document.getElementById("confirm").setCustomValidity("Passwords do not match!");
    } else {
        document.getElementById("confirm").setCustomValidity("");
    }
}

