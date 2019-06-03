$(function () {
	$("#addProduct").on("click",addProduct);

});
function addProduct(){

}
var add_product =  function(self) {
    var value = self.val();
    var src = "";
    for (var i=0;i<products.length;i++){
        if(value==products[i].id){
            src =products[i].productPic;
        }
    }
    debugger;
    console.log(products);
	//获取父节点
	var th = self.parents("th")[0]
	//获取兄弟节点的子节点
  	var img = $(th).next().children()[0];
    $(img).attr("src",src);
}
