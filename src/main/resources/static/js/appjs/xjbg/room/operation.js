//var productHtml = "<tr><th ><select id=\"selectProduct\" name=\"orderType\"  style=\"width: 68%\" onchange=\"add_product($(this));\" class=\"form-control chosen-select\" tabindex=\"2\" required><option value=\"\">--选择商品--</option><option th:each=\"product : ${products}\" th:value=\"${product.id}\" th:text=\"${product.productName}\"></option></select></th><th ><img src='' alt='[找不到图片]' width=\"80px\" ></th><th ><div style=\"width: 100% ;height: 100% \"><a class=\"btn \" href=\"#\" mce_href=\"#\" onclick=\"subtract($(this))\"><i class=\"fa fa-minus\"></i></a><input type=\"text\" style=\"width: 70px \" value=\"0\" ><a class=\"btn   \" href=\"#\" mce_href=\"#\"  onclick=\"add($(this))\"><i class=\"fa fa-plus\"></i></a></div></th></tr>"
var tr = $("tr")[1];;
$(function () {
    $("#addProduct").on("click", addProduct);
});
function addProduct() {
    // newTr.attr("hidden",false);
    var tbody = $("tbody")[0];
    debugger;
    $(tbody).append(tr);
}
function add(self) {
    var num = $(self.prev());
    var result = parseInt(num.val()) + 1;
    num.val(result);
}
function subtract(self) {
    var num = $(self.next());
    var v = num.val();
    if (v <= 0) return;
    var result = parseInt(num.val()) - 1
    num.val(result);
}
var add_product = function (self) {
    var value = self.val();
    var src = "";
    for (var i = 0; i < products.length; i++) {
        if (value == products[i].id) {
            src = products[i].productPic;
        }
    }
    //获取父节点
    var th = self.parents("th")[0]
    //获取兄弟节点的子节点
    var img = $(th).next().children()[0];
    $(img).attr("src", src);
}

