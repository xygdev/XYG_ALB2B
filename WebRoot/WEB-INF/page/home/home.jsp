<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="plugin/bootstrap/css/bootstrap.min.css">
    <link href="plugin/css/font-awesome.min.css" rel="stylesheet">
    <link href="plugin/css/home/flexslider.css" rel="stylesheet" >
    <link href="plugin/css/home/home.css" rel="stylesheet">
    <link href="plugin/css/home/queries.css" rel="stylesheet">
    <link href="plugin/css/home/animate.css" rel="stylesheet">
    	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
  </head>
  
  <body id="homepage">
    <div id="container" style="width:100%;overflow:hidden">
    <header id="home">
      <section class="hero" id="hero">
        <div class="container">
          <div class="row">
            <div class="col-md-12 text-right navicon">
              <a id="nav-toggle" class="nav_slide_button" href="#" style="visibility:hidden"><span></span></a>
            </div>
          </div>
          <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center inner">
              <h1 class="animated fadeInDown">信义<span>玻璃</span></h1>
              <p class="animated fadeInUp delay-05s">融制透明<em>世界</em>&nbsp;建设绿色<em>空间</em></p>
            </div>
          </div>
        </div>
      </section>
    </header>
    <section class="intro text-center section-padding" id="intro">
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2 wp1">
            <h1 class="arrow">浮法玻璃</h1>
            <p><a href="#">浮法玻璃是透明平板玻璃，广泛用于生产钢化、夹层、热弯中空、镀膜等高档玻璃深加工领域。</a></p>
          </div>
        </div>
      </div>
    </section>
    <section class="features text-center section-padding" id="features">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <h1 class="arrow">产品简介</h1>
            <div class="features-wrapper">
              <div class="col-md-4 wp2">
                <div class="icon">
                  <img src="/image/home/CB.jpg" style="width:100%">
                </div>
                <h2>白玻</h2>
                <p>本产品具有表面光滑无波纹，透视性佳规格可做弹性配合，减少切片损失等特点。可提供2-22mm厚度范围及超长超大板玻璃。</p>
              </div>
              <div class="col-md-4 wp2 delay-05s">
                <div class="icon">
                  <img src="/image/home/Green.jpg" style="width:100%">
                </div>
                <h2>F绿玻</h2>
                <p>本产品具有紫外线透过率低，遮阳效果好，增加建筑物外观色彩变化，创造更高价值感等特点。信义玻璃生产的薄板绿玻可常年保证供应，满足高档汽车玻璃加工需求。</p>
              </div>
              <div class="col-md-4 wp2 delay-1s">
                <div class="icon">
                  <img src="/image/home/Gray.jpg" style="width:100%">
                </div>
                <h2>欧洲灰玻</h2>
                <p>本产品具有紫外线透过率低，遮阳效果好，增加建筑物外观色彩变化，创造更高价值感等特点。</p>
              </div>
              <div class="clearfix"></div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section class="text-center" id="responsive">
        <div class="container-fluid nopadding responsive-services">
          <div class="wrapper">
            <div class="iphone">
              <div class="wp3"></div>
            </div>
            <div class="fluid-white"></div>
          </div>
          <div class="container designs">
            <div class="row">
              <div class="col-md-5 col-md-offset-7">
                <div id="servicesSlider">
                  <ul class="slides">
                    <li>
                      <h1 class="arrow">生产工艺</h1>
                      <p>浮法玻璃生产的成型过程是在通入保护气体（N2及H2）的锡槽中完成的 。熔融玻璃从池窑中连续流入并漂浮在相对密度大的锡液表面上，在重力和表面张力的作用下，玻璃液在锡液面上铺开、摊平、形成上下表面平整、硬化、冷却后被引上过渡辊台。辊台的辊子转动 ，把玻璃带拉出锡槽进入退火窑，经退火、切裁，就得到浮法玻璃产品 。浮法与其他成型方法比较， 其优点是： 适合于高效率制造优质平板玻璃，如没有波筋、厚度均匀、上下表面平整 、互相平行；生产线的规模不受成形方法的限制，单位产品的能耗低； 成品利用率高； 易于科学化管理和实现全线机械化、自动化，劳动生产率高；连续作业周期可长达几年，有利于稳定地生产；可为在线生产一些新品种提供适合条件，如电浮法反射玻璃 、退火时喷涂膜玻璃、冷端表面处理等 。</p>
                    </li>
                    <li>
                      <h1 class="arrow">特点</h1>
                      <p>对于浮法玻璃来说，由于厚度的均匀性比较好，其产品的透明度也比较强，所以经过锡面的处理，比较光滑，在光滑的作用下火焰以及抛光的作用下，形成了一种表面比较整齐、平面度比较好，光学性能比较强的玻璃，这种浮法玻璃的装饰特性特别好，更具有良好的透明性、明亮性、纯净性、以及室内的光线明亮等特点，视野的广阔性能，同时还要具有建筑门窗、天然采光的材料的最佳首选材料，更是极富应用的建筑材料之一，可以说，在建筑玻璃的多种种类来看，这种浮法玻璃应用最大，是进行玻璃深加工的最为重要的原片之一。超白的浮法玻璃以透明度纯净度最佳为主要特色</p>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section class="swag text-center">
        <div class="container">
          <div class="row">
            <div class="col-md-8 col-md-offset-2">
              <h1>马来西亚工业园<span>即将<em>投产</em> 敬请期待</span></h1>
              <a href="#"  data-up="true" class="down-arrow-btn"><i class="fa fa-chevron-up"></i></a>
            </div>
          </div>
        </div>
      </section>
      <section class="portfolio text-center section-padding hidden-sm hidden-xs" id="portfolio">
        <div class="container">
          <div class="row">
            <div id="portfolioSlider">
              <ul class="slides">
                <li>
                  <div class="col-md-4 wp4">
                    <div class="overlay-effect effects clearfix">
                      <div class="img">
                        <img src="/image/home/DG.png" alt="Portfolio Item">
                        <div class="overlay">
                          <a href="#" class="expand"><i class="fa fa-search"></i><br>View More</a>
                          <a class="close-overlay hidden">x</a>
                        </div>
                      </div>
                    </div>
                    <h2>信义超薄玻璃（东莞）有限公司</h2>
                    <p>广东省东莞市虎门镇路东村信义玻璃工业园</p>
                  </div>
                  <div class="col-md-4 wp4 delay-05s">
                    <div class="overlay-effect effects clearfix">
                      <div class="img">
                        <img src="/image/home/WH.png" alt="Portfolio Item">
                        <div class="overlay">
                          <a href="#" class="expand"><i class="fa fa-search"></i><br>View More</a>
                          <a class="close-overlay hidden">x</a>
                        </div>
                      </div>
                    </div>
                    <h2>信义节能玻璃（芜湖）有限公司</h2>
                    <p>安徽省芜湖市经济技术开发区信义路2号信义玻璃工业园</p>
                  </div>
                  <div class="col-md-4 wp4 delay-1s">
                    <div class="overlay-effect effects clearfix">
                      <div class="img">
                        <img src="/image/home/TJ.png" alt="Portfolio Item">
                        <div class="overlay">
                          <a href="#" class="expand"><i class="fa fa-search"></i><br>View More</a>
                          <a class="close-overlay hidden">x</a>
                        </div>
                      </div>
                    </div>
                    <h2>信义玻璃（天津）有限公司</h2>
                    <p>天津市武清开发区广源道北侧信义玻璃工业园</p>
                  </div>
                </li>
                <li>
                  <div class="col-md-4 wp4">
                    <div class="overlay-effect effects clearfix">
                      <div class="img">
                        <img src="/image/home/YK.png" alt="Portfolio Item">
                        <div class="overlay">
                          <a href="#" class="expand"><i class="fa fa-search"></i><br>View More</a>
                          <a class="close-overlay hidden">x</a>
                        </div>
                      </div>
                    </div>
                    <h2>信义玻璃（营口）有限公司</h2>
                    <p>辽宁省营口经济技术开发区滨海工业区信义玻璃工业园</p>
                  </div>
                  <div class="col-md-4 wp4 delay-05s">
                    <div class="overlay-effect effects clearfix">
                      <div class="img">
                        <img src="/image/home/DY.png" alt="Portfolio Item">
                        <div class="overlay">
                          <a href="#" class="expand"><i class="fa fa-search"></i><br>View More</a>
                          <a class="close-overlay hidden">x</a>
                        </div>
                      </div>
                    </div>
                    <h2>信义节能玻璃（四川）有限公司</h2>
                    <p> 四川省德阳市岷山南路信义玻璃工业园</p>
                  </div>
                  <div class="col-md-4 wp4 delay-1s">
                    <div class="overlay-effect effects clearfix">
                      <div class="img">
                        <img src="/image/home/JM.png" alt="Portfolio Item">
                        <div class="overlay">
                          <a href="#" class="expand"><i class="fa fa-search"></i><br>View More</a>
                          <a class="close-overlay hidden">x</a>
                        </div>
                      </div>
                    </div>
                    <h2>信义环保特种玻璃（江门）有限公司</h2>
                    <p>广东省江门市高新技术产业开发区8号地</p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </section>
      <div class="ignite-cta text-center hidden-sm hidden-xs" style="padding:0">
		<div style="width:100%;padding:70px 0;background-color:black;opacity:0.6">
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <a href="#" data-up="true" class="down-arrow-btn"><i class="fa fa-chevron-up"></i></a>
              </div>
            </div>
          </div>
		</div>
      </div>
      <section class="team text-center section-padding hidden-sm hidden-xs" id="team">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <h1 class="arrow">精英团队</h1>
            </div>
          </div>
          <div class="row">
            <div class="team-wrapper">
              <div id="teamSlider">
                <ul class="slides">
                  <li>
                    <div class="col-md-4 wp5">
                      <img src="/image/home/batman.jpg" alt="Team Member">
                      <h2>杨镇城</h2>
                      <p>信义超薄玻璃（东莞）有限公司</p>
                      <p>电话:0769-85012863,0769-85266666/6110</p>
					  <p>传真:0769-85265533</p>
					  <p>邮箱:xxx@xinyiglass.com</p>
                    </div>   
                    <div class="col-md-4 wp5 delay-05s">
                      <img src="/image/home/ironman.jpg" alt="Team Member">
                      <h2>周冬平</h2>
                      <p>信义节能玻璃（芜湖）有限公司</p>
                      <p>电话:0553-589 5518</p>
					  <p>传真:0553-589 5777</p>
					  <p>邮箱:xxx@xinyiglass.com</p>
                    </div>
                    <div class="col-md-4 wp5 delay-1s">
                      <img src="/image/home/american.jpg" alt="Team Member">
                      <h2>邵玉辉</h2>
                      <p>信义玻璃（天津）有限公司</p>
                      <p>电话:022-82971201</p>
				      <p>传真:022-82159967</p>
					  <p>邮箱:xxx@xinyiglass.com</p>
                    </div>
                  </li>
                  <li>
                    <div class="col-md-4 wp5">
                      <img src="/image/home/hock.jpg" alt="Team Member">
                      <h2>陈泽明</h2>
                      <p>信义玻璃（营口）有限公司</p>
                      <p>电话:0417-8205178</p>
				      <p>传真:0417-8203355</p>
					  <p>邮箱:xxx@xinyiglass.com</p>
                    </div>
                    <div class="col-md-4 wp5 delay-05s">
                      <img src="/image/home/thunder.jpg" alt="Team Member">
                      <h2>李成</h2>
                      <p>信义节能玻璃（四川）有限公司</p>
                      <p>电话:18227187260</p>
				      <p>传真:022-82159967</p>
					  <p>邮箱:xxx@xinyiglass.com</p>
                    </div>
                    <div class="col-md-4 wp5 delay-1s">
                      <img src="/image/home/deadpool.jpg" alt="Team Member">
                      <h2>郑长乙</h2>
                      <p>信义环保特种玻璃（江门）有限公司</p>
                      <p>电话:0750-3798888</p>
					  <p>传真:0750-3779999</p>
					  <p>邮箱:xxx@xinyiglass.com</p>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </section>
      <section class="subscribe text-center" style="padding:0">
        <div style="width:100%;padding:120px 0 140px;background-color:black;opacity:0.6">
          <div class="container">
            <h1><i class="fa fa-paper-plane"></i><span>填写邮箱订阅公司最新资讯</span></h1>
            <form action="#">
              <input type="text" name="" value="" placeholder="" required>
              <input type="submit" name="" value="提交">
            </form>
          </div>
		</div>
      </section>
      <section class="dark-bg text-center section-padding contact-wrap hidden-sm hidden-xs" id="contact">
        <a href="#" data-up="true" class="up-btn"><i class="fa fa-chevron-up"></i></a>
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <h1 class="arrow">有关网站问题，请联系IT团队</h1>
            </div>
          </div>
          <div class="row contact-details">
            <div class="col-md-4">
              <div class="light-box box-hover">
                <h2><i class="fa fa-map-marker"></i><span>地址</span></h2>
                <p>广东省东莞市虎门镇路东村信义玻璃工业园</p>
              </div>
            </div>
            <div class="col-md-4">
              <div class="light-box box-hover">
                <h2><i class="fa fa-mobile"></i><span>电话</span></h2>
                <p>0755-8888888</p>
              </div>
            </div>
            <div class="col-md-4">
              <div class="light-box box-hover">
                <h2><i class="fa fa-paper-plane"></i><span>邮箱</span></h2>
                <p><a href="#">erp@xinyiglass.com</a></p>
              </div>
            </div>
          </div>
        </div>
      </section>
      <footer>
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <ul class="legals">
                <li><a href="#">信义玻璃控股有限公司 版权所有</a></li>
                <li><a href="#">法律声明</a></li>
              </ul>
            </div>
            <div class="col-md-6 credit">
              <p>设计团队 <a href="http://www.peterfinlan.com/">信义玻璃</a><a href="http://tympanus.net/codrops/"><em>ERP团队</em></a></p>
            </div>
          </div>
        </div>
      </footer>
      <!-- jQuery 2.1.4 -->
   	  <script src="plugin/jQuery/jQuery-2.1.4.min.js"></script>
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src="plugin/js/waypoints.min.js"></script>
      <!-- Bootstrap 3.3.5 -->
   	  <script src="plugin/bootstrap/js/bootstrap.min.js"></script>
      <script src="plugin/js/scripts.js"></script>
      <script src="plugin/js/jquery.flexslider.js"></script>
      <script src="plugin/js/modernizr.js"></script>
      <script>
        /****绑定a标签****/
		$('a[data-up]').on('click', function(e) {
			e.preventDefault();//阻止<a>标签默认的点击事件（超链接跳转）
			//var speed=200;//滑动的速度
			//window.parent.$("body").animate({ scrollTop: 0 }, speed);(jQuery);
			window.parent.$('body,html').animate({scrollTop:0},1000);(jQuery);  
            return false;  
		});
      </script>
    </div>
  </body>
</html>