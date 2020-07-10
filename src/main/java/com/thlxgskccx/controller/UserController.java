package com.thlxgskccx.controller;

import com.sun.deploy.net.HttpResponse;
import com.thlxgskccx.model.PatientInfo;
import com.thlxgskccx.model.User;
import com.thlxgskccx.model.UserTrack;
import com.thlxgskccx.service.UserService;
import javafx.scene.shape.PathElement;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
@RestController
public class UserController {
    @Autowired
    UserService userService;
    public String userphone;
    @RequestMapping("/getAllUsers")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @RequestMapping("/addTrack")
    public void addTrack(HttpServletRequest request){
        String userphone = request.getParameter("userphone");
        HttpSession session = request.getSession();
        session.setAttribute("userphone",userphone);
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        String enaw = request.getParameter("enaw");
        String information = request.getParameter("information");
        UserTrack userTrack = new UserTrack(userphone,name,position,enaw,information);
        System.out.println(userTrack.toString());
       userService.addTrack(userTrack);
    }
    @RequestMapping("/AffectProb")
    @ResponseBody
    public String AffectProb(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userphone = request.getParameter("userphone");
        System.out.println("传参"+userphone);
        List<UserTrack> userTrack = userService.getAllUserTrack(userphone);
        List<PatientInfo> patientInfos = userService.getAllPatient();
        System.out.println( userTrack);
        System.out.println( patientInfos);
        /*
        计算感染风险，考虑空间和时间两方面：
        空间（60%）：方圆小于100米：90%+[(100-x)/1000*100%]---比如30m，就是97%；
              100--200米：80%+[(200-x)/1000*100%]；
              200--300米：70%+[(300-x)/1000*100%];
              ...
              900--1000米：[(1000-x)/1000*100%]；
              1000米以外：0%
        时间（40%）：进出区间全部在病例时间区间内为100%；
                  如果有重叠，则（重叠时间区域）/(进出时间区域)*100%.
         最终一个记录的感染风险为空间+时间：area*60%+time*40%.
         多个记录取平均值。
         */
        double[] Affected = new double[100];//用户的感染风险指数集合，最后取平均数
        int k =0;
        for(UserTrack u : userTrack){
            int i =0;
            double[] trackaffected = new double[100];//这一条轨迹上的感染风险指数
            System.out.println(u.getPosition());
            double lat1 = Double.parseDouble(u.getPosition().split(",")[1]);
            double lon1 = Double.parseDouble(u.getPosition().split(",")[0]);
            for(PatientInfo p:patientInfos){
                double meteraffect = 0;//空间
                double timeaffect = 0;//时间
                double lat2 = Double.parseDouble(p.getLatitude());
                double lon2 = Double.parseDouble(p.getLongitude());
                GlobalCoordinates source = new GlobalCoordinates(lat1, lon1);
                GlobalCoordinates target = new GlobalCoordinates(lat2, lon2);
                double meter = getDistanceMeter(source, target, Ellipsoid.Sphere);
                System.out.println("距离："+meter);
                if(meter<1000) {
                    meteraffect = meterprob(meter);
                    if(org.apache.commons.lang3.time.DateUtils.isSameDay(p.getConfirmdate(),u.getEnawtime())) timeaffect = 1;
                    else timeaffect = 0;//不在同一天也没事
                    trackaffected[i] = meteraffect*0.6+timeaffect*0.4;//小数组
                    System.out.println("现在是："+meteraffect*0.6+timeaffect*0.4);
                    i++;
                }
            }
            double sum = 0;
            for(int j = 0;j<trackaffected.length;j++){
                sum+=trackaffected[j];
            }
            System.out.println("sum:"+sum);
            System.out.println("length:"+i);
            Affected[k] = sum/i;//大数组
            System.out.println("感染风险"+k+Affected[k]);
            k++;
        }
        double res = 0;
        for(int j = 0;j<Affected.length;j++){
            res+=Affected[j];
        }
        System.out.println("感染风险总的"+res/k);
        JSONArray returndata = new JSONArray();
        try {
            returndata.put(res/k*100);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "successCallback("+returndata+")";//总感染风险指数
    }
    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid){

        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();
    }
    double meterprob(double m){
        /*空间（60%）：方圆小于100米：90%+[(100-x)/1000*100%]---比如30m，就是97%；
        100--200米：80%+[(200-x)/1000*100%]；
        200--300米：70%+[(300-x)/1000*100%];
              ...
        900--1000米：[(1000-x)/1000*100%]；*/
        double res=0;
        if(m<100)  res = 0.9+(100-m)/1000;
        else if(m<=200&&m>100) res = 0.8+(200-m)/1000;
        else if(m<=300&&m>200) res = 0.7+(300-m)/1000;
        else if(m<=400&&m>300) res = 0.6+(400-m)/1000;
        else if(m<=500&&m>400) res = 0.5+(500-m)/1000;
        else if(m<=600&&m>500) res = 0.4+(600-m)/1000;
        else if(m<=700&&m>600) res = 0.3+(700-m)/1000;
        else if(m<=800&&m>700) res = 0.2+(800-m)/1000;
        else if(m<=900&&m>800) res = 0.1+(900-m)/1000;
        else if(m<=1000&&m>900) res = (1000-m)/1000;
        return res;
    }
    @RequestMapping("/testGet")
    public void test(HttpServletRequest request){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println(name+","+password);

    }
}
