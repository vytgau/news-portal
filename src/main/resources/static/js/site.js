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


/***************************************************
 * ********** Invitations modal ********************/
$("#invitations-modal-btn").click(function() {
    $( "#invitations-modal-div" ).empty();

    $.getJSON(
        "/get/new_invitations", function (data) {
            if (data.length != 0) {
                $.each(data, function (index, groupInvitation) {
                    var groupTitle = groupInvitation.group.title;

                    $( "#invitations-modal-div" ).append( "<div class=\"row\" id=\"invitation-" + index + "\"></div>" );
                    $( "#invitation-" + index ).append( "<div class=\"col-sm-8\"><p>" + groupTitle + "</p></div>" );
                    $( "#invitation-" + index ).append( "<div class=\"col-sm-2\"><button type=\"button\" class=\"btn btn-success\" aria-label=\"Left Align\"><i class=\"fas fa-check\"></i></button></div>" );
                    $( "#invitation-" + index ).append( "<div class=\"col-sm-2\"><button type=\"button\" class=\"btn btn-danger\" aria-label=\"Left Align\"><i class=\"fas fa-times\"></i></button></div>" );

                    //$("invitations-modal-div").append("<p>Test test test</p>");

                });
            } else {
                $( "#invitations-modal-div" ).append("<h4>Pakvietimu nera</h4>");
            }

            $("#invitations-modal-btn-hidden").click();
        }
    );
});

