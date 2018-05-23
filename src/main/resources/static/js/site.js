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
 * ********** Invitations list modal ***************/
$("#invitations-modal-btn").click(showNewInvitations());

function showNewInvitations() {
    $( "#invitations-modal-div" ).empty();

    $.getJSON(
        "/get/new_invitations", function (data) {
            if (data.length != 0) {
                $.each(data, function (index, groupInvitation) {
                    var groupTitle = groupInvitation.group.title;
                    var invitationId = groupInvitation.id;

                    $( "#invitations-modal-div" ).append( "<div class=\"row\" id=\"invitation-" + invitationId + "\"></div>" );
                    $( "#invitation-" + invitationId ).append( "<div class=\"col-sm-8\"><p>" + groupTitle + "</p></div>" );
                    $( "#invitation-" + invitationId ).append( "<div class=\"col-sm-2\"><button id=\"accept-"  + invitationId + "\" type=\"button\" class=\"btn btn-success\" aria-label=\"Left Align\"><i class=\"fas fa-check\"></i></button></div>" );
                    $( "#invitation-" + invitationId ).append( "<div class=\"col-sm-2\"><button id=\"decline-" + invitationId + "\" type=\"button\" class=\"btn btn-danger\"  aria-label=\"Left Align\"><i class=\"fas fa-times\"></i></button></div>" );
                });
            } else {
                $( "#invitations-modal-div" ).append("<h4>Pakvietimų nera</h4>");
            }

            $("#invitations-modal-btn-hidden").click();
        }
    );
}

$('#invitations-modal-div').on('click','button',function(event){
    if ($(event.target).is("i")){
        handleInvitationClicks(event.target.parentElement.id);
    } else {
        handleInvitationClicks(event.target.id);
    }
})

function handleInvitationClicks(id) {
    var invitationId = id.substring(id.indexOf('-') + 1);
    alert(invitationId);
    if (id.substring(0, id.indexOf("-")) == "accept") {
        acceptInvitation(invitationId);
    } else {
        declineInvitation(invitationId);
    }
}

function acceptInvitation(invitationId) {
    $.ajax({
        type: "POST",
        url: "accept_invitation",
        data: {id: invitationId},
        success: function() {
            $("#invitation-" + invitationId).fadeOut(300, function() { $(this).remove(); });
            refreshGroups();
        },
        error: function() {
            // do something in case of an error
        }
    });
}

function declineInvitation(invitationId) {
    $.ajax({
        type: "POST",
        url: "decline_invitation",
        data: {id: invitationId},
        success: function() {
            $("#invitation-" + invitationId).fadeOut(300, function() { $(this).remove(); });
        },
        error: function() {
            // do something in case of an error
        }
    });
}

function refreshGroups() {
    $("#groups-list").empty();
    $.getJSON(
        "/get/groups", function (data) {
            var items = [];
            $.each(data, function (index, group) {
                var id = group.id;
                var title = group.title;

                items.push("<li><a href=\group?id=" + id + ">" + title + "</a></li>");
            });

            if (items.length > 0) {
                $("#groups-list").append(items.join(""));
            }
        }
    );
}

