/**
 * 字典表js前端缓存
 * @type {{dictCache: {}, showDictText: Common.showDictText, getDicts: (function(*=): *)}}
 */
var DictCommon = {
    dictCache: {},
    showDictText: function (typeCode, value) {
        if (value === "" || value === undefined) {
            return "";
        }
        var dicts = DictCommon.getDicts(typeCode);
        var rtnStr = "";
        for (var i = 0; i < dicts.length; i++) {
            if (dicts[i].value == value) {
                rtnStr = dicts[i].name;
            }
        }
        return rtnStr;
    },
    getOptions:function(typeCode){
        var str = "<select value=''>--请选择--</select>"
        var dicts = DictCommon.getDicts(typeCode);
        for (var i = 0; i < dicts.length; i++) {
            var  dict =  dicts[i];
            str+="<option value="+dict.value+">"+dict.name+"</option>"
        }
        return str;
    },
    getDicts: function (typeCode) {
        if (!sessionStorage.getItem(typeCode)) {
            if (!DictCommon.dictCache[typeCode]
                || !DictCommon.dictCache[typeCode]) {
                //如果不存在，获取缓存
                $.ajax({
                    type: "GET",
                    url: "/common/dict/list/"+typeCode+"",
                    async: false,
                    dataType: "json",
                    success: function (datas) {
                        DictCommon.dictCache[typeCode] = datas;
                        // console.log(datas);
                        sessionStorage.setItem(typeCode, JSON.stringify(DictCommon.dictCache[typeCode]));
                    }
                });
            }
        }
        return sessionStorage.getItem(typeCode) ?
            JSON.parse(sessionStorage.getItem(typeCode)) : DictCommon.dictCache[typeCode];
    }

};