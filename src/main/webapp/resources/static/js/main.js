    //날씨상세
    $(function () {
        $('.fcst').hide();
        $('.weather-icon').hover(function () {
          $(this).parent().find('.fcst').slideToggle(400);
          $(this).parent().hover(function () {
          })
        });
      });
  
      //명언 제조기
      const swiper = new Swiper('.swiper', {
        // Optional parameters
        direction: 'vertical',
        loop: true,
        autoplay: {
          delay: 5000,
        },
  
  
      });
      
      
  
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

      
 