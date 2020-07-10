package com.thlxgskccx.controller;

import com.thlxgskccx.model.Patient;
import com.thlxgskccx.service.PatientService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "city")
public class SocietyController {
    private static final double EARTH_RADIUS = 6378.137;
    DecimalFormat df = new DecimalFormat("#.00");//double保留两位小数

    @Autowired
    private RestTemplate restTemplate;
@Autowired
@Resource
    private PatientService patientService;
    /***********HTTP GET method*************/

    @RequestMapping(value = "getsociety")
    @ResponseBody
    public void getJson(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //获得前端传值 连接api
        String city = request.getParameter("cityName");
        double lng = Double.parseDouble(request.getParameter("longitude"));
        double lat = Double.parseDouble(request.getParameter("latitude"));
        System.out.println(city);
        System.out.println("lng:"+lng+",lat:"+lat);
        String url = "https://wuliang.art/ncov/village/getVillage2?cityName=" + city;
        //获取api的值并处理打包成json
        ResponseEntity<String> results = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String json = results.getBody();
        System.out.println(json);

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");//获取jsonarray对象

            response.setContentType("text/text"); //设置请求以及响应的内容类型以及编码方式
            response.setCharacterEncoding("UTF-8");
            JSONArray returndata = new JSONArray();
            for (int i = 0; i < data.length(); i++) {
                JSONObject item = data.getJSONObject(i);//遍历 jsonarray 数组，把每一个对象转成 json 对象
                String society = item.get("posi_name").toString();//社区
                String address = item.get("posi_address").toString();//地址
                String point = item.get("point").toString();
                String[] pointstr = point.split("\\,|\\[|\\]");
                double latitude = Double.parseDouble(pointstr[1]);//维度
                double longitude = Double.parseDouble(pointstr[2]);//经度
                String dis = df.format(getDistance(lng,lat,longitude,latitude));//距离
                String datestr = item.get("update_time").toString();
                String confirmdate = datestr.substring(0, datestr.indexOf(" "));//确诊时间
                //获取当前系统日期
                SimpleDateFormat dateFormat = new SimpleDateFormat(" yyyy/MM/dd ");
                String currentDate =   dateFormat.format( new Date() );
                int day = getday(currentDate,confirmdate);

                if(Double.parseDouble(dis) <= 20 ){
                    /*Map<String,Object> map = new HashMap<>();
                    map.put("society",society);
                    map.put("latitude",latitude);
                    map.put("longitude",longitude);
                    map.put("dis",dis);
                    map.put("confirmdate",confirmdate);
                    map.put("day",day);*/
                    JSONObject obj = new JSONObject();
                    obj.put("society",society);//病例社区
                    obj.put("latitude",latitude);//纬度
                    obj.put("longitude",longitude);//经度
                    obj.put("dis",dis);
                    obj.put("confirmdate",confirmdate);//确诊日期
                    obj.put("day",day);
                    obj.put("address",address);//详细地址

                    returndata.put(obj);

                    Patient patient = new Patient();
                    patient.setSociety(society);
                    patient.setLatitude(pointstr[1]);
                    patient.setLongitude(pointstr[2]);
                    patient.setConfirmdate(confirmdate);
                    patient.setAddress(address);

                    patientService.addpatient(patient);
                    //System.out.println(society + " " + latitude + " " + longitude + " " + day+" "+dis+" "+confirmdate);
                }

            }
            PrintWriter writer = response.getWriter();
            writer.print(returndata);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //return map;
    }

    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s =  s * EARTH_RADIUS;
        return s;
    }

    public static int getday(String dbtime1,String dbtime2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = format.parse(dbtime1);
        Date date2 = format.parse(dbtime2);
        int a = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
        return a;
    }


}
