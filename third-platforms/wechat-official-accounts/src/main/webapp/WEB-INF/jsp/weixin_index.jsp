<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weixin Test Index</title>
    <!-- 引入 WeUI CDN 链接 -->
    <link rel="stylesheet" href="https://res.wx.qq.com/open/libs/weui/2.3.0/weui.min.css"/>
    <script>
        console.log("starting...")
    </script>
</head>

<body>

Hello ${wxUserInfo.nickname}!

<div id="shareToFriend">
    share to friend
</div>

<div class="weui-cells__title">JSAPI functions</div>
<div class="weui-cells">
    <a class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
            <p>Share to friend</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
    <a class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
            <p>function 2</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
</div>
<a href="javascript:;" class="weui-btn weui-btn_primary">绿色按钮</a>

<!-- Body END -->

<script src="https://res.wx.qq.com/open/js/jweixin-1.6.0.js"/>

<script>
    /**
     * JSAPI interfaces:
     * 基础接口（判断当前客户端版本是否支持指定JS接口），分享接口，图像接口，音频接口，智能接口，设备信息，地理位置，界面操作
     */
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${wxJsapiConfig.appId}', // 必填，公众号的唯一标识
        timestamp: '${wxJsapiConfig.timestamp}', // 必填，生成签名的时间戳
        nonceStr: '${wxJsapiConfig.nonceStr}', // 必填，生成签名的随机串
        signature: '${wxJsapiConfig.signature}',// 必填，签名
        jsApiList: [
            'chooseImage'
        ] // 必填，需要使用的JS接口列表
    });

    wx.ready(function () {
    });

    wx.error(function (res) {
    });
</script>

</body>
</html>