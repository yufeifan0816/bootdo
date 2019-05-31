$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function deletPic(){
    $(".picDiv").remove();
    $("#fileContned").find("label").css('display','inline');
}
$(function () {
    //声明变量
    var $button = $('#submit'),
        //选择文件按钮
        $file = $("#picPath"),
        //回显的列表
        $fileContned = $("#fileContned"),
        //选择要上传的所有文件
        fileList = [];
    var curFile;
    $file.on('change', function (e) {
        //原生的文件对象，相当于$file.get(0).files;//files[0]为第一张图片的信息;
        curFile = this.files;
        reviewFile(curFile[0])
    })
    function reviewFile(file) {
        //实例化fileReader,
        var fd = new FileReader();
        //获取当前选择文件的类型
        var fileType = file.type;
        //调它的readAsDataURL并把原生File对象传给它，
        fd.readAsDataURL(file);//base64
        //监听它的onload事件，load完读取的结果就在它的result属性里了
        fd.onload = function () {
            if (/^image\/[jpeg|png|jpg|gif]/.test(fileType)) {
                $fileContned.find("label").css('display','none');
                $fileContned.append('<div class="picDiv" style="width: 100px height:100px"><img src="' + this.result + '" alt="" height="70"><br/><a  class="file-del" style=" margin-left: 40px" href="#" onclick="deletPic()">删除</a></div>');
            } else {
                $list.append('<li class="file-item"><span class="file-name">' + file.name + '</span><span class="file-del">删除</span></li>')
            }
        }
    }
});
function update() {
    var formData = new FormData();
    var picObject = $("#picPath")[0].files[0];

    var dataArray = $("#signupForm").serializeArray();
    $.each(dataArray, function () {
        formData.append(this.name, this.value);''
    });
    if(picObject){
        formData.append('pic', picObject);
    }
	$.ajax({
		cache : true,
		type : "POST",
		url : "/xjbg/product/update",
		data : formData,// 你的formid
		async : false,
        processData: false,
        contentType: false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入名字"
			}
		}
	})
}
