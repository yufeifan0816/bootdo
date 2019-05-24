$(function () {
    var rowBeging = '<div class="row">';
    var rowEnd = '</div>';
    var cloBeging = '<div class="col-sm-3 room">';
    var cloEnd = '</div>';
    var btnBeging = '<button type="button" class="btn btn-success col-sm-3 roomClass">';
    var btnEnd = '</button>';

    for (var i = 0; i <rooms.length ; i++) {
        var room = rooms[i];
        var roomTypeName = '';
        for (var j = 0; j <roomTypes.length ; j++) {
            if(room.roomType==roomTypes.value)
                roomTypeName = roomTypes.name
        }
        var html = cloBeging+btnBeging+'<h2>'+room.roomNo+'</h2>'+btnEnd+cloEnd;

    }

});