$(function () {

    /**
     * 模板引擎
     *
     * @param {*}
     *            $tmpl 模板内容
     * @param {*}
     *            data 渲染的数据
     * @param {*}
     *            $el 渲染到的元素
     */
    function loadData($tmpl, data, $el) {
        var rendered = Mustache.render($tmpl.html(), {
            listData : data
        });
        $el.html(rendered);
    }

    //将form里面的内容序列化成json数据
    $.fn.serializeJson=function(otherString){
        var serializeObj={},
            array=this.serializeArray();
        $(array).each(function(){
            if(serializeObj[this.name]){
                serializeObj[this.name]+=';'+this.value;
            }else{
                serializeObj[this.name]=this.value;
            }
        });
        if(otherString!=undefined){
            var otherArray = otherString.split(';');
            $(otherArray).each(function(){
                var otherSplitArray = this.split(':');
                serializeObj[otherSplitArray[0]]=otherSplitArray[1];
            });
        }
        return serializeObj;
    };

//将josn对象赋值给form--》即数据回显
    $.fn.setForm = function(jsonValue){
        var obj = this;
        $.each(jsonValue,function(name,ival){
            var $oinput = obj.find("input[name="+name+"]");
            if($oinput.attr("type")=="checkbox"){
                if(ival !== null){
                    var checkboxObj = $("[name="+name+"]");
                    var checkArray = ival.split(";");
                    for(var i=0;i<checkboxObj.length;i++){
                        for(var j=0;j<checkArray.length;j++){
                            if(checkboxObj[i].value == checkArray[j]){
                                checkboxObj[i].click();
                            }
                        }
                    }
                }
            }
            else if($oinput.attr("type")=="radio"){
                $oinput.each(function(){
                    var radioObj = $("[name="+name+"]");
                    for(var i=0;i<radioObj.length;i++){
                        if(radioObj[i].value == ival){
                            radioObj[i].click();
                        }
                    }
                });
            }
            else if($oinput.attr("type")=="textarea"){
                obj.find("[name="+name+"]").html(ival);
            }
            else{
                obj.find("[name="+name+"]").val(ival);
            }
        })
    }

    /**
     * Some examples of how to use features.
     *
     **/

    var userId = "", toId = "";
    var SohoExamle = {
        Message: {
            add: function (message, type) {
                var chat_body = $('.layout .content .chat .chat-body');
                if (chat_body.length > 0) {

                    type = type ? type : '';
                    message = message ? message : 'I did not understand what you said!';

                    $('.layout .content .chat .chat-body .messages').append(`<div class="message-item ` + type + `">
                        <div class="message-avatar">
                            <figure class="avatar">
                                <img src="./dist/media/img/` + (type == 'outgoing-message' ? 'women_avatar5.jpg' : 'man_avatar3.jpg') + `" class="rounded-circle">
                            </figure>
                            <div>
                                <h5>` + (type == 'outgoing-message' ? 'Mirabelle Tow' : 'Byrom Guittet') + `</h5>
                                <div class="time">14:50 PM ` + (type == 'outgoing-message' ? '<i class="ti-check"></i>' : '') + `</div>
                            </div>
                        </div>
                        <div class="message-content">
                            ` + message + `
                        </div>
                    </div>`);

                    setTimeout(function () {
                        chat_body.scrollTop(chat_body.get(0).scrollHeight, -1).niceScroll({
                            cursorcolor: 'rgba(66, 66, 66, 0.20)',
                            cursorwidth: "4px",
                            cursorborder: '0px'
                        }).resize();
                    }, 200);
                }
            }
        }
    };

    /**
     * 获取登录的用户信息,并发送登录包
     */
    var loadUser = function () {
        axios.get('/api/user').then(function(response){
            userId = response.data.userId;
            ws.login_msg();
        }).catch(function(error){
            console.log("获取用户信息失败!");
        })
    }

    var loadChatHeader = function(){

    }

    /**
     * 加载最新消息列表
     */
    var loadRecentChat = function(){
        axios.get('/api/user/recent').then(function(response){
            var $data = response.data;
            userId = $data.userId;
            $("#displayName").text($data.displayName);
            $.each($data.friends,function(index, data){
                if (data.userId == toId) {
                    data.isOpen = true;
                }
            })
            loadData($('#recentChatList-tpl'), $data.friends, $('#recentChatGroup'))
        }).catch(function(error){
        })
    }


    /**
     * 加载历史记录
     */
    var loadMsgHistory = function(){
        axios.get('/api/msg/history',{
            params:{
                'toId': toId
            }
        }).then(function(response){
            //把当前用户添加到返回中
            var $data = response.data;
            //清除未读的记录
            clearNoReadMsg();
            $.each($data,function(index, data){
                if (data.fromId == userId) {
                    data.me = true;
                }
            })
            loadData($("#chatMessage-tpl"), $data, $(".messages"));
        }).catch(function(error){
            console.log(error);
        })
    }

    //清除未读消息数量
    var clearNoReadMsg = function(){
        axios.put('./api/msg/clear',{
            "toId" : toId
        }).then(function (response) {
            loadRecentChat();
        })
    }


    setTimeout(function () {
        // $('#disconnected').modal('show');
        // $('#call').modal('show');
        // $('#videoCall').modal('show');
        $('#pageTour').modal('show');
    }, 1000);

    $(document).on('submit', '.layout .content .chat .chat-footer form', function (e) {
        e.preventDefault();

        var input = $(this).find('input[type=text]');
        var message = input.val();

        message = $.trim(message);

        if (message) {
            SohoExamle.Message.add(message, 'outgoing-message');
            input.val('');

            ws.single_msg(toId, message);
            loadRecentChat();

            // setTimeout(function () {
            //     SohoExamle.Message.add();
            // }, 1000);
        } else {
            input.focus();
        }
    });

    $(document).on('click', '.layout .content .sidebar-group .sidebar .list-group-item', function () {
        if (jQuery.browser.mobile) {
            $(this).closest('.sidebar-group').removeClass('mobile-open');
        }
    });

    $('.list-group').on('click','li', function(){
        toId = $(this).data('id');
        // clearNoReadMsg();
        // loadRecentChat();
        // loadChatHeader();
        loadMsgHistory();
        // $(this).siblings().removeClass("open-chat");
        // $(this).addClass('open-chat');


    });

    $("#saveInfo").click(function(){
        console.log($("#customCheck1"));
        var view = $("#customCheck1").is(':checked');
        var json = $("#infoForm").serializeJson('introduce:' + $("#about-text").val() + ';allowView:' + view);

        axios.post('/api/user/info',json).then(function(response){
            console.log(response);
            // window.location.href='index.html';
        }).catch(function(error){
            console.log(error);
        })
        console.log(json);
    })

    if(!window.WebSocket){
        window.WebSocket = window.MozWebSocket;
    }
    if(window.WebSocket){
        socket = new WebSocket("ws://localhost:8081/ws");
        socket.onmessage = function(event){
            // console.log(event.data);
            var json = JSON.parse(event.data);
            console.log(event);
            //如果发送人是现在打开的页面的，则展示在对话框中
            if (json.fromId == toId) {
                SohoExamle.Message.add(json.content);
                clearNoReadMsg();
            } else {
                loadRecentChat();
            }
        };

        // 连接成功1秒后，将用户信息注册到服务器在线用户表
        socket.onopen = function(event){
            console.log("WebSocket已连接...");
            loadUser();
            loadRecentChat();

        }

        socket.onclose = function(event){
            console.log("WebSocket已关闭...");
        };
    } else {
        alert("您的浏览器不支持WebSocket！");
    }



    var ws = {
        send_msg: function (toId, content, type) {
            if (!window.WebSocket) {
                return;
            }
            console.log(userId);
            if (socket.readyState == WebSocket.OPEN) {
                var data = {
                    "fromId": userId,
                    "toId" : toId,
                    "content" : content,
                    "type": type
                };
                socket.send(JSON.stringify(data));
            } else {
                alert("Websocket连接没有开启！");
            }
        },
        login_msg: function(){
            this.send_msg("", "", 0);
        },
        single_msg: function (toId, content) {
            this.send_msg(toId, content, 1);
        },
        group_msg: function (toId, content) {
            this.send_msg(toId, content, 2);
        },
    }

});