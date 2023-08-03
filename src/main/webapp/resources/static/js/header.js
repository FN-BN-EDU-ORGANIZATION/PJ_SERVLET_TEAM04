//뉴스메뉴
function showNewsList() {
    const newsList = document.querySelector('.news-list');
    if (newsList.style.visibility === 'hidden') {
        newsList.style.visibility = 'visible';
    } else {
        newsList.style.visibility = 'hidden';
    }
}
// 타이머
function displayDateTime() {
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const time = `${hours}:${minutes < 10 ? '0' + minutes : minutes}`;
    const dateString = `${year}-${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`;

    const dateElements = document.getElementsByClassName('date');
    const timeElements = document.getElementsByClassName('time');
    for (let i = 0; i < dateElements.length; i++) {
        dateElements[i].textContent = dateString;
    }
    for (let i = 0; i < timeElements.length; i++) {
        timeElements[i].textContent = time;
    }
}
setInterval(displayDateTime, 1000); // 1초마다 날짜와 시간 갱신