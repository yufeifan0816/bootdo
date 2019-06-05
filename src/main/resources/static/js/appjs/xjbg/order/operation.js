var tr = $("tr")[1];;
$(function () {
    $("#addProduct").on("click", addProduct);
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
}
//删除一条商品
function delProduct(self) {
    var tr = self.parents("tr");
    tr.remove();
    console.log(tr);
}
//数量加1
function add(self) {
    var num = $(self.prev());
    var result = parseInt(num.val()) + 1;
    num.val(result);
}
//数量-1
function subtract(self) {
    var num = $(self.next());
    var v = num.val();
    if (v <= 0) return;
    var result = parseInt(num.val()) - 1
    num.val(result);
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
}

function save(){
    var orderItems = new Array();
    /**封装工单信息**/
    var order = {};
    order.roomId = room.id;
    order.orderType = $("#orderType").val();
    order.price = $("#price").val();
    order.paidUp = 1;
    /**封装工单明细*/
    var ths = $("#pTable").children();
    ths.each(function(i,val){
        var orderItem = {};
        var a = $(val).find(".productId");
        //商品id
        orderItem['productId'] = $(val).find(".productId").val();
        //商品数量
        orderItem['productAccount'] = $(val).find(".number").val();
        orderItems.push(orderItem);
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