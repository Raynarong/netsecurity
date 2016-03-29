﻿/**
 * @requires OpenCharts/BaseTypes/Class.js
 * @requires OpenCharts/BaseTypes/Point.js
 * @requires OpenCharts/BaseTypes/Rect.js
 * @requires OpenCharts/TextStyle.js
 *@requires OpenCharts/Style.js
 * @requires OpenCharts/Item.js
 * @requires OpenCharts/RenderEnginer.js
 * @requires OpenCharts/Charts.js
 */

/**
 * Class: OpenCharts.Chart.BarChart
 * 柱状图表类。用于创建柱状类型的统计图表
 * Inherits from:
 *  - <OpenCharts.Chart>
 */
OpenCharts.Chart.BarChart = OpenCharts.Class(OpenCharts.Chart,{

    /**
     * Property: nMaxSpace
     * {Number} 数值数组最大长度
     */
    nMaxSpace:null,

    /**
     * APIProperty: coordinateStyle
     * {<OpenCharts.Coordinate>} 坐标轴风格
     */
    coordinateStyle:null,

    /**
     * Property: nXRange
     * {Number} x轴单位坐标值
     */
    nXRange:null,

    /**
     * Property: axesHeight
     * {Number} 坐标轴高度
     */
    axesHeight:null,

    /**
     * Property: nYRange
     * {Number} y轴单位坐标值
     */
    nYRange:null,

    /**
     * Property: nYStepNumber
     * {Number} y轴段数
     */
    nYStepNumber:null,

    /**
     * Property: stepYValue
     * {Number} Y轴每段大小
     */
    stepYValue:null,

    /**
     * Constructor: OpenCharts.Chart.BarChart
     *
     * Parameters:
     * div - {Obj} 外部传入canvas元素
     * options - {Object} 设置该类开放的属性。
     */
    initialize: function (div, options){
        OpenCharts.Chart.prototype.initialize.apply(this,
            arguments);

        var me = this;
        me.coordinateStyle = new OpenCharts.Coordinate();
    },

    /**
     * Method: renderAxes
     * 绘制坐标轴
     */
    renderAxes:function(){
        var me = this;
        if(me.coordinateStyle.bShowAxes && me.itemArray != null)
        {
            var nXRange = me.nXRange;
            var height =  me.axesHeight;
            var nYRange = me.nYRange;
            var nYStepNumber = me.nYStepNumber;

            //XY轴绘制
            me.myContext.strokeStyle = me.coordinateStyle.axesStyle.strokeStyle;
            me.myContext.lineWidth = me.coordinateStyle.axesStyle.lineWidth;
            me.myContext.beginPath();
            me.myContext.moveTo(nXRange,me.coordinateStyle.headRange );
            me.myContext.lineTo(nXRange,me.height-me.coordinateStyle.footerRange);
            me.myContext.lineTo(me.width - nXRange,me.height-me.coordinateStyle.footerRange);
            me.myContext.lineTo(me.width - nXRange,me.coordinateStyle.headRange);
            me.myContext.closePath();
            me.myContext.stroke();

            {
                me.myContext.save();
                me.myContext.lineWidth = 1;
                me.myContext.strokeStyle  = "#dbe1e1";
                me.myContext.globalCompositeOperation = "destination-over";

                if(me.coordinateStyle.bShowYAxesGrid)
                {
                    //Y刻度绘制
                    for(var j=1;j<nYStepNumber+1;j++)
                    {
                        me.myContext.beginPath();
                        me.myContext.moveTo(nXRange,me.height - me.coordinateStyle.footerRange - nYRange*j);
                        me.myContext.lineTo(me.width - nXRange,me.height - me.coordinateStyle.footerRange - nYRange*j);
                        me.myContext.stroke();
                        me.myContext.closePath();
                    }
                }

                me.myContext.restore();

            }

            //Y刻度绘制
            for(var j=1;j<nYStepNumber+1;j++)
            {
                me.myContext.beginPath();
                me.myContext.moveTo(nXRange,me.height - me.coordinateStyle.footerRange - nYRange*j);
                me.myContext.lineTo(nXRange+me.coordinateStyle.scaleLength*(j%2==0?2:1),me.height - me.coordinateStyle.footerRange - nYRange*j);
                me.myContext.stroke();
                me.myContext.closePath();
            }

            //Text Prepare to Draw
            me.myContext.font = me.coordinateStyle.axesTextStyle.fStyle + " " + me.coordinateStyle.axesTextStyle.fWeight + " " +
                me.coordinateStyle.axesTextStyle.fSize + " " + me.coordinateStyle.axesTextStyle.fFamily;
            me.myContext.fillStyle = me.coordinateStyle.axesTextStyle.fColor;
            me.myContext.textAlign = me.coordinateStyle.axesTextStyle.fTextAlign;
            //Y轴文字
            if(me.coordinateStyle.bShowYAxesText)
            {
                var stepYValue = Math.ceil(me.getMaxValue()/nYStepNumber);
                me.myContext.textAlign = "right";
                if(me.coordinateStyle.axesTextStyle.bFillText)
                {
                    for(var j=0;j<nYStepNumber+1;j++)
                    {
                        me.myContext.fillText(stepYValue*j,nXRange*0.9,me.height-me.coordinateStyle.footerRange-nYRange*j );
                    }
                }
                else
                {
                    for(var j=0;j<nYStepNumber+1;j++)
                    {
                        me.myContext.strokeText(stepYValue*j,nXRange*0.9,me.height-me.coordinateStyle.footerRange-nYRange*j );
                    }
                }
            }

            //X轴文字
            me.myContext.textAlign = "center";
            if(me.coordinateStyle.bShowXAxesText)
            {
                if(me.coordinateStyle.axesTextStyle.bFillText)
                {
                    for(var i=0;i<me.itemArray[0].length;i++)
                    {
                            var xBegin= (1+(i+1)+i*me.itemArray.length)*me.nXRange + me.itemArray.length*me.nXRange/2;
                            me.myContext.fillText(me.itemArray[0][i].strCaption,xBegin,me.height-me.coordinateStyle.footerRange+15 );
                    }
                }
                else
                {
                    for(var i=0;i<me.itemArray[0].length;i++)
                    {
                        var xBegin= (1+(i+1)+i*me.itemArray.length)*me.nXRange + me.itemArray.length*me.nXRange/2;
                        me.myContext.strokeText(me.itemArray[0][i].strCaption,xBegin,me.height-me.coordinateStyle.footerRange+15 );
                    }
                }
            }
        }
    },

    /**
     * Method: calculateValue
     * 计算图表绘制所需数值
     */
    calculateValue:function(){
        var me = this;
        me.nMaxSpace = me.itemArray[0].length;

        //所有项、空白、以及坐标轴两侧的空隙
        me.nXRange = me.width/(me.nMaxSpace+1 + me.nMaxSpace*me.itemArray.length + 2);
        me.axesHeight =  me.height - me.coordinateStyle.headRange - me.coordinateStyle.footerRange;
        me.nYRange = me.coordinateStyle.stepRange;

        if( me.nYRange == -1){
            me.nYRange =me.axesHeight / me.coordinateStyle.stepNumber;
            me.nYStepNumber = me.coordinateStyle.stepNumber;
        }else
        {
            me.nYStepNumber = me.axesHeight / me.nYRange;
        }
        me.stepYValue = Math.ceil(me.getMaxValue()/me.nYStepNumber);
    },

    /**
     * Method: renderChart
     * 渲染柱状统计图表
     */
    renderChart:function(){
        var me = this;
        me.renderAxes();

        if(me.bAnimation == false)
        {
            me.renderChartByAnimation(10);
        }
        else
        {
            me.iIndex = 0;
            var callBack = function(obj){
                return function(){
                    obj.renderChartByAnimation(obj.iIndex);
                    obj.iIndex ++;
                }
            }(me);

            me.queueId = OpenCharts.Animation.start(
                callBack,
                null,null
            );
        }
    },

    renderChartByAnimation:function(step){
        var me = this;

        var nXRange = me.nXRange;
        var height =  me.axesHeight;
        var nYRange = me.nYRange;
        var nYStepNumber = me.nYStepNumber;
        var stepYValue = me.stepYValue;

        for(var i=0;i<me.itemArray.length;i++)
        {
            for(var j=0;j<me.itemArray[i].length;j++)
            {
                var barStyle = me.itemArray[i][j].itemStyle;
                if(typeof barStyle == "string")
                {
                    me.myContext.strokeStyle = barStyle;
                    me.myContext.lineWidth = 1;
                    me.myContext.fillStyle = barStyle;
                } else if(barStyle === undefined)
                {
                    me.myContext.strokeStyle = "#" + i.toString() + i.toString() + i.toString();
                    me.myContext.lineWidth = 1;
                    me.myContext.fillStyle = me.myContext.strokeStyle;
                }
                else
                {
                    me.myContext.strokeStyle = barStyle.strokestrokeStyle;
                    me.myContext.lineWidth = barStyle.lineWidth;
                    me.myContext.fillStyle = barStyle.fillStyle;
                }

                var xBegin= (1+(j+1)+j*me.itemArray.length+i)*me.nXRange;

                var yBegin=me.height - (me.itemArray[i][j].data / stepYValue * nYRange) -  me.coordinateStyle.footerRange;
                var nHeight=me.itemArray[i][j].data / stepYValue * nYRange;

                me.myContext.fillRect(xBegin,yBegin+nHeight*(1-step/10),me.nXRange,nHeight*step/10);
            }
        }

        if(me.bAnimation == true &&
            step == 10)
        {
            OpenCharts.Animation.stop(me.queueId);

            if(me.bShowLegend)
                me.renderLegend();
        }
    },

    /**
     * APIMethod: getItemByPosition
     * 获取点击点所在的图表子项
     * Parameters:
     * pointX - {Number} 鼠标相对canvas所在X位置。
     * pointY - {Number} 鼠标相对canvas所在Y位置。
     * selectType - {String} 点击类型，精确or按照x。
     *
     * Returns:
     * {JsonObject} 返回所在IndexX和IndexY及其描述信息
     */
    getItemByPosition:function(pointX,pointY,selectType){
        var me = this;
        var bXInRange = pointX>=me.nXRange && pointX<=(me.width-me.nXRange);
        var bYInRange = pointY>=me.coordinateStyle.headRange && pointY<=(me.height-me.coordinateStyle.footerRange);
        if(!bXInRange || !bYInRange)
            return null;

        var nXRange = me.nXRange;
        var height =  me.axesHeight;
        var nYRange = me.nYRange;
        var nYStepNumber = me.nYStepNumber;
        var stepYValue = me.stepYValue;

        var itemSelectedArray = [];
        for(var i=0;i<me.itemArray.length;i++)
        {
            for(var j=0;j<me.itemArray[i].length;j++)
            {
                var xBegin= (1+(j+1)+j*me.itemArray.length+i)*me.nXRange;
                var xEnd= (1+(j+1)+j*me.itemArray.length+i+1)*me.nXRange;
                var yBegin=me.height - (me.itemArray[i][j].data / stepYValue * nYRange) -  me.coordinateStyle.footerRange;
                var yEnd=me.height - me.coordinateStyle.footerRange;
                if(pointX>=xBegin && pointX<xEnd
                    && pointY>=yBegin && pointY<yEnd)
                {
                    itemSelectedArray.push({indexX:i,indexY:j});
                    return itemSelectedArray;
                }
            }
        }
        return itemSelectedArray;
    },

    /**
     * Method: onMouse
     * 鼠标事件
     * Parameters:
     * evt - {Object} 鼠标点击事件。
     */
    onMouse:function(evt){
        var me = this;
        var relPosition = me.relMouseCoords(evt);
        var mouseX=relPosition.x;
        var mouseY=relPosition.y;
        var items = me.getItemByPosition(mouseX,mouseY);
        if(items==null || items.length==0)
        {
            me.msgObj.style.visibility = "hidden";
            return;
        }

        me.msgObj.style.visibility = "visible";
        var strokecolor = "#000";
        var areaStyle = me.itemArray[items[0].indexX][items[0].indexY].itemStyle;
        if(typeof areaStyle == "string")
        {
            strokecolor = areaStyle;
        }

        me.msgObj.style.borderColor= strokecolor;
        me.msgObj.className = "message";
        var value = mouseX + me.totalOffsetX;
        me.msgObj.style.left = value+"px";
        value = mouseY + me.totalOffsetY;
        me.msgObj.style.top = value+ "px";
        me.msgObj.style.position = "absolute";
        me.msgObj.style.visibility = "visible";
        me.msgObj.innerHTML = "";
        for(var i=0;i<items.length;i++)
        {
            var strMessage = OpenCharts.String.format(me._message,{
                itemName: me.itemNameArray[items[i].indexX] || "",
                itemCaption:me.itemArray[items[i].indexX][items[i].indexY].strCaption ||"",
                itemData:me.itemArray[items[i].indexX][items[i].indexY].data||""
            });
            me.msgObj.innerHTML +=  strMessage + "<br />";
        }
    },

    /**
     * Method: renderLegend
     * 渲染图例
     */
    renderLegend:function(){
        var me = this;
        var nItems = me.itemArray[0].length;
        var strTitle;
        var nWidth = 0;
        var charWidth = 12;
        var nHeight = 2*charWidth*(nItems+1);
        if(nHeight>me.height)
        {
            charWidth = me.height/(2*nItems+2);
            nHeight = me.height;
        }

        var textstyle = new OpenCharts.TextStyle();
        textstyle.fTextAlign = "left";
        textstyle.fSize = "8px";
        textstyle.fFamily = "Yahei";

        for(var i=0;i<nItems;i++)
        {
            strTitle = me.itemArray[0][i].strCaption;
            var cArr = strTitle.match(/[^\x00-\xff]/ig);
            var n = strTitle.length + (cArr == null ? 0 : cArr.length);
            if(nWidth<n*charWidth/2)
                nWidth = n*charWidth/2;
        }

        var titlePosition = new OpenCharts.Point(me.width-10-nWidth,(me.height-nHeight)/2+2*charWidth+5);

        me.myContext.fillStyle = "#FFF";
        me.myContext.strokeStyle = "#000";
        me.myContext.lineWidth = 1;
        me.myContext.fillRect(titlePosition.x - charWidth*2,titlePosition.y-charWidth*2,me.width-titlePosition.x+2*charWidth - 5,nItems*2*charWidth+charWidth);
        me.myContext.strokeRect(titlePosition.x - charWidth*2,titlePosition.y-charWidth*2,me.width-titlePosition.x+2*charWidth - 5,nItems*2*charWidth+charWidth);

        for(var i=0;i<nItems;i++)
        {
            var fillStyle = me.itemArray[0][i].itemStyle;
            if(typeof fillStyle == "string")
            {
                me.myContext.fillStyle = fillStyle;
            } else if(fillStyle === undefined)
            {
                me.myContext.fillStyle = "#" + i.toString() + i.toString() + i.toString();
            }
            else
            {
                me.myContext.fillStyle = fillStyle.fillStyle;
            }
            me.myContext.strokeStyle = "#000";
            me.myContext.lineWidth = 1;
            me.myContext.fillRect(titlePosition.x - charWidth-5,titlePosition.y-charWidth,12,12);
            me.myContext.strokeRect(titlePosition.x - charWidth-5,titlePosition.y-charWidth,12,12);

            strTitle = me.itemArray[0][i].strCaption;
            OpenCharts.RenderEnginer.renderText(me.myContext,strTitle,textstyle,titlePosition);
            titlePosition.y += 2*charWidth;
        }
    },

    CLASS_NAME: "OpenCharts.Chart.BarChart"
});