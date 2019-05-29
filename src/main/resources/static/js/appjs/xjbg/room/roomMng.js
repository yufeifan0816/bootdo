var prefix = "/xjbg/room";
$(function () {
    for (var key in rooms) {
        //第k层
        var rommList = rooms[key];
        var floorHtml = $('<label class="col-sm-12 bg-danger text-center" style="height: 50px font-size: 40px">' + key + '楼</label>')
        var rowCurrent = $('<div class="row"></div>');
        rowCurrent.attr('id', 'floor' + key);
        for (var i = 0; i < rommList.length; i++) {
            var roomTypeName = '';
            var room = rommList[i];
            for (var j = 0; j < roomTypes.length; j++) {
                if (room.roomType == roomTypes[j].value)
                    roomTypeName = roomTypes[j].name
            }
            var roomCurrent = $('<div class="col-sm-3 room"><button type="button" onclick=" btnOnClick('+room.id+')" class="btn  col-sm-3 roomClass"><p class="font1" style="font-size: 50px"></p><h1 class="font2"></h1></button></div>');
            var p = roomCurrent.find('p')[0];
            var h2 = roomCurrent.find('h1')[0];
            var btn = roomCurrent.find('button')[0];
            $(btn).attr('id',room.roomNo );
            p.innerHTML = roomTypeName;
            h2.innerHTML = room.roomNo;

            if (room.roomState == 1) {
                $(btn).css('background-color', '#00B83F')
            } else if (room.roomState == 2) {
                $(btn).css('background-color', '#dc5b31')
            }
            rowCurrent.append(roomCurrent)
        }
        $(".gray-bg").append(floorHtml).append(rowCurrent);
    }


});

function btnOnClick(roomId) {
    layer.open({
        type: 2,
        title: '开房',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['800px', '520px'],
        content: prefix + '/operation/'+roomId
    });
}