<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" async="" src="/js/conversion_async.js"></script>
    <script type="text/javascript" async="" src="/js/wl.js"></script>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/css/base.css">
    <link href="/css/purchase.2012.css?v=201410141639" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/css/saved_resource">
    <title>商品已成功加入购物车</title>
    <script async="" src="/js/gtm.js"></script>
    <script type="text/javascript">window.pageConfig = {compatible: true};</script>
    <script type="text/javascript" src="/js/saved_resource(1)"></script>
    <style type="text/css" adt="123"></style>
    <link type="text/css" rel="stylesheet" href="/css/saved_resource(2)" source="widget">
</head>
<body>
<!--shortcut start-->
<jsp:include page="commons/shortcut.jsp"/>
<!--shortcut end-->
<div id="settleup-2014" class="dorpdown">
    <div class="cw-icon"><i class="ci-left"></i> <i class="ci-right">&gt;</i>
        <i class="ci-count" id="shopping-amount">1</i>
        <a target="_blank" href="http://localhost:8082/cart/cart.html">我的购物车</a>
    </div>
    <div class="dorpdown-layer">
        <div class="spacer"></div>
        <div id="settleup-content">
            <span class="loading"></span>
        </div>
    </div>
</div>
<div id="hotwords-2014"></div>
<span class="clr"></span>

<div class="main">
    <div class="success-wrap">
        <div class="w" id="result">
            <div class="m succeed-box">
                <div class="mc success-cont">
                    <div class="success-lcol">
                        <div class="success-top"><b class="succ-icon"></b>
                            <h3 class="ftx-02">商品已成功加入购物车！</h3>
                        </div>
                        <div class="p-item">
                            <div class="p-img">
                                <a href="http://item.jd.com/536668.html" target="_blank">
                                    <img src="./商品已成功加入购物车_files/576b5c2aN6b828938.jpg"
                                         clstag="pageclick|keycount|201601152|11"></a>
                            </div>
                            <div class="p-info">
                                <div class="p-name">
                                    <a href="http://item.jd.com/536668.html" target="_blank"
                                       clstag="pageclick|keycount|201601152|2"
                                       title="惠普（HP） HP Laserjet PRO P1106激光打印机">惠普（HP） HP Laserjet PRO P1106激光打印机</a>
                                </div>
                                <div class="p-extra">
                                    <span class="txt" title="智慧之选1106">尺码：智慧之选1106</span>
                                    <span class="txt">/  数量：1</span>
                                </div>
                            </div>
                            <div class="clr"></div>
                        </div>
                    </div>
                    <div class="success-btns">
                        <div class="success-ad">
                            <a href="http://cart.jd.com/addToCart.html?rcd=1&pid=536668&pc=1&rid=1468314854125&em=#none"></a>
                        </div>
                        <div class="clr"></div>
                        <div>
                            <a class="btn-tobback"
                               href="http://cart.jd.com/addToCart.html?rcd=1&pid=536668&pc=1&rid=1468314854125&em=#"
                               onclick="window.history.back();return false;"
                               clstag="pageclick|keycount|201601152|3">返回</a>
                            <a class="btn-addtocart" href="http://localhost:8082/cart/cart.html"
                               id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"> <b></b>
                                去购物车结算
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer start -->
<jsp:include page="commons/footer.jsp"/>
<!-- footer end -->
</body>
</html>