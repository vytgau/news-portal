/***************************************************
* ********** Side menu toggle script ***************
****************************************************/
// Look for .hamburger
var hamburger = document.querySelector(".hamburger");

// On click
hamburger.addEventListener("click", function() {
    // Toggle class "is-active"
    hamburger.classList.toggle("is-active");

    // Open side menu
    $("#wrapper").toggleClass("toggled");
});

