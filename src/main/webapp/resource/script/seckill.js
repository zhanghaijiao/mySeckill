/*存放主要交互逻辑js代码*/
//javascript 模块化
//seckill.detail.init(params)
var seckill = {
    //封装秒杀相关的ajax的url
    URL: {
        now: function () {
            return "/seckill/time/now";
        },
        exposer: function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        execution: function (seckillId, md5) {
            return "/seckill/" + seckillId + "/" + md5 + "/execution";
        },
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $("#seckillBox");
        //时间的判断
        if (nowTime > endTime) {
            //秒杀结束
            console.log("秒杀结束。")
            seckillBox.html("秒杀结束！");
            //seckillBox.show();
        } else if (nowTime < startTime) {
            //秒杀未开始,计时事件绑定
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime("距离秒杀开始时间还有：%D天 %H时 %M分 %S秒");
                seckillBox.html(format);
                //时间完成后回调事件
            }).on("finish.countdown", function () {
                //获取秒杀地址  控制实现逻辑  执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },
    handleSeckill: function (seckillId, node) {
        //处理秒杀
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            //回调函数中执行交互流程
            if (result && result["success"]) {
                var exposer = result["data"];
                if (exposer["exposed"]) {
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposer["md5"];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log("killUrl:" + killUrl);
                    $("#killBtn").one("click", function () {
                        //绑定点击事件 执行秒杀请求的操作
                        $(this).addClass("disabled");
                        //发送秒杀请求
                        $.post(killUrl, {}, function (result) {
                            if (result && result["success"]) {
                                var killResult = result["data"];
                                var state = killResult["state"];
                                var stateInfo = killResult["stateInfo"];
                                node.html('<span class = "label label-success">'+stateInfo+'</span>');

                            }
                        });
                    });
                    node.show();
                } else {
                    //未开启秒杀
                    var now = exposer["now"];
                    var start = exposer["start"];
                    var end = exposer["end"];
                    //重新计时逻辑
                    seckill.countdown(seckillId, now, start, end);
                }
            } else {
                console.log("result:" + result);
            }
        });

    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //用户手机验证和登陆，计时交互
            //规划交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');

            if (!seckill.validatePhone(killPhone)) {
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show: true,     //显示弹出层
                    backdrop: false,//禁止位置关闭
                    keyboard: false  //关闭键盘事件
                });
                $("#killPhoneBtn").click(function () {
                    var inputPhone = $("#killPhoneKey").val();
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie("killPhone", inputPhone, {expires: 7, path: "/seckill"});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $("#killPhoneMessage").hide().html('<label class = "label label-danger">手机号格式错误!</label>').show(300);
                    }
                });
            }
            //已登录
            //计时交互

            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    /*时间判断*/
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log("result:" + result)
                }
            });
        }
    }
}











