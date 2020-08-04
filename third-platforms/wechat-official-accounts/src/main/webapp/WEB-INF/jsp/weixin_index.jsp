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
    <%--    <script>--%>
    <%--        console.log("starting...")--%>
    <%--    </script>--%>
</head>

<body>

Hello ${wxUserInfo.nickname}!


<div class="weui-cells__title">JSAPI functions: </div>
<div class="weui-cells">

    <!-- image interface begin -->
    <!-- choose image and get localId-->
    <a id="chooseImage" class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
            <p>Choose Image</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>

    <!-- get local image data -->
    <a id="getLocalImgData" class="weui-cell  weui-cell_access" href="javascript:">
        <div class="weui-cell__bd">
            <p>Get Local Image</p>
        </div>
        <div class="weui-cell__ft">
        </div>
    </a>
    <div id="localImageShowArea">
        <!-- append images in here -->
    </div>
    <!-- image inteface end -->

</div>
<a href="javascript:;" class="weui-btn weui-btn_primary">绿色按钮</a>

<!-- Body END -->

<script src="https://res.wx.qq.com/open/js/jweixin-1.6.0.js"></script>

<script>
    /**
     * JSAPI interfaces:
     * 基础接口（判断当前客户端版本是否支持指定JS接口），分享接口，图像接口，音频接口，智能接口，设备信息，地理位置，界面操作
     * Note: If JavaScript code in this file have some grammar errors，the page will not alert JSSDK debug infomations
     */
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '${wxJsapiConfig.appId}', // 必填，公众号的唯一标识
        timestamp: ${wxJsapiConfig.timestamp}, // 必填，生成签名的时间戳
        nonceStr: '${wxJsapiConfig.nonceStr}', // 必填，生成签名的随机串
        signature: '${wxJsapiConfig.signature}',// 必填，签名
        jsApiList: [
            'updateAppMessageShareData',
            'updateTimelineShareData',
            'chooseImage'
        ] // 必填，需要使用的JS接口列表
    });

    wx.ready(function () {
        console.log("start...");


        /**
         * 基础接口
         */
        // 判断当前客户端版本是否支持指定JS接口
        // Auto running
        wx.checkJsApi({
            jsApiList: [
                'updateAppMessageShareData',
                'updateTimelineShareData',
                'chooseImage',
                'previewImage',
                'uploadImage',
                'downloadImage',
                'getLocalImgData'
            ], // 需要检测的JS接口列表，所有JS接口列表见附录2,
            success: function(res) {
                alert("Support JSSDK interfaces: " + JSON.stringify(res.checkResult));
                // 以键值对的形式返回，可用的api值true，不可用为false
                // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
            }
        });

        /**
         * 分享接口
         */
        //自定义“分享给朋友”及“分享到QQ”按钮的分享内容
        // Auto running
        wx.updateAppMessageShareData({
            title: 'wxPage-index', // 分享标题
            desc: 'Test share to friend.', // 分享描述
            link: '${wxJsapiConfig.url}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: '', // 分享图标
            success: function () {
                // 设置成功
                console.log("Set share to friend info successfully!");
            }
        })

        // 自定义“分享到朋友圈”及“分享到QQ空间”按钮的分享内容
        // Auto running
        wx.updateTimelineShareData({
            title: 'wxPage-index', // 分享标题
            desc: 'Test share to timeline', // 分享描述
            link: '${wxJsapiConfig.url}', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
            imgUrl: '', // 分享图标
            success: function () {
                // 设置成功
                console.log("Set share to timeline info successfully!");
            }
        })

        /**
         * 图像接口
         */
        // 拍照或从手机相册中选图接口
        // onclick running
        var localIds;
        document.querySelector('#chooseImage').onclick = function () {
            wx.chooseImage({
                count: 9, // 默认9
                sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
                sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
                success: function (res) {
                    localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
                    console.log("chooseImage -- localIds first: " + localIds[0]);
                }
            });
        };

        // 获取本地图片接口
        // onclick running
        document.querySelector('#getLocalImgData').onclick = function () {
            console.log("getLocalImage -- localIds: " + localIds);
            alert("localIds length: " + localIds.length);
            var localImageShowArea = document.querySelector('#localImageShowArea');
            localImageShowArea.innerHTML = '';
            for (var i=0; i < localIds.length; i++) {
                wx.getLocalImgData({
                    localId: localIds[i], // 图片的localID
                    success: function (res) {
                        var localData = res.localData; // localData是图片的base64数据，可以用img标签显示
                        // console.log("local image data-0: " + localData);
                        if (localData.toString().substring(0, 10) != 'data:image') {
                            localData = "data:image/png;base64, " + localData;
                        }
                        // console.log("local image data: " + localData);
                        localImageShowArea.appendChild(document.createTextNode('image: '));
                        localImageShowArea.appendChild(document.createElement('br'));
                        localImageShowArea.appendChild(document.createElement('img')).src=localData;
                        localImageShowArea.appendChild(document.createElement('br'));
                    }
                });
            }
        };

    });

    wx.error(function (res) {
        alert(res.errMsg);
    });
</script>

</body>
</html>