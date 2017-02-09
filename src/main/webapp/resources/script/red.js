//存放主要交互逻辑代码
//模块化
var red = {
    url : {
        now : function () {
            return '/demo/time/now';
        },
        exposer : function (rId) {
            return '/demo/'+rId+'/exposer';
        },
        get : function (rId, md5) {
            return '/demo/'+rId+'/'+md5+'/execution';
        }
    },
    validatePhone : function (phone) {//验证是否手机号
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    handlerRedGet : function (rId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始抢红包</button>');
        $.post(red.url.exposer(rId),{},function (data) {
            console.log(data);
            if(data && data['success']){
                var exposer = data['data'];
                if(exposer['exposed']){
                    var md5 = exposer['md5'];
                    var getUrl = red.url.get(rId, md5);
                    $("#killBtn").one('click',function () {
                     //   $(this).addClass('disabled');
                        $.post(getUrl, {}, function (data) {
                            if(data && data['success']){
                                var result = data['data'];
                                var money=result['money'];
                                var state = result['state'];
                                var stateInfo = result['stateInfo'];
                                node.html(money+'元');

                            }else{
                                var result = data['data'];
                                var stateInfo = result['stateInfo'];
                                node.html(stateInfo);
                            }
                        });
                    });
                    node.show();
                }else{
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countDown(seckillId,now,start,end);
                }
            }else{
                console.warn("data:"+data);
            }
        });
    },
    countDown : function (rId, nowTime, startTime, endTime) {
        var $redBox = $("#red-box");
        if(nowTime >= endTime){
            $redBox.html('秒杀结束!');
        }else if(nowTime < startTime){
            var GetTime = new Date(startTime + 1000);
            $redBox.countdown(GetTime,function (e) {
                var format = e.strftime('秒杀计时: %D天 %H时 %M分 %S秒');
                $redBox.html(format);
            }).on('finish.countdown',function () {
                red.handlerRedGet(rId, $redBox);
            });

        }else{
            red.handlerRedGet(rId, $redBox);
        }
    },
    detail : {
        init : function (args) {
            console.log(args);
            var Phone = $.cookie("userPhone");//从cookie拿手机号
            if(!red.validatePhone(Phone)){
                var $PhoneModal = $("#PhoneModal");
                $PhoneModal.modal('show');
                $("#PhoneBtn").click(function () {
                    var Phone = $("#PhoneKey").val();
                    if(red.validatePhone(Phone)){
                        $.cookie("userPhone",Phone,{expires:7, path:"/demo"});
                        window.location.reload();
                    }else{
                        $("#PhoneMessage").css("display","inline-block");
                    }
                });
            }else{
                var id = args["rId"];
                console.log(id);
                var startTime = args["startTime"];
                console.log(startTime);
                var endTime = args["endTime"];
                $.get(red.url.now(),function (data) {
                    console.log(data);
                    if(data && data['success']){
                        var now = data['data'];
                        red.countDown(id, now, startTime, endTime);
                    }else{
                        console.warn("data:"+data);
                    }
                });
            }
        }
    }
};
