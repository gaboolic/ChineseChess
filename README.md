# ChineseChess
java版中国象棋
打算先做一个简单的界面
再做一个αβ搜索，用局面估值

在chatgpt帮助下，几分钟时间把各个棋子的行动路线生成出来了，太牛了
之前发现有王不见王、别马脚的规则有bug改掉了，今天突然发现chatgpt写的走棋规则还是有很多bug，
车居然能穿透所有敌军直接打到底线。
又改了几天，走棋规则都改完了，局面估值、αβ搜索也写完了
测试的时候遇到第一步炮先打馬，发现局面估值顾前不顾后了，暂时修改了一下
现在开局屏风马，出贴身車都会了。


`bestStep:Step{start=Point{x=0, y=0}, end=Point{x=3, y=0}} maxScore:-299.2554689359521
Step{start=Point{x=0, y=0}, end=Point{x=3, y=0}}`

`Chessman{point=Point{x=0, y=0}, class=Rook, color=1}---null`

```
　　　車將士象　　
　　　　士　　　　
　砲　　象　馬　　
卒　　　卒　傌車卒
　馬卒　　　　　　
　　　　　　兵　　
兵　兵　兵　　　兵
　　傌　　　炮　俥
　　　俥　　　　　
　　相仕帥仕相　　
```