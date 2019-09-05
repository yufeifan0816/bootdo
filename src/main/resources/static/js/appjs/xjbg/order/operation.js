var tr = $("tr")[1];

$(function () {
    // $("#addProduct").on("click", addProduct);
    /**初始化删除空白商品明细**/
    $("#pTable").empty();
    /**初始化价格*/
    changePrice();
    /**初始化消费金额*/
    calBalance();
});

//添加一条商品
function addProduct() {
    var target = true;
    $(".productId").each(function(i, val) {
      if(!val.value||val.value==""){
          layer.alert("请选择商品!")
         target = false;
      }
    });
    if(!target)return;
    var tbody = $("tbody")[0];
    $(tbody).append('<tr>'+$(tr).html()+'</tr>');
    calBalance();
}
//删除一条商品
function delProduct(self) {
    var tr = self.parents("tr");
    tr.remove();
    console.log(tr);
    calBalance();
}
//数量加1
function add(self) {
    var num = $(self.prev());
    var result = parseInt(num.val()) + 1;
    num.val(result);
    calBalance();
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
    var prodictId = "";
    for (var i = 0; i < products.length; i++) {
        if (value == products[i].id) {
            src = products[i].productPic;
            prodictId  = products[i].id;
        }
    }
    //获取父节点
    var th = $(self.parents("th")[0])
    //获取兄弟节点的子节点
    var img = th.next().children()[0];
    $(img).attr("src", src);
    th.prev().children().val(prodictId);
    calBalance()
}

function save(){
    var orderItems = new Array();
    /**封装工单信息**/
    var order = {};
    order.roomId = room.id;
    order.orderType = $("#orderType").val();
    order.price = $("#price").val();
    order.paidUp = $("#paidUp").val();
    /**封装工单明细*/
    var ths = $("#pTable").children();
    ths.each(function(i,val){
        debugger;
        var orderItem = {};
        var productId = $(val).find(".productId").val();
        if(productId!=null&&productId!="") {
            //商品id
            orderItem['productId'] = productId;
            //商品数量
            orderItem['productAccount'] = $(val).find(".number").val();
            orderItems.push(orderItem);
        }
    });
    order.orderItems = orderItems;
    $.ajax({
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        cache : true,
        dataType:"json",
        type : "POST",
        url : "/xjbg/order/kaifang",
        data :JSON.stringify(order),
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
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
function calBalance(){
    var price = calPrice();
    var balance = parseInt($("#paidUp").val())-price;
    if(balance&&balance<0){
        $("#countPrice").css("color","red")
        $("#balance").css("color","red")
    }else {
        $("#countPrice").css("color", "green");
        $("#balance").css("color", "green");
    }
    $("#countPrice").text(price+'元');
    $("#balance").text(balance+'元');
}
/**计算消费总价格总价格*/
function calPrice(){
    var account = 0;
    var roomPrice = $("#price").val();
    var trs = $("#pTable").children()
    trs.each(function (i,val) {
        var number =  $(val).find(".number").val()//商品数量
        var productId =  $(val).find(".productId").val();//商品id
        var price =  getPriceById(productId);
        account+=(price*number);
    });
    account+=parseInt(roomPrice);
    return account;
}
function  getPriceById(productId) {
    for (var j = 0; j < products.length; j++) {
        if (parseInt(productId) == products[j].id) {
            return products[j].sellingPrice;
        }
    }
    return 0;
}


/**选择入住类型,动态改变房间价格*/
function changePrice() {
    var orderType =$("#orderType").val();
    $.post("/xjbg/roomPrice/getPrice", {"roomId": room.id, "orderType": orderType}, function (result) {
        $("#price").val(result);
        calBalance()
    })


}