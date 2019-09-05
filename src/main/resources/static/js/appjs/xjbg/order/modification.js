var tr = $("tr")[1];
var tbody = $("tbody");
var orderId = $("#id").val();
$(function () {
    $("#addProduct").on("click", addProduct);
    $("#checkOut").on("click", checkOut);
    /**初始化删除空白商品明细**/
    $("#pTable").empty();
    $("#paidUp").blur(function () {
        calBalance();
    });
    //初始化工单商品
    var orderItems = order.orderItems;
    for (var i = 0; i < orderItems.length; i++) {
        tbody.prepend('<tr>' + $(tr).html() + '</tr>');
        var newTr = $(tbody.children()[0]);
        var src = "";
        var item = orderItems[i];
        for (var j = 0; j < products.length; j++) {
            if (item.productId == products[j].id) {
                src = products[j].productPic;
            }
        }
        newTr.find(".orderItemId").val(item.id);
        newTr.find(".productId").val(item.productId);
        newTr.find(".number").val(item.productAccount);
        newTr.find(".productPic").attr("src", src)
    }
    calBalance()
});

/**选择入住类型,动态改变房间价格*/
function changePrice(self) {
    var orderType = self.val()
    $.post("/xjbg/roomPrice/getPrice", {"roomId": room.id, "orderType": orderType}, function (result) {
        $("#price").val(result);
    })
    calBalance()

}

//添加一条商品
function addProduct() {
    var target = true;
    $(".productId").each(function (i, val) {
        if (!val.value || val.value == "") {
            layer.alert("请选择商品!")
            target = false;
        }
    });
    if (!target) return;
    var tbody = $("tbody")[0];
    $(tbody).append('<tr>' + $(tr).html() + '</tr>');
    calBalance()
}

//删除一条商品
function delProduct(self) {
    layer.confirm("确认删除这件商品", function (index) {
        var tr = self.parents("tr");
        var orderItemId = tr.find(".orderItemId").val();
        if (orderItemId) {
            //后台删除
            $.post("/xjbg/orderItem/remove", {"id": orderItemId}, function (result) {
                if (result.code == 0) {
                    tr.remove();
                    layer.close(index);
                } else {
                    layer.close(index);
                    layer.alert("删除失败:" + result.msg)
                }
            });
            calBalance();
        } else {
            tr.remove();
            layer.close(index);
            calBalance();
        }
    });
    calBalance();

}

//数量加1
function add(self) {
    var num = $(self.prev());
    var result = parseInt(num.val()) + 1;
    num.val(result);
    calBalance()
}

//数量-1
function subtract(self) {
    var num = $(self.next());
    var v = num.val();
    if (v <= 0) return;
    var result = parseInt(num.val()) - 1
    num.val(result);
    calBalance()
}

//选择商品
var add_product = function (self) {
    var value = self.val();
    var src = "";
    for (var i = 0; i < products.length; i++) {
        if (value == products[i].id) {
            src = products[i].productPic;
        }
    }
    //获取父节点
    var th = $(self.parents("th")[0])
    //获取兄弟节点的子节点
    var img = th.next().children()[0];
    $(img).attr("src", src);
    calBalance()
}

function upload() {
    var orderItems = new Array();
    /**封装工单信息**/
    var order = {};
    order.id = $("#id").val();
    order.roomId = room.id;
    order.orderType = $("#orderType").val();
    order.price = $("#price").val();
    order.paidUp = $("#paidUp").val();
    ;
    /**封装工单明细*/
    var ths = $("#pTable").children();
    ths.each(function (i, val) {
        var orderItem = {};
        var productId = $(val).find(".productId").val();
        var productAccount = $(val).find(".number").val();
        var orderItemId = $(val).find(".orderItemId").val();
        if (!productId || productId == "") return true;
        //明细
        orderItem.id = orderItemId;
        //商品id
        orderItem.productId = productId;
        //商品数量
        orderItem.productAccount = productAccount;
        orderItems.push(orderItem);
    });
    order.orderItems = orderItems;
    $.ajax({
        contentType: 'application/json;charset=utf-8', //设置请求头信息
        cache: true,
        dataType: "json",
        type: "POST",
        url: "/xjbg/order/updateOrder",
        data: JSON.stringify(order),
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.location.reload();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }
        }
    });
}

/**计算余额并更新到界面*/
function calBalance() {
    var price = calPrice();
    var balance = parseInt($("#paidUp").val()) - price;
    if (balance && balance < 0) {
        $("#countPrice").css("color", "red");
        $("#balance").css("color", "red");
    }else {
        $("#countPrice").css("color", "green");
        $("#balance").css("color", "green");
    }
    $("#countPrice").text(price + '元');
    $("#balance").text(balance + '元');
}

/**计算消费总价格总价格*/
function calPrice() {
    var account = 0;
    var roomPrice = $("#price").val();
    var trs = tbody.children()
    trs.each(function (i, val) {
        var number = $(val).find(".number").val()//商品数量
        var productId = $(val).find(".productId").val();//商品id
        var price = getPriceById(productId);
        account += (price * number);
    });
    account += parseInt(roomPrice);
    return account;
}

/**根据商品id获取价格**/
function getPriceById(productId) {
    for (var j = 0; j < products.length; j++) {
        if (parseInt(productId) == products[j].id) {
            return products[j].sellingPrice;
        }
    }
    return 0;
}
/**退房结算**/
function checkOut(){
    var balance = $("#balance").text().toString();
    var msg = "<h3 style=''></h3>";
    // 判断余额是否大于0
    if(balance.indexOf('-')!=-1){
        //需要付款
        msg = "<h3 style='color: red; margin-left: 90px;margin-top: 20px'>需收款"+balance.replace('-','')+",请确认退房!</h3>";
    }else{
        //需要找零
        msg = "<h3 style='color: green; margin-left: 90px;margin-top: 20px'>需找零"+balance+",请确认退房!</h3>";
    }
    msg+="</select>";
    $("#checkOutForm").append($(msg));
    $("#myModal").modal('show');
}
/**确认退房*/
function checkOutSubmit(){
    var isDebt  = $("#isDebt").val();
    var orderId  = $("#orderId").val();
    var checkOutUser  = $("#checkOutUser").val();
    $.post("/xjbg/order/checkOut",{"isDebt":isDebt,"orderId":orderId,"checkOutUser":checkOutUser},function (result) {
        if(result.code!=0){
            layer.msg(result.msg);
        }else{
            $("#myModal").modal('hide');
            var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
            parent.layer.close(index);
            // layer.alert("退房成功!");
            parent.location.reload();
            setTimeout(function(){layer.msg("退房成功!");},1000);
        }
    });
}
/**续住*/
function renew(){
    $.post("/xjbg/order/renew",{"orderId":orderId},function (result) {
        console.log(result);
    });
}

