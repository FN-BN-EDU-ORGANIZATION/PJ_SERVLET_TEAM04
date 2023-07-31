<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>

    <style>
        .wrapper-body {
        }

        .wrapper-body > section:nth-child(1) {
            top: 10vh;
            list-style: none;
            height: 60vh;
            justify-content: center;
            align-items: center;
            display: flex;
            flex-direction: column;
        }

        .wrapper-body > section:nth-child(1) > .addr {
            margin: 1vh;
            display: flex;
            justify-content: center;
        }

        .wrapper-body > section:nth-child(1) > .addr > li {
        }

        .wrapper-body > section:nth-child(1) > .weather-icon {
            margin: 1vh;
            display: flex;
            justify-content: center;
            width: 21vh;
        }

        .wrapper-body > section:nth-child(1) > .weather-text {
            margin: 1vh;
            display: flex;
            justify-content: center;
        }


    </style>
</head>
<body>


<select id="city">

</select>

<select id="county">

</select>

<select id="dong">

</select>

<hr>
<button id="inxy">좌표 출력</button>


<div class="wrapper-body">
    <section>
        <div class="addr">

        </div>

        <div class="weather-icon">
            <a><img src="img/0.png" style="width: 21vh; height: 21vh;"></a>
        </div>

        <div class="weather-text">
            <a>
                <li style="font-size: 2.7vh;" id="T1H">기온:  ℃</li>
                <li style="font-size: 2.7vh;" id="REH">습도:  %</li>
                <li style="font-size: 2.7vh;" id="RN1">강수량:  mm</li>
            </a>
        </div>


    </section>
    <section>

    </section>

</div>


<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js"
        integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
    // 비동기 요청으로 JSON 데이터 받아오기
    const projectPath='${pageContext.request.contextPath}'; // WeatherForecast/
    const ServerPort = '${pageContext.request.serverPort}'; // 8080/

    function ncst() {
        // 자바로 구현된 API를 호출하여 특정값을 가져오는 Ajax 요청
        fetch("http://localhost:" + ServerPort + projectPath + "/api/ncst.do")

            .then(response => response.json())
            .then(data => {
                // API에서 가져온 특정값을 화면에 출력
                document.getElementById('T1H').innerText ="기온: "+ data.T1H+" ℃";
                document.getElementById('REH').innerText ="습도: "+ data.REH+" %";
                document.getElementById('RN1').innerText ="강수량: "+ data.RN1+" mm";
            })
            .catch(error => console.error('API 호출 에러:', error));

    }

    let cityvar;
    let county;
    let dong;

    //시 선택 스크립트 // http://localhost:8080/WeatherForecast//Combobox/ccity.do
    axios.get("http://localhost:"+ ServerPort + projectPath +"/Combobox/ccity.do")
        .then(function (response) {
            var dataList = response.data;

            // 받아온 데이터를 콤보박스에 추가
            var comboBox = document.getElementById('city');
            for (var i = 0; i < dataList.length; i++) {
                var option = document.createElement('option');
                option.text = dataList[i];
                comboBox.appendChild(option);
            }
        })
        .catch(function (error) {
            console.error('콤보박스 리스트 추가 실패.. 원인 : '+error);
        });

    function sendSelectedValue(svalue){
        axios.post("http://localhost:"+ ServerPort + projectPath +"/Combobox/ccounty.do",null,{params:{ccounty : svalue}})
            .then(function (response){
                var dataList = response.data;

                var comboBox = document.getElementById('county');
                var comboBox2 = document.getElementById('dong');
                comboBox.innerHTML = '';
                comboBox2.innerHTML = '';
                for (var i = 0; i < dataList.length; i++) {
                    var option = document.createElement('option');
                    option.text = dataList[i];
                    comboBox.appendChild(option);
                }
            })
            .catch(function (error) {
                console.error('콤보박스 리스트 추가 실패.. 원인 : '+error);
            });
    }

    function sendSelectedValue2(svalue1,svalue2){
        var svalue = {params:{ccounty : svalue2,
                city : svalue1}};
        axios.post("http://localhost:"+ ServerPort + projectPath +"/Combobox/cdong.do",null,svalue)
            .then(function (response){
                var dataList = response.data;

                var comboBox = document.getElementById('dong');
                comboBox.innerHTML = '';
                for (var i = 0; i < dataList.length; i++) {
                    var option = document.createElement('option');
                    option.text = dataList[i];
                    comboBox.appendChild(option);
                }
            })
            .catch(function (error) {
                console.error('콤보박스 리스트 추가 실패.. 원인 : '+error);
            });
    }


    function sendSelectedxy(svalue1,svalue2,svalue3){
        var svalue = {params:{ccounty : svalue2,
                city : svalue1,
                dong : svalue3}};
        axios.post("http://localhost:"+ ServerPort + projectPath +"/Combobox/Coordinate.do",null,svalue)
            /*           .then(function (response){


                      }) */
            .catch(function (error) {
                console.error('좌표 생설실패.. 원인 : '+error);
            });
    }





    const cityel = document.getElementById('city');
    cityel.addEventListener('change',function(){
        var svalue = this.value;
        cityvar = svalue;
        county = null;
        dong = null;
        sendSelectedValue(svalue);
    });

    const countyel = document.getElementById('county');
    countyel.addEventListener('change',function(){
        var svalue = this.value;
        county = svalue;
        dong = null;
        sendSelectedValue2(cityvar,svalue);
    });
    const dongel = document.getElementById('dong');
    dongel.addEventListener('change',function(){
        var svalue = this.value;
        dong = svalue;
    });


    const inxyel = document.getElementById('inxy');
    inxyel.addEventListener('click',function(){
        console.log("cityvar : "+cityvar);
        console.log("county : "+county);
        console.log("dong : "+dong);

        sendSelectedxy(cityvar,county,dong);
    });

    const print_ncst=document.getElementById('inxy');
    print_ncst.addEventListener('click', function (){
        ncst();
    })


    ncst();


</script>


</body>
</html>