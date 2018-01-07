(function($) {
	/**
     * 获取制定表单的所有input元素，拼装成AJAX请求的参数
     * 
     * @param p
     *            页面表单的对象
     * 
     * @return data
     * 
     * @author huxiang
     */
    $.formParams = function(p) {
        if (p == undefined) {
            return;
        }

        var data = new Array;

        $(p).find(":input").each(function(i, n) {
            var flag = "false";
            if ($(n).attr("param") == undefined) {
                flag = "true";
            } else {
                flag = $(n).attr("param");
            }
            if (flag == true || flag == "true") {
                if (($(n).attr('type') == "checkbox" || $(n).attr('type') == "radio") && $(n).attr('checked') == true) {
                    data.push({
                                name : $(n).attr("name"),
                                value : $(n).val()
                            });
                } else if ($(n).attr('type') == "text" || $(n).attr('type') == "password"
                        || $(n).attr('type') == "hidden" || $(n).attr('type') == "textarea") {
                    data.push({
                                name : $(n).attr("name"),
                                value : $(n).val()
                            });
                }
            }
        });
        $(p).find("select").each(function(i, n) {

                    var flag = "false";
                    if ($(n).attr("param") == undefined) {
                        flag = "true";
                    } else {
                        flag = $(n).attr("param");
                    }
                    if (flag == true || flag == "true") {
                        if ($.trim($(n).val()) != '') {
                            data.push({
                                        name : $(n).attr("name"),
                                        value : $(n).val()
                                    });
                        } else {
                            data.push({
                                        name : $(n).attr("name"),
                                        value : ''
                                    });
                        }

                    }

                });
        $(p).find("textarea").each(function(i, n) {

            var flag = "false";
            if ($(n).attr("param") == undefined) {
                flag = "true";
            } else {
                flag = $(n).attr("param");
            }
            if (flag == true || flag == "true") {
                if ($.trim($(n).val()) != '') {
                    data.push({
                                name : $(n).attr("name"),
                                value : $(n).attr("value")
                            });
                } else {
                    data.push({
                                name : $(n).attr("name"),
                                value : ''
                            });
                }

            }

        });

        //return data;

        var dataTemp = {};
        for(var i=0; i<data.length; i++){
        	if(!data[i].name) continue;
            dataTemp[data[i].name] = data[i].value;
        }
        return dataTemp;
    };
})(jQuery);