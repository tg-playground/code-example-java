<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weixin Test Index</title>
    <!-- 引入 WeUI CDN 链接 -->
    <link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/2.3.0/weui.min.css"/>
    <script>
        console.log("starting...")
    </script>
</head>

<body>

Hello World!

<a href="javascript:;" class="weui-btn weui-btn_primary">绿色按钮</a>



<!-- Body END -->

<script src="https://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>

<script>
    wx.checkJsApi({
        jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
        success: function(res) {
            console.log("Support list: " + res);
            // 以键值对的形式返回，可用的api值true，不可用为false
            // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
        }
    });
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${jsapiConfig.appId}', // 必填，公众号的唯一标识
        timestamp: ${jsapiConfig.timestamp}, // 必填，生成签名的时间戳
        nonceStr: '${jsapiConfig.nonceStr}', // 必填，生成签名的随机串
        signature: '${jsapiConfig.signature}',// 必填，签名
        jsApiList: ['chooseImage'] // 必填，需要使用的JS接口列表
    });
    wx.ready(function(){
        console.log("");
        // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    });
    wx.error(function(res){
        console.log("error to call Weixin!");
        // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
    });
</script>

</body>
</html>