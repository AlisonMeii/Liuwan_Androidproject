# Abstruct
&emsp;&emsp;Our project is based on **Android Studio**. It is an **app** themed on **Beijing parks**.The purpose of our development is to make it easy for Beijing citizens to visit the parks in their leisure time. We not only provide users with park data, but also design services such as navigation and forums. Our project features different travel options according to the four seasons and a change in the theme colour of the app. It can better provide users with convenient travelling services.
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/Abstruct.png?raw=true"/></div>

# 1.Inspiration & Design
## 1.1 Project Background
<strong>tags:</strong> <strong>city life</strong>&emsp;<strong>community activities</strong>&emsp;<strong>physical exercise</strong>&emsp;<strong>travels</strong>&emsp;<strong>enjoy leisure</strong>  
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/park.png?raw=true"/></div>

- Beijing is a large city with many parks, numbering 1,000+
- Park visits in high demand, 280w visitors on National Day holiday(Data source: Beijing Daily Chinese government website)
- Beijing's parks are rich and varied, making them an excellent choice for citizens and tourists.   
- People are focusing more and more on spirituality in their leisure time.

## 1.2 Project Characteristics
- Range accuracy
- Convenient travelling
- Simple style
- Wide age range of audience
- Full-featured

## 1.3 Theme Colours
&emsp;&emsp;We used the following colour palette to design the overall UI interface. We designed different theme colours according to the characteristics of different seasons as follows, for example, in spring we chose the vibrant green :leaves: , in summer we chose the delicious watermelon as the theme colour :yum: , in autumn we chose the gloomy brownish-yellow :maple_leaf: , and in winter we chose the snow-white and dark blue :snowflake: .
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/color.png?raw=true"/></div>

## 1.4 Logo & IP
&emsp;&emsp;When designing the logo, we chose the shape of a small foot. This corresponds exactly to our functional area: travelling. We also designed the logo in different seasonal theme colours.  
&emsp;&emsp;For the IP design, we chose the orange fat cat which often appears in the park, making our interface more lively and cute! It can also better fit our product theme.
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/Logo&IP.png?raw=true"/></div>

## 1.5 Final Design Result
&emsp;&emsp;The main programmes for the four seasons are shown below：
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/spring.png?raw=true"/></div>
<p align="center">The image above shows the UI based on spring</p>
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/summer.png?raw=true"/></div>
<p align="center">The image above shows the UI based on summer</p>
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/autumn.png?raw=true"/></div>
<p align="center">The image above shows the UI based on autumn</p>
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/winter.png?raw=true"/></div>
<p align="center">The image above shows the UI based on winter</p>

# 2.Technical Realisation
## 2.1 Usage Process
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/process.png?raw=true"/></div>

## 2.2 Functional Realisation
- Implementation of all page jumps
- All front-end pages have been developed according to the UI design.
- More difficult interface animation: after scrolling down, a hoverball button appears, and you can jump by clicking on it
- Use Baidu Map API to achieve location acquisition and positioning function
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/navi.png?raw=true"/></div>

- Determine whether to register or not and jump to the interface accordingly
- Use Python crawler to get the information of all the parks counted in the Beijing Municipal Bureau of Landscape Architecture and Greening, a total of 1,050  
<div align=center><img src="https://github.com/AlisonMeii/Liuwan_Androidproject/blob/main/Image/database.png?raw=true"/></div>

