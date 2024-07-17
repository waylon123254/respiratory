<template>
	<div>
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<img src="..\src\views\img\banner01.png" alt="">
				</div>
				<div class="swiper-slide">
					<img src="..\src\views\img\banner02.png" alt="">
				</div>
				<div class="swiper-slide">
					<img src="..\src\views\img\banner03.png" alt="">
				</div>
				<div class="swiper-slide">
					<img src="..\src\views\img\banner04.png" alt="">
				</div>
				<div class="swiper-slide">
					<img src="..\src\views\img\banner05.png" alt="">
				</div>
			</div>
			<!-- 如果需要分页器 -->
			<div class="swiper-pagination"></div>
			<!-- 如果需要导航按钮 -->
			<div class="swiper-button-prev"></div>
			<div class="swiper-button-next"></div>
		</div>

		<div style="margin-top:20px;display:flex">
    <div style="height:90%;width:90%;margin-right:2%;margin-left:10%;justify-content:center; overflow: auto;">
    <el-card style="height:240px; width:100%;align-content:center;text-align:center; margin:10px 10px">
        <el-scrollbar style="height: 100%; margin:10px 10px">
						基于Spring Boot 和 Vue2 的高校兼职信息管理系统的设计与开发，将使用 Spring Boot 作为后端框架，Vue2 作为前端框架来构建整个系统。后端使用 Spring Boot 可以快速搭建高效的 Java 后端服务，并提供各种必要的功能，如用户管理、权限控制、数据存储等。同时，前端使用 Vue2 可以实现动态且友好的用户界面，提供良好的用户体验。
						该系统将实现以下功能：
						<br>
						用户管理：包括学生、企业用户和管理员的注册、登录和信息管理。
						兼职信息发布：企业用户可以发布兼职信息，包括工作内容、薪酬、工作时间等详细信息。
						兼职信息浏览：学生用户可以浏览和搜索各类兼职信息，并对感兴趣的兼职进行申请。
						申请管理：企业用户可以管理收到的兼职申请，并进行处理。
						统计分析：管理员可以对系统内的兼职信息和用户数据进行统计和分析，为学校就业指导提供参考。
						通过使用 Spring Boot 提供的 RESTful 接口和 Vue2 提供的组件化开发，可以实现前后端的分离开发，提高了系统的灵活性和可维护性。同时，采用这种技术栈还可以充分利用 Spring Boot 和 Vue2 的生态资源，加快开发进度，提高系统的稳定性和性能。

						总之，基于 Spring Boot 和 Vue2 的高校兼职信息管理系统将能够为高校学生和企业提供便捷的兼职信息发布和管理服务，为校园就业创造更多机会，提升校园就业服务的质量和效率。
					</el-scrollbar>
				</el-card>
        
			</div>
    <div style="width: 30%; height: 100%; margin-left: 20px;">
<el-card>
你好
</el-card>
    </div>
			<!-- <el-card class="announcement-card">
				<div slot="header" class="announcement-header">
					<i class="el-icon-bell"></i>
					<span>公告</span>
				</div>
				<div class="announcement-content">
          <div style="flex: 1; border-bottom: 1px solid #ebeef5; margin-left: 10px;"></div>
				</div>
			</el-card> -->
		</div>
		<el-row>
			<el-col :span="8">
				<el-card style="min-height: 200px;">
					<div slot="header">
						<span>卡片一</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
			<el-col :span="8">
				<el-card style="min-height: 200px;">
					<div slot="header">
						<span>卡片二</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
			<el-col :span="8">
				<el-card style="min-height: 200px;">
					<div slot="header">
						<span>卡片三</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
		</el-row>
		<el-row>
			<el-col :span="6">
				<el-card>
					<div slot="header">
						<span>卡片一</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
			<el-col :span="6">
				<el-card>
					<div slot="header">
						<span>卡片二</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
			<el-col :span="6">
				<el-card>
					<div slot="header">
						<span>卡片三</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
			<el-col :span="6">
				<el-card>
					<div slot="header">
						<span>卡片四</span>
					</div>
					<!-- 卡片内容 -->
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
import Swiper from "swiper"; // 注意引入的是Swiper
import "swiper/css/swiper.min.css"; // 注意这里的引入

export default {
  name: "Home",
  data() {
    return;
    {
      tableData: [];
    }
  },
  mounted() {
    const mySwiper = new Swiper(".swiper-container", {
      loop: true,
      autoplay: {
        delay: 200
      },
      pagination: {
        el: ".swiper-pagination",
        clickable: true
      },
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
      }
    });
  }
};
</script>
<style>
.el-row {
  margin-top: 20px; /* 添加上方间隔 */
}

.el-col {
  margin-bottom: 20px; /* 添加下方间隔 */
}
</style>
