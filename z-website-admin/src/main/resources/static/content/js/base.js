$.getQuery = function (name) {
    var search = location.search.slice(1);
    var arr = search.split("&");
    for (var i = 0; i < arr.length; i++) {
        var ar = arr[i].split("=");
        if (ar[0] == name) {
            if (unescape(ar[1]) == 'undefined') {
                return "";
            } else {
                return unescape(ar[1]);
            }
        }
    }
    return "";
}
$.jump = function (url) {
    window.localStorage.setItem('cur_url', url);
    $("#content").load(url);
}
$.file_upload = function (ops) {
    var defaults = {
        file: "#file",
        img: "#upload_img",
        size: 50,
        upload: "#btnUpload",
        form: "#uploadFile",
        success: null
    };
    var options = $.extend(defaults, ops);
    $(options.file).off().on("change", function () {
        var _this = $(this)[0],
            _file = _this.files[0],
            fileType = _file.type;
        var max = options.size * 1024;
        if (_file.size > max) {
            toastr.error("上传图片", "选择的图片不能超过" + options.size + "KB大小");
            return false;
        }
        var fileReader = new FileReader();
        fileReader.readAsDataURL(_file);
        fileReader.onload = function (event) {
            var result = this.result;//返回的dataURL
            $(options.img)[0].src = result;
        }
    });
    $(options.upload).off().on("click", function () {
        $(options.form).ajaxSubmit(function (res) {
            if (options.success) {
                options.success(res);
            }
        });
    });
}
$.fn.file_upload = function (ops) {
    var el = $(this);
    var defaults = {
        file: "#file",
        img: "#upload_img",
        size: 50,
        upload: "#btnUpload",
        form: "#uploadFile",
        success: null
    };
    var options = $.extend(defaults, ops);
    $(options.file).off().on("change", function () {
        var _this = $(this)[0],
            _file = _this.files[0],
            fileType = _file.type;
        var max = options.size * 1024;
        if (_file.size > max) {
            toastr.error("上传图片", "选择的图片不能超过" + options.size + "KB大小");
            return false;
        }
        var fileReader = new FileReader();
        fileReader.readAsDataURL(_file);
        fileReader.onload = function (event) {
            var result = this.result;//返回的dataURL
            el[0].src = result;
        }
    });
    $(options.upload).off().on("click", function () {
        $(options.form).ajaxSubmit(function (res) {
            if (options.success) {
                options.success(res);
            }
        });
    });
}
$.request = function (url, data, success, error) {
    var parm = JSON.stringify(data);
    $.ajax({
        url: url,
        data: parm,
        type: "POST",
        contentType: "application/json",
        success: function (res) {
            if (success) {
                success(res);
            }
        },
        error: function (res) {
            if (error) {
                error();
            }
        }
    })
}
$.requestForm = function (url, data, success, error) {
    var parm = JSON.stringify(data);
    $.ajax({
        url: url,
        data: parm,
        type: "POST",
        contentType: "application/x-www-form-urlencoded",
        success: function (res) {
            if (success) {
                success(res);
            }
        },
        error: function (res) {
            if (error) {
                error();
            }
        }
    })
}

$.fn.formToObj = function () {
    var element = $(this);
    var postdata = {};
    var checkBox = [];
    element.find('input,select,textarea').each(function (r) {
        var $this = $(this);
        var id = $this.attr('id');
        var name = $this.attr("name");
        var type = $this.attr('type');
        switch (type) {
            case "checkbox":
                if ($this.is(":checked")) {
                    checkBox.push({k: name, v: $this.val()});
                }
                postdata[name] = $this.val();
                break;
            case "radio":
                if ($this.is(":checked")) {
                    postdata[name] = $this.val();
                }
                break;
            default:
                var value = $this.val() == "" ? "&nbsp;" : $this.val();
                if (!$.getQuery("keyValue")) {
                    value = value.replace(/&nbsp;/g, '');
                }
                postdata[name] = value;
                break;
        }
    });
    if (checkBox.length == 1) {
        postdata[checkBox[0].k] = checkBox[0].v;
    }
    if (checkBox.length > 1) {
        var arrks = [];
        $(checkBox).each(function () {
            arrks.push(this.v);
        });
        postdata['ids'] = arrks.join(',');
    }
    return postdata;
};
$.fn.ObjToForm = function (data) {
    var element = $(this);
    var checkBox = [];
    element.find('input,select,textarea').each(function (r) {
        var $this = $(this);
        var id = $this.attr('id');
        var name = $this.attr("name");
        var type = $this.attr('type');
        switch (type) {
            case "checkbox":
                $(this).val(data[id]);
                break;
            case "radio":
                if($(this).val()==data[name])
                {
                    $(this).attr("checked",'checked');
                }
                break;
            default:
                $(this).val(data[id]);
                break;
        }
    });
};

$.fn.ObjToHtml = function (data, el) {
    var element = $(this);
    var checkBox = [];
    element.find(el).each(function (r) {
        var $this = $(this);
        var id = $this.attr('id');
        var name = $this.attr("name");
        var type = $this.attr('type');
        $(this).html(data[name]);
    });
};