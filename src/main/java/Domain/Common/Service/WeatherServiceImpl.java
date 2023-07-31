package Domain.Common.Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Domain.Common.Dao.AddressDao;
import Domain.Common.Dao.AddressDaoIpml;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class WeatherServiceImpl implements WeatherService {
	private static WeatherService Instance;
	public static WeatherService getInstance() {
		if(Instance==null)
			Instance = new WeatherServiceImpl();
		return Instance;
	}
	
	private AddressDao DBS;
	public WeatherServiceImpl() {
		DBS = AddressDaoIpml.getInstance();
	}

    public String nx="89";
    public String ny="90";
	
	//================================================================
	@Override
	public Set<String> nameLvl1() throws SQLException {
		System.out.println("시 선택 서비스");
		return DBS.selectNamesLevel1();
	}
	
	@Override
	public Set<String> nameLvl2(String a) throws SQLException{
		System.out.println("구 선택 서비스");
		return DBS.selectNamesLevel2(a);
	}
	
	@Override
	public Set<String> nameLvl3(String a,String b) throws SQLException{
		System.out.println("동 선택 서비스");
		return DBS.selectNamesLevel3(a, b);
	}
	@Override
	public Map<String,Object> coordinate(String a,String b,String c) throws SQLException {
		System.out.println("좌표 검색 서비스");
        Map<String,Object> map = new HashMap();
		map = DBS.getcoordinate(a, b, c);
        nx=(String)map.get("NX");
        ny=(String)map.get("NY");
        System.out.println(nx);
        System.out.println(ny);
        
        return map;
	}
	//==================================================================


//API 요청값
    String serviceKey = "M7UdcUfNMFBu8D3ng0rZrilA8oNgv1Sfr3kT%2BdeJphKw5BlLPTksBL2suXd1hMK5hQ5XMr5hCsgsFDNzfQ7UUg%3D%3D";
    String pageNo = "1";
    String numOfRows = "60";
    String dataType = "json";
    String base_date = DateNow();
    String base_time = TimeNow();



    public Map<String ,Object> Ncst=new HashMap<>();//실황 데이터 입력된 Map(key=PTY,RN1,REH,T1H)






    //메서드 영역
    String DateNow(){
        LocalDate date=LocalDate.now();

        int year=date.getYear();
        int month=date.getMonthValue();
        int day=date.getDayOfMonth();
        String strYear=year+"";
        String strMonth;
        String strDay;
        if(month<10)
            strMonth="0"+month;
        else
            strMonth=month+"";
        if(day<10)
            strDay="0"+day;
        else
            strDay=day+"";

        String strDate=strYear.concat(strMonth);
        strDate=strDate.concat((strDay));

        return strDate;
    }
    String TimeNow() {

        LocalTime time=LocalTime.now();
        int hour=time.getHour();
        int minute= time.getMinute();

        String strHour;
        String strMinute;

        if(minute<=40){
            hour=hour-1;
            minute=41;
        }
        else{
            minute=0;
        }

        if(hour<0)
            hour=0;

        strHour=hour+"";
        strMinute=minute+"";

        if(hour<10)
            strHour="0"+hour;
        if(minute<10)
            strMinute="0"+minute;


        String strTime=strHour.concat(strMinute);

        return strTime;
    }
    @Override
	public Map<String ,Object> ApiExplorerNcst() throws Exception {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
//        System.out.println(result);


        //파싱

        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);

        // response 키를 가지고 데이터를 파싱
        JSONObject parse_response = (JSONObject) obj.get("response");

        // response 로 부터 body 찾기
        JSONObject parse_body = (JSONObject) parse_response.get("body");

        // body 로 부터 items 찾기
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        //JSONObject item = (JSONObject) parse_item.get("item");


        //받은 데이터 입력

        for (int i = 0; i < parse_item.size(); i++) {
//            System.out.println(parse_item.get(i));
            JSONObject item = (JSONObject) parse_item.get(i);
            String category = (String) item.get("category");
            String obsrValue = (String) item.get("obsrValue");
//
            //강수상태
            if (category.equals("PTY")) {
                Ncst.put("PTY",obsrValue);

            }

            //1시간 강수량
            else if (category.equals("RN1")) {
                Ncst.put("RN1",obsrValue);
            }

            //습도 출력
            else if (category.equals("REH")) {
                Ncst.put("REH",obsrValue);
            }

            //기온 출력
            else if (category.equals("T1H")) {
                Ncst.put("T1H",obsrValue);
            }

        }

        return Ncst;
    }

    //예보 데이터 입력된 Map(Key=fcstTime,Value=fcstValue)
    public Map<String,Object> fcstPTY=new TreeMap<>();
    public Map<String,Object> fcstRN1=new TreeMap<>();
    public Map<String,Object> fcstREH=new TreeMap<>();
    public Map<String,Object> fcstT1H=new TreeMap<>();
    @Override
	public void ApiExplorerFcst() throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(base_date, "UTF-8")); /*‘21년 6월 28일 발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(base_time, "UTF-8")); /*06시 발표(정시단위) */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result=sb.toString();

        //파싱

        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);

        // response 키를 가지고 데이터를 파싱
        JSONObject parse_response = (JSONObject) obj.get("response");

        // response 로 부터 body 찾기
        JSONObject parse_body = (JSONObject) parse_response.get("body");

        // body 로 부터 items 찾기
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        JSONArray parse_item = (JSONArray) parse_items.get("item");

        //받은 데이터 입력
        for (int i = 0; i < parse_item.size(); i++) {
            System.out.println(parse_item.get(i));
            JSONObject item = (JSONObject) parse_item.get(i);
            String category = (String) item.get("category");
            String fcstTime = (String) item.get("fcstTime");
            String fcstValue = (String) item.get("fcstValue");

            //강수상태
            if (category.equals("PTY")) {
                if (fcstValue.equals("0"))
                    fcstValue="강수X";
                else if (fcstValue.equals("1"))
                    fcstValue="비";
                else if (fcstValue.equals("2"))
                    fcstValue="비/눈";
                else if (fcstValue.equals("3"))
                    fcstValue="눈";
                else if (fcstValue.equals("5"))
                    fcstValue="빗방울";
                else if (fcstValue.equals("6"))
                    fcstValue="빗방울/눈날림";
                else if (fcstValue.equals("7"))
                    fcstValue="눈날림";
                fcstPTY.put(fcstTime,fcstValue);
            }

            //1시간 강수량
            else if (category.equals("RN1")) {
                if(fcstValue.equals("강수없음"))
                    fcstValue="0.0mm";
                fcstRN1.put(fcstTime,fcstValue);
            }

            //습도 출력
            else if (category.equals("REH")) {
                fcstREH.put(fcstTime,fcstValue);
            }

            //기온 출력
            else if (category.equals("T1H")) {
                fcstT1H.put(fcstTime,fcstValue);
            }


        }

    }


}



