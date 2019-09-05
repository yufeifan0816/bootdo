var prefix = "/xjbg/order"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/list", // 服务器数据的加载地址
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        checkbox: false
                    },
                    {
                        field: 'roomNo',
                        title: '房间号'
                    },
                    {
                        field: 'roomType',
                        title: '房间类型',
                        formatter:function (value,row,index) {
                            return DictCommon.showDictText("room_type",value);
                        }
                    },
                    {
                        field: 'orderType',
                        title: '入住类型',
                        formatter:function (value,row,index) {
                            return DictCommon.showDictText("order_type",value);
                        }
                    },
                    {
                        field: 'roomPrice',
                        title: '房间价格',
                        formatter:function (value,row,index) {
                            return value+"元";
                        }
                    },
                    {
                        field: 'realityPrice',
                        title: '实际房费',
                        formatter:function (value,row,index) {
                            return value+"元";
                        }
                    },
                    {
                        field: 'otherConsumption',
                        title: '其他消费',
                        formatter:function (value,row,index) {
                            return value+"元";
                        }
                    },
                    {
                        field: 'checkInDays',
                        title: '入住天数',
                        formatter:function (value,row,index) {
                            return value==null?"1天":value+"天";
                        }
                    },
                    {
                        field: 'totalFee',
                        title: '总消费',
                        formatter:function (value,row,index) {
                            return value+"元";
                        }
                    },
                    {
                        field: 'debt',
                        title: '是否欠款',
                        formatter:function (value,row,index) {
                            if(value==undefined||value==null){
                                return "<label class='label label-primary'>未欠费</label>";
                            }else{
                                if(value){
                                    return "<label class='label label-primary'>未欠费</label>";
                                }else{
                                    return "<label class='label label-danger'>欠费</label>";
                                }
                            }

                        }
                    },
                    {
                        field: 'isCheckOut',
                        title: '是否退房',
                        formatter:function (value,row,index) {
                            if(row.checkOutTime == undefined||row.checkOutTime==null){
                                return "<label class='label label-danger'>未退</label>";
                            }else{
                                return "<label class='label label-primary'>已退</label>";
                            }
                        }
                    },
                    {
                        field: 'checkInTime',
                        title: '入住时间'
                    },
                    {
                        field: 'checkOutTime',
                        title: '退房时间'
                    },
                    {
                        field: 'inOption',
                        title: '开房操作人'
                    },
                    {
                        field: 'outOption',
                        title: '退房操作人',
                        formatter:function(value,row,index){
                            return DictCommon.showDictText("option_user",value);
                        }
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var f = '<a class="btn btn-success btn-sm" href="#" title="消费账单"  mce_href="#" onclick="detail(\''
                                + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return f;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function detail() {
    layer.alert(DictCommon.showDictText("del_flag",0));
}

