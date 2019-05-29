$().ready(function () {
    validateRule();
});


function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            name: {
                required: true
            }
        },
        messages: {
            name: {
                required: icon + "请输入姓名"
            }
        }
    })
}

$(function () {
    ////////////////////////////////////////////////图片上传//////////////////////////////////////////////
    //声明变量
    var $button = $('#submit'),
        //选择文件按钮
        $file = $("#productPic"),
        //回显的列表
        $list = $('.file-list'),
        //选择要上传的所有文件
        fileList = [];
    //当前选择上传的文件
    var curFile;
    // 选择按钮change事件，实例化fileReader,调它的readAsDataURL并把原生File对象传给它，
    // 监听它的onload事件，load完读取的结果就在它的result属性里了。它是一个base64格式的，可直接赋值给一个img的src.
    $file.on('change', function (e) {
        //上传过图片后再次上传时限值数量
        var numold = $('li').length;
        if (numold >= 6) {
            layer.alert('最多上传6张图片');
            return;
        }
        //限制单次批量上传的数量
        var num = e.target.files.length;
        var numall = numold + num;
        if (num > 6) {
            layer.alert('最多上传6张图片');
            return;
        } else if (numall > 6) {
            layer.alert('最多上传6张图片');
            return;
        }
        //原生的文件对象，相当于$file.get(0).files;//files[0]为第一张图片的信息;
        curFile = this.files;
        //curFile = $file.get(0).files;
        //console.log(curFile);
        //将FileList对象变成数组
        fileList = fileList.concat(Array.from(curFile));
        //console.log(fileList);
        for (var i = 0, len = curFile.length; i < len; i++) {
            reviewFile(curFile[i])
        }
        $('.file-list').fadeIn(2500);
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
                $list.append('<li style="border:solid red px; margin:5px 5px;" class="file-item"><img src="' + this.result + '" alt="" height="70"><span class="file-del">删除</span></li>').children(':last').hide().fadeIn(2500);
            } else {
                $list.append('<li class="file-item"><span class="file-name">' + file.name + '</span><span class="file-del">删除</span></li>')
            }

        }
    }

    //点击删除按钮事件：
    $(".file-list").on('click', '.file-del', function () {
        var parent = $(this).parent();
        console.log(parent);
        var index = parent.index();
        fileList.splice(index, 1);
        parent.fadeOut(850, function () {
            $parent.remove()
        });
        //$parent.remove()
    });
    //点击上传按钮事件：
    $button.on('click', function () {
        var formData = new FormData();
        var picObject = fileList[0];

        var dataArray = $("#signupForm").serializeArray();
        $.each(dataArray, function () {
            formData.append(this.name, this.value);
        });
        formData.append('pic', picObject);
        /*var request = new XMLHttpRequest();
        request.open("POST", "/xjbg/product/save");
        request.onreadyStateChange = function () {
            alert(request.status);
              if (request.status === 304 || (request.status >= 200 && request.status < 300)) {
                  var result = request.responseText;
                  debugger;
                  if (data.code == 0) {
                      parent.layer.msg("操作成功");
                      parent.reLoad();
                      var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                      parent.layer.close(index);

                  } else {
                      parent.layer.alert(data.msg)
                  }
              } else {
                  console.log('type: error, errCode:', xhr.status)
              }

        }
        request.send(formData);
*/


        $.ajax({
            cache: true,
            type: "post",
            url: "/xjbg/product/save",
            data: formData,
            async: false,
            processData: false,
            contentType: false,
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
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
    )
})