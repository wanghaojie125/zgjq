(function () {

    var dpy = {}, loading = false,
    host = 'http://www.zhangongjx.com',
    Regs = {
        user: /^[A-Za-z0-9_-]{4,12}$/,
        phone: /^1[3-8]\d{9}$/,
        password: /^[A-Za-z0-9_-]{6,8}$/,
        email: /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
    };
    
    var Ajax = {
        Post: function (path, params, callback) {
            $.ajax({
                type: "post",
                url: host + path,
                // async: false, // 使用同步方式
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true, // 允许跨域带上cookies
                // 1 需要使用JSON.stringify 否则格式为 a=2&b=3&now=14...
                // 2 需要强制类型转换，否则格式为 {"a":"2","b":"3"}
                data: JSON.stringify(params),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data) {
                    console.info('ajax ', path, ':', data)
                    callback(data)
                } 
            });
        },
        LoginPost: function (path, params, callback) {
            $.ajax({
                type: "post",
                url: host + path, 
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true, // 允许跨域带上cookies 
                data: params, 
                success: callback
            });
        },
        Upload: function (path, files, callback) {
            var formData=new FormData(); 
            for(var key in files)
                formData.append(key, files[key]);  /*获取上传的图片对象*/ 
            $.ajax({
                url: host + path,
                type: 'POST',
                data: formData,
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true, // 允许跨域带上cookies 
                contentType: false,
                processData: false,
                success: callback
            })
        }
    } 
    

    function showError (msg) {
        let errobj = document.querySelectorAll('.login_msg')
        errobj = errobj[0].clientWidth == 0 ? errobj[1] : errobj[0]
        errobj.innerHTML = '<i style="font-size: 14px; color: red;">' + msg + '</i>'
        setTimeout(() => errobj.innerHTML = '登录账户', msg.length * 250)
    }


    dpy.Login = {
        Login: function () {
            if (loading) return 
            else loading = true

            let u = document.querySelector('#login_u')
            let p = document.querySelector('#login_p')

            let reg_u = Regs.user.test(u.value),
                reg_p = Regs.password.test(p.value);
            if (reg_u && reg_p) { 
                Ajax.LoginPost('/auth/login', { username: u.value, password: p.value}, function (data) {
                    loading = false 
                    if (data.code == 0) {
                        alert('登录成功')
                        setCookie('userid', data.data.user_id) 
                        $('.close').click()
                        dpy.Login.getUserinfo()
                    } else {
                        showError(data.msg)
                    }
                })
            } else {
                loading = false
                showError('用户名或密码不正确')
            }
        },
        Register: function () {
            if (loading) return 
            else loading = true

            let params = {
                username: document.querySelector('#reg_u').value,
                pwd: document.querySelector('#reg_p').value,
                confirmpwd: document.querySelector('#reg_rp').value,
                email: document.querySelector('#reg_email').value,
                phone: document.querySelector('#reg_phone').value,
                authenticode: document.querySelector('#reg_code').value
            } 
            console.info(params.phone, Regs.phone.test(params.phone))
            if (!Regs.user.test(params.username)) showError('用户名只接受4-12位字母或数字') 
            else if (!Regs.password.test(params.pwd)) showError('密码只接受6-8位字母或数字') 
            else if (params.pwd != params.confirmpwd) showError('两次密码输入不一致') 
            else if (!Regs.email.test(params.email)) showError('邮箱地址不正确') 
            else if (!Regs.phone.test(params.phone)) showError('手机号格式不正确') 
            else { 
                Ajax.Post('/api/save_user', params, function (data) {
                    loading = false 
                    if (data.code == 0) {
                        alert('注册成功，请登录')
                        $('.toggle').click();
                    } else {
                        console.error(data)
                        showError(data.msg)
                    }
                })
            }

            loading = false
        },
        getPhoneCode () {
            let btn = document.querySelector('.getIdenCode'),
                phone = document.querySelector('#reg_phone').value
                if (!Regs.phone.test(phone)) { showError('手机号格式不正确') } 

            if (btn.className.indexOf('disable') < 0) {
                btn.classList.add('disable')
                $.get(host + '/api/getRegisterCode', { phoneNo: phone }, function (data) { 
                    console.info('phoneNo', data) 
                    if (data.code == 0) { 
                        counting ()
                    } else {
                        showError(data.msg)
                        btn.classList.remove('disable'); 
                    }
                })
            }

            function counting () {
                window.count = 60
                window.si = setInterval(function () {
                    if (window.count >= 1) { 
                        window.count--; 
                        btn.innerHTML = '重新发送（' + window.count + '）' 
                    } else { 
                        btn.classList.remove('disable'); 
                        btn.innerHTML = '获取验证码'; 
                        clearInterval(window.si) 
                    }
                }, 1000)
            }

        },
        getUserinfo () {
            let uid = getCookie('userid');
            if (uid) $('#topBtnLogin').hide().next().show()
			else $('#topBtnLogin').show().next().hide()
            return uid
        }
    }
    
    dpy.Book = {
        upload: function () { 
            if ($('#uploadmsg').html() == '上传中...') return
            else $('#uploadmsg').html('上传中...')

            var file = $("#uploadFileLab")[0].files[0] 
            if (file.name.match(/(\.zip|\.rar)$/) == null) { alert('只支持上传压缩包'); return; }
            if (file.size > 5000000) { alert('图片不能大于5M'); return; }
            console.info(file)
            Ajax.Upload('/uploadPicFile', { file: file }, function (data) {
                console.log(data); 
                if (data.success) {
                    $('#uploadmsg').html(file.name + ' 上传成功')
                    $('#uploadmsg').attr('data-url', data.data.url) 
                } else {
                    $('#uploadmsg').html('点击上传附件')
                    console.error(data)
                    alert('上传失败')
                }
            }) 
        },
        yixing: function () {
            if (loading) return 
            else loading = true

            let params = {
                "url": $('#uploadmsg').attr('data-url'),
                "picScale": $("[name=bili]:checked").val(),
                "picAngular": $("[name=shijiao]:checked").val(),
                "picIsMeasure": $("[name=celiang]:checked").val(),
                "remark": $("#remark").val()
            } 
            if (!params.url) { alert('请上传附件') }
            Ajax.Post('/save_pics', params, function (data) {
                loading = false 
                console.error(data)
                // if (data.code == 0) {
                //     alert('注册成功，请登录')
                //     $('.toggle').click();
                // } else {
                //     console.error(data)
                //     showError(data.msg)
                // }
            })
        }
    }





    dpy.User = {
        Update: function () {
            $("#ebx").validationEngine() 
        }
    }

    window.dpy = dpy

    // 操作 cookie
    function setCookie(name,value) {
        var Days = 30;
        var exp = new Date();
        // exp.setTime(exp.getTime() + Days*24*60*60*1000); 
        document.cookie = name + "="+ escape (value) + ';path=/' // + ";expires=" + exp.toGMTString();
    } 
    function getCookie(name){
        var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
        if(arr != null) return unescape(arr[2]); 
        else return null; 
    }
    function delCookie(name){
        var exp = new Date(); //当前时间
        exp.setTime(exp.getTime() - 1);
        var cval=getCookie(name);
        if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
})()